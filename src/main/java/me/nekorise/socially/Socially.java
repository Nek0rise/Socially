package me.nekorise.socially;

import me.nekorise.socially.commands.MessageCommand;
import me.nekorise.socially.commands.SociallyCommand;
import me.nekorise.socially.commands.SociallyTabCompleter;
import me.nekorise.socially.config.ConfigManager;
import me.nekorise.socially.events.*;
import me.nekorise.socially.utils.ChatBubbleManager;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Socially extends JavaPlugin {
    private static Socially instance;

    @Override
    public void onEnable() {
        instance = this;

        ConfigManager.loadConfig();
        ChatBubbleManager.initialize();
        registerCommands();
        registerTabCompleters();
        registerEvents();
        //registerBStats();
    }

    @Override
    public void onDisable()
    {
        OnChatBubble.deleteAllBubblesOnStop();
    }

    private void registerCommands() {
        getCommand("socially").setExecutor(new SociallyCommand());
        getCommand("msg").setExecutor(new MessageCommand());
    }
    
    private void registerTabCompleters() {
        getCommand("socially").setTabCompleter(new SociallyTabCompleter());
    }
    
    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new OnAsyncChat(), instance);
        pm.registerEvents(new OnQuitJoin(), instance);
        pm.registerEvents(new OnChatBubble(), instance);
        pm.registerEvents(new OnHandshake(), instance);
        pm.registerEvents(new OnChatBubbleRemove(), instance);
    }

    private void registerBStats() {
        Metrics metrics = new Metrics(instance, 28849);
    }
    
    public static Socially getInstance() {
        return instance;
    }
}

