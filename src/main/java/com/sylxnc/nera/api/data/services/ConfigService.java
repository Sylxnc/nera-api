package com.sylxnc.nera.api.data.services;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;

public class ConfigService {

    private MongoClient mongo;
    private MongoDatabase db;
    private MongoCollection<Document> collection;
    @Getter
    private Document config;
    private String name;

    public ConfigService(Guild guild, String name) {
        Dotenv dotenv = Dotenv.load();
        mongo = MongoClients.create(new ConnectionString(dotenv.get("MONGO_URI")));
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
        if(config.containsKey(key)) {
            config.replace(key, value);
        }else {
            config.append(key, value);
        }
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
