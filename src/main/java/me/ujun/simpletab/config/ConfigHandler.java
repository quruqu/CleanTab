package me.ujun.simpletab.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigHandler {

    private final JavaPlugin plugin;
    private static ConfigHandler instance;

    public static String header;
    public static String footer;
    public static  int updateDuration;

    public ConfigHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public static void init(JavaPlugin plugin) {
        instance = new ConfigHandler(plugin);
        instance.loadConfig();
    }

    public static ConfigHandler getInstance() {
        return instance;
    }

    public void loadConfig() {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        header = config.getString("Header");
        footer = config.getString("Footer");
        updateDuration = config.getInt("UpdateDuration");
    }

}
