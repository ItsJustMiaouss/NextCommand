package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permissions;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class ChatEvent implements Listener {

    private NextCommand nextCommand;
    private ConfigManager configManager;
    private PermissionsManager permissionsManager;

    public ChatEvent(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
        this.configManager = nextCommand.getConfigManager();
        this.permissionsManager = nextCommand.getPermissionsManager();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(!message.contains("&")) return;
        if(!configManager.getBoolean("chat-event.allow-custom-chat-colors")) return;

        if(permissionsManager.hasPermissionRaw(player, Permissions.NEXTCOMMAND_CHATCOLOR)) {
            message = message.replaceAll("&", "§");
            event.setMessage(message);
        }
    }

    @EventHandler
    public void onPlayerExecuteCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        List<String> disabledCommands = nextCommand.getConfig().getStringList("command-event.protection-list");

        if(!configManager.getBoolean("command-event.enable-command-protection")) return;
        if(permissionsManager.hasPermissionRaw(player, Permissions.NEXTCOMMAND_BYPASS_COMMAND_PROTECTION)) return;

        for(String command : disabledCommands) {
            if(event.getMessage().equalsIgnoreCase("/" + command)) {
                event.setCancelled(true);
                player.sendMessage(configManager.getString(Prefixes.ERROR, "command-event.protection-message"));
            }
        }
    }

}
