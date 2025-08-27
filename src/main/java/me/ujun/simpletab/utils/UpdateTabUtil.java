package me.ujun.simpletab.utils;

import me.ujun.simpletab.SimpleTab;
import me.ujun.simpletab.config.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import me.ujun.absolutevanish.api.AbsoluteVanishAPI;

public class UpdateTabUtil {

    public static void sendTabHeaderFooter(Player player) {

        String header = ConfigHandler.header;
        String footer = ConfigHandler.footer;

        header = replacePlaceHolders(header, player);
        footer = replacePlaceHolders(footer, player);

     player.setPlayerListHeaderFooter(header, footer);
    }


    public static String replacePlaceHolders(String input, Player player) {
        int ping = player.getPing();
        int online = Bukkit.getOnlinePlayers().size();
        String tps = String.format("%.2f", Math.min(Bukkit.getServer().getTPS()[0], 20.0));

        if (SimpleTab.isAbsoluteVanishEnabled) {
            online = exceptVanishedPlayers(online);
        }

        input = input.replace("%ping%", String.valueOf(ping));
        input = input.replace("%online%", String.valueOf(online));
        input = input.replace("%tps%", tps);

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
