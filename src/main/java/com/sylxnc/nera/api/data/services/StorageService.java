package com.sylxnc.nera.api.data.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.bson.Document;
import redis.clients.jedis.Jedis;


import java.util.Objects;

public class StorageService {



    private final Dotenv dotenv = Dotenv.load();

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    @Getter
    private Jedis jedis;


    public void init() {
        initMongo();
        initRedis();

    }

    private void initMongo() {
        this.mongoClient = MongoClients.create(dotenv.get("MONGO_URI"));
        this.mongoDatabase = this.mongoClient.getDatabase("MONGO_DB");
        System.out.println("MongoDB initialized.");
    }

    private void initRedis() {
        this.jedis = new Jedis(dotenv.get("REDIS_HOST"), Integer.parseInt(dotenv.get("REDIS_PORT")));
        this.jedis.ping();
        System.out.println("Redis (Jedis) initialized.");
    }

    public Document document (String identifier, String collection) {
        if(this.mongoDatabase.getCollection(collection).find(new Document("_id",identifier)).first() != null) {
            return this.mongoDatabase.getCollection(collection).find(new Document("_id", identifier)).first();
        }else {
            throw  new NullPointerException("Document does not exist.");
        }
    }

    public void pushDoc(Document doc,String collection) {
        String identifier = doc.getString("_id");

        if(this.mongoDatabase.getCollection(collection).find(new Document("_id",identifier)).first() != null) {
            this.mongoDatabase.getCollection(collection).insertOne(doc);
        }else {
            this.mongoDatabase.getCollection(collection).deleteOne(Objects.requireNonNull(mongoDatabase.getCollection(collection).find(new Document("_id", identifier)).first()));
            pushDoc(doc,collection);
        }
    }







}
