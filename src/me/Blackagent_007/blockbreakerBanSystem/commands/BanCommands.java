package me.Blackagent_007.blockbreakerBanSystem.commands;

import me.Blackagent_007.blockbreakerBanSystem.Main;
import me.Blackagent_007.blockbreakerBanSystem.util.BanManager;
import me.Blackagent_007.blockbreakerBanSystem.util.BanUnit;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class BanCommands implements CommandExecutor{

    private Main plugin;

    public BanCommands(Main main) {
        this.plugin = plugin;
    }

    /*
     *   Syntax: Spielername, UUID, Ende, Grund
     */

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("ban")) {
            if(args.length >= 2) {
                String playername = args[0];
                if (BanManager.isBanned(getUUID(playername))) {
                    sender.sendMessage(plugin.prefix + "§cDieser Spieler ist bereits gebannt!");
                }
                String reason = "";
                for(int i = 1; i < args.length; i++) {
                    reason += args[i] + " ";
                }
                BanManager.ban(getUUID(playername), playername, reason, -1);
                sender.sendMessage(plugin.prefix + "§Du hast §e" + playername + " §4PERMANENT §7gebannt!");
                return true;
            }
            sender.sendMessage(plugin.prefix + "§c/ban <Spieler> <Grund>");
            return true;

        }

        if(cmd.getName().equalsIgnoreCase("tempban")) {
            if(args.length >= 4) {
                String playername = args[0];
                if (BanManager.isBanned(getUUID(playername))) {
                    sender.sendMessage(plugin.prefix + "§cDieser Spieler ist bereits gebannt!");
                }
                int value;
                try {
                    value = Integer.valueOf(args[1]);
                } catch(NumberFormatException e) {
                    sender.sendMessage(plugin.prefix + "§c<Zahlenwert> muss eine Zahl sein!");
                    return true;
                }
                String unit = args[2];

                String reason = "";
                for(int i = 3; i < args.length; i++) {
                    reason += args[i] + " ";
                }
                List<String> unitList = BanUnit.getUnitsAsString();
                if(unitList.contains(unit.toLowerCase())) {
                    int seconds =   //HIIIIIIIIIIIIIIIIIEEEEEEEEEEEEEEEEEEEEERRRRRRRRR GEHTS WEITER
                    return true;
                }
                sender.sendMessage(plugin.prefix + "§cDiese Einheit existiert nich!");
                return true;
            }
            sender.sendMessage(plugin.prefix + "§c/tempban <Spieler> <Zahlenwert> <Einheit> <Grund>");
            return true;
        }


    return true;
    }

    private String getUUID(String playername) {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }

}
