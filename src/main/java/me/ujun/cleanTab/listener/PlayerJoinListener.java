package me.ujun.cleanTab.listener;

import me.ujun.cleanTab.util.UpdateTabUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final UpdateTabUtil tabUtil;

    public PlayerJoinListener(UpdateTabUtil tabUtil) {
        this.tabUtil = tabUtil;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        tabUtil.sendTabHeaderFooter(player);
    }
}
