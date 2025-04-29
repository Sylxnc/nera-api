package com.sylxnc.nera.api.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;

import javax.print.Doc;
import java.util.UUID;

class NetworkConfig {

    private MongoClient mongoClient;

    private MongoDatabase database;

    private String con_string = "mongodb://localhost:27017";

    private MongoCollection<Document> collection;

    @Getter
    private Document config;

    public NetworkConfig(Guild guild, String collectionName, String configName) {
        mongoClient = MongoClients.create(con_string);
        database = mongoClient.getDatabase(guild.getId());
        collection = database.getCollection(collectionName);
        config = collection.find(new Document("config",configName)).first();
    }

    public void set(String key, Object value) {
        config.append(key, value);
    }

    public void save(){
        collection.insertOne(config);
    }

    public Object get(String key){
        if(config.get(key) != null){
            return config.get(key);
        }else throw new NullPointerException("Config value not found");

    }

    public String getString(String key){
        if(config.getString(key) != null){
            return config.getString(key);
        }else throw new NullPointerException("Config value not found");

    }

    public int getInt(String key){
        if(config.getInteger(key) != null){
            return config.getInteger(key);
        }else throw new NullPointerException("Config value not found");
    }

    public long getLong(String key){
        if(config.getLong(key) != null){
            return config.getLong(key);
        }else throw new NullPointerException("Config value not found");
    }

    public boolean getBoolean(String key){
        if(config.getBoolean(key) != null){
            return config.getBoolean(key);
        }else throw new NullPointerException("Config value not found");
    }


    public double getDouble(String key){
        if(config.getDouble(key) != null){
            return config.getDouble(key);
        }else throw new NullPointerException("Config value not found");
    }






}
