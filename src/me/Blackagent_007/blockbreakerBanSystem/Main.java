package me.Blackagent_007.blockbreakerBanSystem;

/*
    Hier geht´s weiter im Video!!!!!!!!!!!!!!!!!!!!!!!!!
        http://youtu.be/XfmaLWE6p-I?t=18m20s   !!

        ===================================
        ----- Mögliche Fehlerstellen: -----
        => BanCommands Zeile 15
        => BanCommands Zeile 58 => ... = null == ???

 */
import me.Blackagent_007.blockbreakerBanSystem.MySQL.MySQL;
import me.Blackagent_007.blockbreakerBanSystem.commands.BanCommands;
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
        BanCommands banCMD = new BanCommands(this);
        getCommand("ban").setExecutor(banCMD);
        getCommand("tempban").setExecutor(banCMD);
        getCommand("check").setExecutor(banCMD);
        getCommand("unban").setExecutor(banCMD);
    }

    public String prefix;
    public static Main instance;

    public static Main getInstance() {
        return instance;
    }
}