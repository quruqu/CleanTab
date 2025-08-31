package me.ujun.simpletab;

import me.ujun.simpletab.command.ReloadCMD;
import me.ujun.simpletab.config.ConfigHandler;
import me.ujun.simpletab.listener.PlayerJoinListener;
import me.ujun.simpletab.listener.TickListener;
import me.ujun.simpletab.util.MsptTracker;
import me.ujun.simpletab.util.UpdateTabUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class SimpleTab extends JavaPlugin {
    public static boolean isAbsoluteVanishEnabled = false;
    private MsptTracker tracker;
    private UpdateTabUtil tabUtil;


    @Override
    public void onEnable() {
        tracker = new MsptTracker();
        tabUtil = new UpdateTabUtil(tracker);
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
        getServer().getPluginManager().registerEvents(new TickListener(tracker), this);
    }

    private void registerCommands() {
        getCommand("simpletab-reload").setExecutor(new ReloadCMD());
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    tabUtil.sendTabHeaderFooter(p);
                }
            }
        }.runTaskTimer(this, 0L, 20L);
    }
}


