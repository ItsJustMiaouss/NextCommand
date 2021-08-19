package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvent implements Listener {

    private NextCommand nextCommand;
    private ConfigManager config;

    public PlayerEvent(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
        this.config = nextCommand.getConfigManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!config.getBoolean("join-event.enable-join-messages")) return;
        Player player = event.getPlayer();

        event.setJoinMessage(config.getString(Prefixes.NONE, "join-event.join-message")
                .replace("{player}", player.getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if(!config.getBoolean("join-event.enable-quit-messages")) return;
        Player player = event.getPlayer();

        event.setQuitMessage(config.getString(Prefixes.NONE, "join-event.quit-message")
                .replace("{player}", player.getName()));
    }

}
