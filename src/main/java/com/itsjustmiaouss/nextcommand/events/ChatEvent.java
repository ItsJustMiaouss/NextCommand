package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permissions;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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

        if(!configManager.getBoolean("chat-event.allow-custom-chat-colors")) return;

        if(permissionsManager.hasPermission(player, Permissions.NEXTCOMMAND_CHATCOLOR)) {
            message = message.replaceAll("&", "§");
            event.setMessage(message);
        }
    }

}
