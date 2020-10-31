package com.itsjustmiaouss.nextcommand.events;

import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.commands.AfkCommand;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class PlayerEvent implements Listener {

	private final Main main = Main.getInstance();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (main.getConfig().getBoolean("join-event.enable-join-message")) {
			event.setJoinMessage(main.getConfig().getString("join-event.join-message").replaceAll("&", "§").replace("{PLAYER}", event.getPlayer().getName()));
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (main.getConfig().getBoolean("join-event.enable-quit-message")) {
			event.setQuitMessage(main.getConfig().getString("join-event.quit-message").replaceAll("&", "§").replace("{PLAYER}", event.getPlayer().getName()));
		}
	}

	@EventHandler
	public void onPlayerHit(EntityDamageEvent event) {

		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (main.godPlayer.contains(player)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (main.afkPlayer.contains(player)) {
			if (main.getConfig().getBoolean("afkcommand.disable-move")) {
				main.afkPlayer.remove(player);
				AfkCommand.removeAFK(player);
			}
		}
	}

	@EventHandler
	public void onPlayerExecuteCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		List<String> commands = main.getConfig().getStringList("commandprotection.protection-list");

		if (main.getConfig().getBoolean("commandprotection.enable-protection")) {
			for (String str : commands) {
				if (event.getMessage().equalsIgnoreCase("/" + str)) {

					if (!Utils.hasPermission(player, "nextcommand.bypassprotection")) {
						event.setCancelled(true);
						player.sendMessage(Utils.getErrorString("commandprotection.protection-message"));
						return;
					}

				}
			}
		}
	}
}
