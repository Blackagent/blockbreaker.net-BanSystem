package me.Blackagent_007.blockbreakerBanSystem;


import me.Blackagent_007.blockbreakerBanSystem.MySQL.MySQL;
import me.Blackagent_007.blockbreakerBanSystem.commands.BanCommands;
import me.Blackagent_007.blockbreakerBanSystem.listeners.JoinListener;
import me.Blackagent_007.blockbreakerBanSystem.listeners.PlayerLogin;
import me.Blackagent_007.blockbreakerBanSystem.util.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

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

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    public void onDisable() {
        MySQL.close();
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLogin(this), this);
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