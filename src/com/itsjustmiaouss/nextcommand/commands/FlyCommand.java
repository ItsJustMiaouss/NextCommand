package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.itsjustmiaouss.nextcommand.Main;

public class FlyCommand implements CommandExecutor {
	
	private final Main main;

	public FlyCommand(Main main) {
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
			if(!Utils.hasPermission(p, "nextcommand.fly")) {
				p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
				return true;
			}
			
				if(p.getAllowFlight()) {
					p.setAllowFlight(false);
					p.setFlying(false);
					p.sendMessage(main.prefix + main.getConfig().getString("flycommand.fly-disabled").replaceAll("&", "§"));
					return true;
				} else {
					p.setAllowFlight(true);
					p.setFlying(true);
					p.sendMessage(main.prefix + main.getConfig().getString("flycommand.fly-enabled").replaceAll("&", "§"));
					return true;
				}
		}
		
		if(args.length >= 1) {
			if(!Utils.hasPermission(p, "nextcommand.fly.other")) {
				
			p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
			return true;
			}
			
				if(getOfflinePlayer(args[0]) == null) {
					p.sendMessage(main.prefixerror + main.getConfig().getString("player-not-found").replaceAll("&", "§"));
					return true;
				}
				
				Player t = Bukkit.getPlayer(args[0]);
				if(t.getAllowFlight()) {
					t.setAllowFlight(false);
					t.setFlying(false);
					t.sendMessage(main.prefix + main.getConfig().getString("flycommand.fly-disabled").replaceAll("&", "§"));
					p.sendMessage(main.prefix + main.getConfig().getString("flycommand.fly-disabled-sender").replaceAll("&", "§").replace("{PLAYER}", t.getPlayer().getName()));
					return true;
				} else {
					t.setAllowFlight(true);
					t.setFlying(true);
					t.sendMessage(main.prefix + main.getConfig().getString("flycommand.fly-enabled").replaceAll("&", "§"));
					p.sendMessage(main.prefix + main.getConfig().getString("flycommand.fly-enabled-sender").replaceAll("&", "§").replace("{PLAYER}", t.getPlayer().getName()));
					return true;
				}
		}
		
		return false;
	}

}
