package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class ChatEvent implements Listener {

    private final NextCommand nextCommand;

    public ChatEvent(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(!message.contains("&")) return;
        if(!nextCommand.getConfigManager().getBoolean("chat-event.allow-custom-chat-colors")) return;

        if(nextCommand.getPermissionsManager().hasPermissionRaw(player, Permission.NEXTCOMMAND_CHATCOLOR)) {
            message = message.replaceAll("&", "§");
            event.setMessage(message);
        }
    }

    @EventHandler
    public void onPlayerExecuteCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        List<String> disabledCommands = nextCommand.getConfig().getStringList("command-event.protection-list");

        if(!nextCommand.getConfigManager().getBoolean("command-event.enable-command-protection")) return;
        if(nextCommand.getPermissionsManager().hasPermissionRaw(player, Permission.NEXTCOMMAND_BYPASS_COMMAND_PROTECTION)) return;

        for(String command : disabledCommands) {
            if(event.getMessage().equalsIgnoreCase("/" + command)) {
                event.setCancelled(true);
                player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "command-event.protection-message"));
            }
        }
    }

}
