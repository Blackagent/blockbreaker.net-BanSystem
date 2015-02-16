package me.Blackagent_007.blockbreakerBanSystem.util;


import me.Blackagent_007.blockbreakerBanSystem.Main;
import me.Blackagent_007.blockbreakerBanSystem.MySQL.MySQL;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public static File getConfigfile() {
        return new File("plugins/BanManager", "config.yml");
    }

    public static FileConfiguration getConfigFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getConfigfile());
    }



    public static File getMySQLFile() {
        return new File("plugins/BanManager", "mysql.yml");
    }

    public static FileConfiguration getMySQLFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getMySQLFile());
    }

    public static void setStandardConfig() {
        FileConfiguration cfg = getConfigFileConfiguration();
        cfg.options().copyDefaults(true);
        cfg.addDefault("prefix", "&8[&6BanManager&8]");
        try {
            cfg.save(getConfigfile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readConfig() {
        FileConfiguration cfg = getConfigFileConfiguration();
        Main.getInstance().prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("prefix")) + "§r";
    }

    public static void setStandardMySQL() {
        FileConfiguration cfg = getMySQLFileConfiguration();
        cfg.options().copyDefaults(true);
        cfg.addDefault("username", "root");
        cfg.addDefault("password", "password");
        cfg.addDefault("database", "localhost");
        cfg.addDefault("host", "localhost");
        cfg.addDefault("port", "3306");
        try {
            cfg.save(getMySQLFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readMySQL() {
        FileConfiguration cfg = getMySQLFileConfiguration();
        MySQL.username = cfg.getString("username");
        MySQL.password = cfg.getString("password");
        MySQL.database = cfg.getString("database");
        MySQL.host = cfg.getString("host");
        MySQL.port = cfg.getString("port");
    }


}
