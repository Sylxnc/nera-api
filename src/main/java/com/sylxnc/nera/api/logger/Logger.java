package com.sylxnc.nera.api.logger;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final Dotenv dotenv = Dotenv.load();
    private static final MongoClient client = MongoClients.create(dotenv.get("MONGO_URI"));
    private static final MongoDatabase database = client.getDatabase(dotenv.get("MONGO_DB"));

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void info(String message) {
        log("info", "[INFO]", BLUE, message);
    }

    public static void warning(String message) {
        log("warning", "[WARN]", YELLOW, message);
    }

    public static void error(String message) {
        log("error", "[ERROR]", RED, message);
    }

    private static void log(String collectionName, String prefix, String color, String message) {
        String date = LocalDate.now().format(DATE_FORMAT);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        Document todayLog = collection.find(Filters.eq("date", date)).first();
        if (todayLog == null) {
            todayLog = new Document("date", date)
                    .append("logs", new org.bson.types.BasicBSONList());
            collection.insertOne(todayLog);
        }

        collection.updateOne(
                Filters.eq("date", date),
                Updates.push("logs", "[" + prefix + "] " + message)
        );

        System.out.println( prefix +RESET+ " " + color + message+RESET);
    }
}
