package me.ujun.simpletab;

import me.ujun.simpletab.command.ReloadCMD;
import me.ujun.simpletab.config.ConfigHandler;
import me.ujun.simpletab.listener.PlayerJoinListener;
import me.ujun.simpletab.util.MsptUtil;
import me.ujun.simpletab.util.UpdateTabUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class SimpleTab extends JavaPlugin {
    public static boolean isAbsoluteVanishEnabled = false;
    private UpdateTabUtil tabUtil;
    private static SimpleTab instance;


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
        getCommand("simpletab-reload").setExecutor(new ReloadCMD());
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

    public static SimpleTab getInstance() {
        return instance;
    }

}


