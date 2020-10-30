package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class PlayerEvent implements Listener {
	
	private final Main main;

	public PlayerEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if(main.getConfig().getBoolean("join-event.enable-join-message")) {
			e.setJoinMessage(main.getConfig().getString("join-event.join-message").replaceAll("&", "§").replace("{PLAYER}", e.getPlayer().getName()));
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if(main.getConfig().getBoolean("join-event.enable-quit-message")) {
			e.setQuitMessage(main.getConfig().getString("join-event.quit-message").replaceAll("&", "§").replace("{PLAYER}", e.getPlayer().getName()));
		}
	}
	
	@EventHandler
	public void onPlayerHit(EntityDamageEvent e) {
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(main.godPlayer.contains(p)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerExecuteCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		List<String> commands = main.getConfig().getStringList("commandprotection.protection-list");
		
		if(main.getConfig().getBoolean("commandprotection.enable-protection")) {
			for(String str : commands) {
				if (e.getMessage().equalsIgnoreCase("/" + str)) {

					if (!p.hasPermission("nextcommand.bypassprotection")) {
						e.setCancelled(true);
						p.sendMessage(Utils.getErrorString("commandprotection.protection-message", main));
						return;
					}

				}
			}
		}
	}
}
