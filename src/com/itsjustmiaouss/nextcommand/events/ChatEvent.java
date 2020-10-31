package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    private final Main main = Main.getInstance();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        String m = event.getMessage();

        if (p.hasPermission("nextcommand.chatcolors")) {
            if (main.getConfig().getBoolean("chatevent.allow-custom-colors")) {
                m = m.replaceAll("&", "§");
                event.setMessage(m);
            }
        }

        if (main.getConfig().getBoolean("chatevent.allow-custom-format")) {
            event.setFormat(main.getConfig().getString("chatevent.custom-format").replaceAll("&", "§").replace("{PLAYER}", "%1$s").replace("{MESSAGE}", "%2$s")); // %1$s » %2$s
        }
	}
}
