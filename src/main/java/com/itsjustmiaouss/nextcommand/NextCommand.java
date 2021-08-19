package com.itsjustmiaouss.nextcommand;

import com.itsjustmiaouss.nextcommand.commands.*;
import com.itsjustmiaouss.nextcommand.events.ChatEvent;
import com.itsjustmiaouss.nextcommand.events.EntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NextCommand extends JavaPlugin {

    private static NextCommand instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        initEvents();
        initCommands();
    }

    /**
     * Initialize plugins events
     */
    private void initEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ChatEvent(), this);
        pluginManager.registerEvents(new EntityEvent(), this);
    }

    /**
     * Initialize plugin commands
     * Do not forget to add them into plugin.yml
     */
    private void initCommands() {
        getCommand("nextcommand").setExecutor(new NextCDMCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("hat").setExecutor(new HatCommand());
    }

    /**
     * Get instance of the main class
     * @return Instance of the main class
     */
    public static NextCommand getInstance() {
        return instance;
    }
}
