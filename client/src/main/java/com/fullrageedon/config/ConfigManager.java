package com.fullrageedon.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;

import com.fullrageedon.modules.Module;
import com.fullrageedon.FullrageedonClient;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Configuration manager for saving/loading module settings
 */
public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_DIR = "fullrageedon";
    private static final String CONFIG_FILE = "config.json";
    private File configDirectory;
    private File configFile;

    public ConfigManager() {
        File mcDir = MinecraftClient.getInstance().runDirectory;
        configDirectory = new File(mcDir, CONFIG_DIR);
        if (!configDirectory.exists()) {
            configDirectory.mkdirs();
        }
        configFile = new File(configDirectory, CONFIG_FILE);
    }

    public void saveConfig() {
        try {
            JsonObject root = new JsonObject();
            JsonObject modules = new JsonObject();

            // Save each module's state
            for (Module module : FullrageedonClient.moduleManager.getAllModules().values()) {
                JsonObject moduleJson = new JsonObject();
                moduleJson.addProperty("enabled", module.isEnabled());
                moduleJson.addProperty("keyCode", module.getKeyCode());
                modules.add(module.getName(), moduleJson);
            }

            root.add("modules", modules);

            try (FileWriter writer = new FileWriter(configFile)) {
                GSON.toJson(root, writer);
            }

            System.out.println("[Fullrageedon] Config saved successfully!");
        } catch (IOException e) {
            System.err.println("[Fullrageedon] Failed to save config: " + e.getMessage());
        }
    }

    public void loadConfig() {
        try {
            if (configFile.exists()) {
                try (FileReader reader = new FileReader(configFile)) {
                    JsonObject root = GSON.fromJson(reader, JsonObject.class);
                    if (root.has("modules")) {
                        JsonObject modules = root.getAsJsonObject("modules");
                        for (String moduleName : modules.keySet()) {
                            JsonObject moduleJson = modules.getAsJsonObject(moduleName);
                            Module module = FullrageedonClient.moduleManager.getModule(moduleName);
                            if (module != null) {
                                if (moduleJson.get("enabled").getAsBoolean()) {
                                    module.enable();
                                }
                            }
                        }
                    }
                }
                System.out.println("[Fullrageedon] Config loaded successfully!");
            }
        } catch (IOException e) {
            System.err.println("[Fullrageedon] Failed to load config: " + e.getMessage());
        }
    }

    public File getConfigDirectory() {
        return configDirectory;
    }

    public File getConfigFile() {
        return configFile;
    }
}
