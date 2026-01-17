package me.nekorise.socially.commands;

import me.nekorise.socially.utils.MMessage;
import me.nekorise.socially.config.LanguageConfigStorage;
import me.nekorise.socially.config.MainConfigStorage;
import me.nekorise.socially.utils.ChatStringFormatter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static me.nekorise.socially.utils.ChatStringFormatter.isContainsBlacklistedWords;

public class MessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission("socially.msg") || !(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        if (args.length < 2 || player.getName().equals(args[0])) {
            player.sendMessage(args.length < 2 ? MMessage.applyColor(LanguageConfigStorage.msgUsage) : MMessage.applyColor(LanguageConfigStorage.msgSelfSendError));
            return false;
        }

        Player recipient = Bukkit.getServer().getPlayer(args[0]);
        if (recipient == null) {
            player.sendMessage(MMessage.applyColor(LanguageConfigStorage.msgUserOffline));
            return false;
        }

        String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if (isContainsBlacklistedWords(message, player)) {
            sender.sendMessage(MMessage.applyColor(LanguageConfigStorage.badWordMessage));
            return false;
        }

        Component finalMessage = ChatStringFormatter.getPrivateMessage(player, recipient, message);
        recipient.sendMessage(finalMessage);
        recipient.playSound(recipient, MainConfigStorage.messageSound, 1.0f, 1.0f);
        player.sendMessage(finalMessage);
        return true;
    }
}

