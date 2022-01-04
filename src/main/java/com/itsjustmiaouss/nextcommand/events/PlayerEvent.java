package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.Style;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.function.Consumer;

public class PlayerEvent implements Listener {

    private final NextCommand nextCommand;

    public PlayerEvent(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!nextCommand.getConfigManager().getBoolean("join-event.enable-join-messages")) return;
        Player player = event.getPlayer();
        event.setJoinMessage(nextCommand.getConfigManager().getString(Prefix.NONE, "join-event.join-message")
                .replace("{player}", player.getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if(!nextCommand.getConfigManager().getBoolean("join-event.enable-quit-messages")) return;
        Player player = event.getPlayer();

        event.setQuitMessage(nextCommand.getConfigManager().getString(Prefix.NONE, "join-event.quit-message")
                .replace("{player}", player.getName()));
    }

}
