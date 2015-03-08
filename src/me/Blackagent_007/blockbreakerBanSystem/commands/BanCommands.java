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
                String unitString = args[2];

                String reason = "";
                for(int i = 3; i < args.length; i++) {
                    reason += args[i] + " ";
                }
                List<String> unitList = BanUnit.getUnitsAsString();
                if(unitList.contains(unitString.toLowerCase())) {
                    BanUnit unit = BanUnit.getUnit(unitString);
                    int seconds = value * unit.getToSecond();
                    BanManager.ban(getUUID(playername), playername, reason, seconds);
                    sender.sendMessage(plugin.prefix + "§7Du hast §e" + playername + " §7für §c" + value + unit.getName() + " §7gebannt!");
                    return true;
                }
                sender.sendMessage(plugin.prefix + "§cDiese <Einheit> existiert nich!");
                return true;
            }
            sender.sendMessage(plugin.prefix + "§c/tempban <Spieler> <Zahlenwert> <Einheit> <Grund>");
            return true;
        }

    if(cmd.getName().equalsIgnoreCase("check")) {
        if(args.length == 1) {
            if(args [0].equalsIgnoreCase("list")) {
                List<String> list = BanManager.getBannedPlayers();
                if(list.size() == 0) {
                    sender.sendMessage(plugin.prefix + "§cEs sind aktuell keine Spieler gebannt!");
                    return true;
                }
                for(String allBanned : BanManager.getBannedPlayers()) {
                    sender.sendMessage(plugin.prefix + "§e" + allBanned + " §7(Grund: §r" + BanManager.getReason(getUUID(allBanned)) + "§7)");
                }
                return true;
            }
            String playername = args[0];

            return true;
        }
        sender.sendMessage(plugin.prefix + "§c/check (list) <Spieler>");
        return true;
    }
    return true;
    }

    @SuppressWarnings("deprecation")
    private String getUUID(String playername) {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }

}
