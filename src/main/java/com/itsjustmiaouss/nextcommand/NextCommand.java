package com.itsjustmiaouss.nextcommand;

import com.itsjustmiaouss.nextcommand.commands.*;
import com.itsjustmiaouss.nextcommand.events.ChatEvent;
import com.itsjustmiaouss.nextcommand.events.EntityEvent;
import com.itsjustmiaouss.nextcommand.events.PlayerEvent;
import com.itsjustmiaouss.nextcommand.utils.PlayerManager;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NextCommand extends JavaPlugin {

    private ConfigManager configManager;
    private PermissionsManager permissionsManager;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {

        permissionsManager = new PermissionsManager(this);
        playerManager = new PlayerManager(this);

        saveDefaultConfig();
        reload();

        initEvents();
        initCommands();
    }

    public void reload() {
        this.reloadConfig();

        configManager = new ConfigManager(this);
    }

    /**
     * Initialize plugins events
     */
    private void initEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ChatEvent(this), this);
        pluginManager.registerEvents(new EntityEvent(this), this);
        pluginManager.registerEvents(new PlayerEvent(this), this);
    }

    /**
     * Initialize plugin commands
     * Do not forget to add them into plugin.yml
     */
    private void initCommands() {
        getCommand("nextcommand").setExecutor(new NextCDMCommand(this));
        getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        getCommand("feed").setExecutor(new FeedCommand(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("hat").setExecutor(new HatCommand(this));
        getCommand("invsee").setExecutor(new InvseeCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public PermissionsManager getPermissionsManager() {
        return permissionsManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
