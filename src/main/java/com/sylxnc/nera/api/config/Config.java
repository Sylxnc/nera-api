package com.sylxnc.nera.api.config;

import com.mongodb.MongoException;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;

import java.util.Timer;
import java.util.TimerTask;

public class Config {

    private final NetworkConfig networkConfig;
    private final LocalConfig localConfig;

    @Getter
    private boolean usingNetwork;

    @Getter
    private Guild guild;
    private final String collectionName = "config";
    private final String configName;

    public Config(Guild guild,String configName) {
        this.guild = guild;
        NetworkConfig tempNet = null;
        boolean netAvailable = false;

        this.configName = configName;
        try {
            tempNet = new NetworkConfig(guild, collectionName, this.configName);
            if (tempNet.getConfig() != null) {
                netAvailable = true;
            }
        } catch (MongoException | NullPointerException e) {
            System.err.println("MongoDB not available, falling back to LocalConfig: " + e.getMessage());
        }

        this.networkConfig = tempNet;
        this.localConfig = new LocalConfig(guild, configName);
        this.usingNetwork = netAvailable;

        if (usingNetwork) {
            startBackupTimer();
        }
    }

    private void startBackupTimer() {
        Timer timer = new Timer(true); // Daemon-Thread
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Document netConfig = networkConfig.getConfig();
                    if (netConfig != null) {
                        for (String key : netConfig.keySet()) {
                            localConfig.set(key, netConfig.get(key));
                        }
                        localConfig.save();
                        System.out.println("Backup von MongoDB nach LocalConfig abgeschlossen.");
                    }
                } catch (Exception e) {
                    System.err.println("Backup fehlgeschlagen: " + e.getMessage());
                }
            }
        }, 0, 120_000);
    }

    public Object get(String key) {
        return usingNetwork ? networkConfig.get(key) : localConfig.get(key);
    }

    public void set(String key, Object value) {
        if (usingNetwork) {
            networkConfig.set(key, value);
        } else {
            localConfig.set(key, value);
        }
    }

    public String getString(String key) {
        return usingNetwork ? networkConfig.getString(key) : localConfig.getString(key);
    }

    public int getInt(String key) {
        return usingNetwork ? networkConfig.getInt(key) : localConfig.getInt(key);
    }

    public long getLong(String key) {
        return usingNetwork ? networkConfig.getLong(key) : localConfig.getLong(key);
    }

    public boolean getBoolean(String key) {
        return usingNetwork ? networkConfig.getBoolean(key) : localConfig.getBoolean(key);
    }

    public double getDouble(String key) {
        return usingNetwork ? networkConfig.getDouble(key) : localConfig.getDouble(key);
    }
}
