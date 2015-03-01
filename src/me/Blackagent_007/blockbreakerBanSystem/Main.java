package me.Blackagent_007.blockbreakerBanSystem;

/*
    Hier geht´s weiter im Video!!!!!!!!!!!!!!!!!!!!!!!!!
        https://www.youtube.com/watch?v=XfmaLWE6p-I   !!

        ===================================
        ----- Mögliche Fehlerstellen: -----
        => -

 */
import me.Blackagent_007.blockbreakerBanSystem.MySQL.MySQL;
import me.Blackagent_007.blockbreakerBanSystem.util.BanManager;
import me.Blackagent_007.blockbreakerBanSystem.util.FileManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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