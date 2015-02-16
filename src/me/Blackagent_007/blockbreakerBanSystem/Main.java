package me.Blackagent_007.blockbreakerBanSystem;

/*
    Hier geht´s weiter im Video!!!!!!!!!!!!!!!!!!!!!!!!!
        http://youtu.be/UJN3tTkRjVs?t=17m43s

 */
import me.Blackagent_007.blockbreakerBanSystem.MySQL.MySQL;
import me.Blackagent_007.blockbreakerBanSystem.util.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    public void onEnable() {
        instance = this;
        registerEvents();
        registerCommands();
        FileManager.setStandardConfig();
        FileManager.setStandardMySQL();
        FileManager.readConfig();
        FileManager.readMySQL();
        MySQL.connect();
        MySQL.createTable();
        Bukkit.getConsoleSender().sendMessage(prefix + "Plugin aktiviert");
    }

    public void onDisable() {
        MySQL.close();
    }

    private void registerEvents() {

    }

    private void registerCommands() {

    }

    public String prefix;
    public static Main instance;

    public static Main getInstance() {
        return instance;
    }
}