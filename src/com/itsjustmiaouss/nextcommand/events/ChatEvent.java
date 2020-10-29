package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.itsjustmiaouss.nextcommand.Main;

public class ChatEvent implements Listener {
	
	private final Main main;

	public ChatEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String m = e.getMessage();
		if(Utils.hasPermission(p, "nextcommand.chatcolors")) {
			if(main.getConfig().getBoolean("chatevent.allow-custom-colors")) {
				m = m.replaceAll("&", "§");
				e.setMessage(m);
			}
		}
		if(main.getConfig().getBoolean("chatevent.allow-custom-format")) {
			e.setFormat(main.getConfig().getString("chatevent.custom-format").replaceAll("&", "§").replace("{PLAYER}", "%1$s").replace("{MESSAGE}", "%2$s")); // %1$s » %2$s
		}
	}
}
