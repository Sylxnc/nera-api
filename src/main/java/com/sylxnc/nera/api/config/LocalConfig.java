package com.sylxnc.nera.api.config;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class LocalConfig {

    @Getter
    private Map<String, Object> config;

    private final String filePath;


    public LocalConfig(Guild guild, String configName) {
        this.filePath = "configs/" + guild.getId() + "/" + configName + ".yml";
        File file = new File(filePath);

        if (!file.exists()) {
            throw new RuntimeException("Config file not found: " + filePath);
        }

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Could not load config file: " + filePath);
            }
            Yaml yaml = new Yaml(new SafeConstructor(new LoaderOptions()));
            config = yaml.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config: " + e.getMessage(), e);
        }
    }

    public void save() {
        try {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setPrettyFlow(true);

            Yaml yaml = new Yaml(new Representer(options));
            File file = new File(filePath);

            // Erstelle Ordner falls nicht vorhanden
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }

            try (FileWriter writer = new FileWriter(file)) {
                yaml.dump(config, writer);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to save config: " + e.getMessage(), e);
        }
    }

    public void set(String key, Object value) {
        config.put(key, value);
    }


    public Object get(String key) {
        if (config.containsKey(key)) {
            return config.get(key);
        } else {
            throw new NullPointerException("Config value not found");
        }
    }

    public String getString(String key) {
        Object value = get(key);
        return value instanceof String ? (String) value : String.valueOf(value);
    }

    public int getInt(String key) {
        Object value = get(key);
        return value instanceof Number ? ((Number) value).intValue() : Integer.parseInt(value.toString());
    }

    public long getLong(String key) {
        Object value = get(key);
        return value instanceof Number ? ((Number) value).longValue() : Long.parseLong(value.toString());
    }

    public boolean getBoolean(String key) {
        Object value = get(key);
        return value instanceof Boolean ? (Boolean) value : Boolean.parseBoolean(value.toString());
    }

    public double getDouble(String key) {
        Object value = get(key);
        return value instanceof Number ? ((Number) value).doubleValue() : Double.parseDouble(value.toString());
    }
}
