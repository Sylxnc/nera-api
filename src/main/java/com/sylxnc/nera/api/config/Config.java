package com.sylxnc.nera.api.config;


import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;

public class Config {

    private MongoClient mongo;
    private MongoDatabase db;
    private MongoCollection<Document> collection;
    @Getter
    private Document config;
    private String name;

    public Config(Guild guild, String name) {
        mongo = MongoClients.create(new ConnectionString("mongodb://localhost:27017/"));
        db = mongo.getDatabase("ServerConfigs");
        this.name = name;
        collection = db.getCollection(guild.getId());
        config = new Document("cfg", name);

        Document existing = collection.find(new Document("cfg", name)).first();
        if (existing != null) {
            config = existing;
        }
    }

    public void set(String key, Object value) {
        config.put(key, value);
    }

    public void save() {
        Document query = new Document("cfg", name);
        Document existing = collection.find(query).first();

        if (existing != null) {
            collection.updateOne(query, new Document("$set", config));
        } else {
            collection.insertOne(config);
        }
    }
}
