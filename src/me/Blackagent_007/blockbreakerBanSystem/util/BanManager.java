package me.Blackagent_007.blockbreakerBanSystem.util;

import me.Blackagent_007.blockbreakerBanSystem.MySQL.MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanManager {

    public static void ban(String uuid, String playername, String reason, int seconds) {
        long end = 0;
        if(seconds == -1) {
            end = -1;
        }
        long current = System.currentTimeMillis();
        long millis = seconds*1000;
        end = current + millis;
        MySQL.update("INSERT INTO BannedPlayers (Spielername, UUID, Ende, Grund) VALUES ('"+playername+"','"+uuid+"','"+end+"','"+reason+"')");
        if(Bukkit.getPlayer(playername) != null) {
            Bukkit.getPlayer(playername).kickPlayer("§cDu wurdest vom Server gebannt!\n" +
            "\n" +
            "§3Grund: §e" + getReason(uuid) + "\n" +
            "\n" +
//            "§3Verbleibende Zeit: §e" + getRemainingTime(uuid) + "\n"
            "\n" +
            "§3 Du kannst §c§nkeinen§3 Entbannungsantrag stellen!");
        }
    }

    public static void unban(String uuid) {
        MySQL.update("DELETE FROM BannedPlayers WHERE UUID='"+uuid+"'");
    }

    public static boolean isBanned(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT Ende FROM BannedPlayers WHERE UUID='"+uuid+"'");
        try {
            while(rs.next()) {
                if(rs.getInt("Ende") == -1) {
                    return true;
                }
            }
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getReason(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='"+uuid+"'");
        try {
            while(rs.next()) {
                return rs.getString("Grund");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long getEnd(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='"+uuid+"'");
        try {
            while(rs.next()) {
                return rs.getLong("Ende");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getBannedPlayers() {
        List<String> list = new ArrayList<String>();
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers");
        try {
            while(rs.next()) {
                list.add(rs.getString("Spielername"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


//    public static String getRemainingTime(String uuid) {
//
//    }

}
