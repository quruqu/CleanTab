package me.ujun.simpletab;

import me.ujun.simpletab.commands.ReloadCMD;
import me.ujun.simpletab.config.ConfigHandler;
import me.ujun.simpletab.listeners.PlayerJoinListener;
import me.ujun.simpletab.utils.UpdateTabUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class SimpleTab extends JavaPlugin {
    public static boolean isAbsoluteVanishEnabled = false;


    @Override
    public void onEnable() {
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
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    private void registerCommands() {
        this.getCommand("simpletab-reload").setExecutor(new ReloadCMD());
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    UpdateTabUtil.sendTabHeaderFooter(p);
                }
            }
        }.runTaskTimer(this, 0L, 20L);
    }
}


