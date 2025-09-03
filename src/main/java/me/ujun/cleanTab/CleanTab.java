package me.ujun.cleanTab;

import me.ujun.cleanTab.command.ReloadCMD;
import me.ujun.cleanTab.config.ConfigHandler;
import me.ujun.cleanTab.listener.PlayerJoinListener;
import me.ujun.cleanTab.util.MsptUtil;
import me.ujun.cleanTab.util.UpdateTabUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class CleanTab extends JavaPlugin {
    public static boolean isAbsoluteVanishEnabled = false;
    private UpdateTabUtil tabUtil;
    private static CleanTab instance;


    @Override
    public void onEnable() {
        tabUtil = new UpdateTabUtil();
        saveDefaultConfig();
        ConfigHandler.init(this);

        if (Bukkit.getPluginManager().isPluginEnabled("AbsoluteVanish")) {
            isAbsoluteVanishEnabled = true;
        }

        registerListeners();
        registerCommands();
        run();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(tabUtil), this);
    }

    private void registerCommands() {
        getCommand("cleantab-reload").setExecutor(new ReloadCMD());
    }

    public static double msptAvg20;


    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    msptAvg20  = MsptUtil.averageMspt(20);
                    tabUtil.sendTabHeaderFooter(p);
                }
            }
        }.runTaskTimer(this, 0L, ConfigHandler.updateDuration);
    }

    public static CleanTab getInstance() {
        return instance;
    }
}


