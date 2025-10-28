package plugins.excavations.listeners.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import plugins.excavations.managers.BalanceManager;
import plugins.excavations.util.Colorize;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!(e.getPlayer().hasPlayedBefore())) {
            BalanceManager.setBalance(e.getPlayer().getUniqueId(), 0);
            e.getPlayer().sendMessage(Colorize.colorize("&7Welcome"));
        }
    }

}
