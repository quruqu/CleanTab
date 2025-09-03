package me.ujun.cleanTab.util;

import me.ujun.cleanTab.CleanTab;
import me.ujun.cleanTab.config.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import me.ujun.absolutevanish.api.AbsoluteVanishAPI;

public class UpdateTabUtil {



    public void sendTabHeaderFooter(Player player) {

        String header = ConfigHandler.header;
        String footer = ConfigHandler.footer;

        header = replacePlaceHolders(header, player);
        footer = replacePlaceHolders(footer, player);

        player.setPlayerListHeaderFooter(header, footer);
    }


    private String replacePlaceHolders(String input, Player player) {
        int ping = player.getPing();
        int online = Bukkit.getOnlinePlayers().size();
        int maxOnline = Bukkit.getMaxPlayers();
        String tps = String.format("%.2f", Math.min(Bukkit.getServer().getTPS()[0], 20.0));
        String mspt = String.valueOf((int) CleanTab.msptAvg20);

        if (CleanTab.isAbsoluteVanishEnabled) {
            online = exceptVanishedPlayers(online);
        }

        input = input.replace("%ping%", String.valueOf(ping));
        input = input.replace("%online%", String.valueOf(online));
        input = input.replace("%max_online%", String.valueOf(maxOnline));
        input = input.replace("%tps%", tps);
        input = input.replace("%mspt%", mspt);

        input = ChatColor.translateAlternateColorCodes('&', input);

        return input;
    }

    public static int exceptVanishedPlayers(int onlinePlayer) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (AbsoluteVanishAPI.isVanished(p)) {
                onlinePlayer--;
            }
        }

        return onlinePlayer;
    }
}
