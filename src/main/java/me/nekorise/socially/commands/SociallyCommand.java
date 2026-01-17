package me.nekorise.socially.commands;

import me.nekorise.socially.utils.MMessage;
import me.nekorise.socially.config.ConfigManager;
import me.nekorise.socially.config.LanguageConfigStorage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SociallyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission("socially.reload")) { 
            return false; 
        }
        
        if ((args.length < 1) || !args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(MMessage.applyColor(LanguageConfigStorage.reloadUsage));
            return false;
        }
        
        ConfigManager.loadConfig();
        sender.sendMessage(MMessage.applyColor(LanguageConfigStorage.reloadDone));
        return false;
    }
}

