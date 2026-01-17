package me.nekorise.socially.events;

import me.nekorise.socially.config.LanguageConfigStorage;
import me.nekorise.socially.config.MainConfigStorage;
import me.nekorise.socially.utils.ChatStringFormatter;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnQuitJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!MainConfigStorage.isJoinMessageEnabled) {
            return; 
        }

        Player player = event.getPlayer();
        Component formattedMessage = ChatStringFormatter.getJoinQuitMessage(player, LanguageConfigStorage.joinMessage);
        event.joinMessage(formattedMessage);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (!MainConfigStorage.isQuitMessageEnabled) {
            return; 
        }

        Player player = event.getPlayer();
        Component formattedMessage = ChatStringFormatter.getJoinQuitMessage(player, LanguageConfigStorage.quitMessage);
        event.quitMessage(formattedMessage);
    }
}

