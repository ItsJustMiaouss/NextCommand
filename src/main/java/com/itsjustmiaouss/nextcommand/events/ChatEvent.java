package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permissions;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(!ConfigManager.getBoolean("chat-event.allow-custom-chat-colors")) return;

        if(PermissionsManager.hasPermission(player, Permissions.NEXTCOMMAND_CHATCOLOR)) {
            message = message.replaceAll("&", "§");
            event.setMessage(message);
        }
    }

}
