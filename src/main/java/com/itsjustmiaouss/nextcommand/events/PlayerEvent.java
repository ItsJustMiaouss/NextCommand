package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.Style;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
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

    /**
     * Set custom join and quit messages
     */
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

    /**
     * Cancel teleporting tasks if
     * the player is moving
     */
    @EventHandler
    public void onPlayerChangeBlock(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(event.hasExplicitlyChangedBlock()) {
            if (nextCommand.getTeleporting().containsKey(player)) {
                nextCommand.getTeleporting().remove(player);
                player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "teleportation-cancelled"));
            }
        }
    }

    /**
     * Sign edit feature
     */
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(event.getClickedBlock() == null) return;
        if(!nextCommand.getConfig().getBoolean("sign-edit.enabled")) return;
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if(nextCommand.getPermissionsManager().hasPermission(player, Permission.SIGN)) {
            if(event.getClickedBlock().getState() instanceof Sign sign) {
                if(nextCommand.getConfig().getBoolean("sign-edit.only-sneak") && !player.isSneaking()) return;

                if(player.getInventory().getItemInOffHand().getType() != Material.AIR || player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                    player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "sign-edit.cannot-edit"));
                    return;
                }

                player.openSign(sign);

            }
        }


    }

}
