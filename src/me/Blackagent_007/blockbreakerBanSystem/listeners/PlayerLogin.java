package me.Blackagent_007.blockbreakerBanSystem.listeners;


import me.Blackagent_007.blockbreakerBanSystem.Main;
import me.Blackagent_007.blockbreakerBanSystem.util.BanManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener{

    private Main plugin;

    public PlayerLogin(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        if(BanManager.isBanned(p.getUniqueId().toString())) {
            long current = System.currentTimeMillis();
            long end = BanManager.getEnd(p.getUniqueId().toString());
            if(current < end | end == -1) {
                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§cDu wurdest vom Server gebannt!\n" +
                        "\n" +
                        "§3Grund: §e" + BanManager.getReason(p.getUniqueId().toString()) + "\n" +
                        "\n" +
                        "§3Verbleibende Zeit: §e" + BanManager.getRemainingTime(p.getUniqueId().toString()) + "\n" +
                        "\n" +
                        "§3 Du kannst §c§nkeinen§3 Entbannungsantrag stellen!");
            }
        }

    }
}
