package me.ujun.cleanTab.command;

import me.ujun.cleanTab.config.ConfigHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        sender.sendMessage("§asuccessfully reload the config.");
        ConfigHandler.getInstance().loadConfig();
        return true;
    }
}
