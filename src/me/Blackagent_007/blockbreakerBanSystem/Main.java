package me.Blackagent_007.blockbreakerBanSystem;

/*
    Hier geht´s weiter im Video!!!!!!!!!!!!!!!!!!!!!!!!!
        http://youtu.be/tXzfdqJ2k4Y?t=15m26s   !!

 */
import me.Blackagent_007.blockbreakerBanSystem.MySQL.MySQL;
import me.Blackagent_007.blockbreakerBanSystem.util.BanManager;
import me.Blackagent_007.blockbreakerBanSystem.util.FileManager;
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

        @SuppressWarnings("deprecation")
        OfflinePlayer op = Bukkit.getOfflinePlayer("lusu007");
        boolean b = BanManager.isBanned(op.getUniqueId().toString());
        System.out.println(b);
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