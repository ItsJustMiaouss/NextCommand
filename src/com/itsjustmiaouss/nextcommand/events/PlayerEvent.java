package com.itsjustmiaouss.nextcommand.events;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.itsjustmiaouss.nextcommand.Main;

public class PlayerEvent implements Listener {
	
	private Main main;

	public PlayerEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if(main.getConfig().getBoolean("join-event.enable-join-message") == true) {
			e.setJoinMessage(main.getConfig().getString("join-event.join-message").replaceAll("&", "§").replace("{PLAYER}", e.getPlayer().getName()));
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if(main.getConfig().getBoolean("join-event.enable-quit-message") == true) {
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
		
		if(main.getConfig().getBoolean("commandprotection.enable-protection") == true) {	
			for(String str : commands) {
				if(e.getMessage().equalsIgnoreCase("/" + str)) {	
					if(!p.hasPermission("nextcommand.bypassprotection")) {
						e.setCancelled(true);
						p.sendMessage(main.prefixerror + main.getConfig().getString("commandprotection.protection-message").replaceAll("&", "§"));
						return;
					}
				}
			}
		}
	}
}
