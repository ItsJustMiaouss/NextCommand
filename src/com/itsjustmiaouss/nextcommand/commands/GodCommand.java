package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.itsjustmiaouss.nextcommand.Main;

public class GodCommand implements CommandExecutor {
	
	private final Main main;

	public GodCommand(Main main) {
		this.main = main;
	}
	
	public OfflinePlayer getOfflinePlayer(String name) {
		for(OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			if(player.getName().equals(name)) return player;
		}
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(main.prefixerror + main.getConfig().getString("console-no-player").replaceAll("&", "§"));
			return true;
		}
		
		
		Player p =(Player)sender;
		
		if(args.length == 0) {
			if(!Utils.hasPermission(p, "nextcommand.god")) {
				p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
				return true;
			}
			
				if(main.godPlayer.contains(p)) {
					main.godPlayer.remove(p);
					p.sendMessage(main.prefix + main.getConfig().getString("godcommand.god-deactivated").replaceAll("&", "§"));
					return true;
				} else {
					main.godPlayer.add(p);
					p.sendMessage(main.prefix + main.getConfig().getString("godcommand.god-activated").replaceAll("&", "§"));
					return true;
				}
		}
		
		if(args.length >= 1) {
			if(!Utils.hasPermission(p, "nextcommand.god.other")) {
			p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
			return true;
			}
			
				if(getOfflinePlayer(args[0]) == null) {
					p.sendMessage(main.prefixerror + main.getConfig().getString("player-not-found").replaceAll("&", "§"));
					return true;
				}
				
				Player t = Bukkit.getPlayer(args[0]);
				if(main.godPlayer.contains(t)) {
					main.godPlayer.remove(t);
					t.sendMessage(main.prefix + main.getConfig().getString("godcommand.god-deactivated").replaceAll("&", "§"));
					p.sendMessage(main.prefix + main.getConfig().getString("godcommand.god-deactivated-sender").replaceAll("&", "§").replace("{PLAYER}", t.getPlayer().getName()));
					return true;
				} else {
					main.godPlayer.add(t);
					t.sendMessage(main.prefix + main.getConfig().getString("godcommand.god-activated").replaceAll("&", "§"));
					p.sendMessage(main.prefix + main.getConfig().getString("godcommand.god-activated-sender").replaceAll("&", "§").replace("{PLAYER}", t.getPlayer().getName()));
					return true;
				}
		}
		
		return false;
	}

}
