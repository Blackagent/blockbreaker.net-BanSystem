package me.Blackagent_007.blockbreakerBanSystem.listeners;


import me.Blackagent_007.blockbreakerBanSystem.Main;
import me.Blackagent_007.blockbreakerBanSystem.util.BanManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(BanManager.isBanned(p.getUniqueId().toString())) {
            BanManager.unban(p.getUniqueId().toString());
        }
    }

}
