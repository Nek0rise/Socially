package me.nekorise.socially.events;

import me.nekorise.socially.config.LanguageConfigStorage;
import me.nekorise.socially.config.MainConfigStorage;
import me.nekorise.socially.utils.ChatStringFormatter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class OnHandshake implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent event) {
        if (!MainConfigStorage.isHandshakeEnabled) {
            return;
        }
        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }

        Player player = event.getPlayer();
        Player targetPlayer = (Player) event.getRightClicked();
        if (!player.hasPermission("socially.handshake")) {
            return;
        }
        player.sendActionBar(ChatStringFormatter.getHandshakeMessage(LanguageConfigStorage.handshakeMessage, targetPlayer));
    }
}

