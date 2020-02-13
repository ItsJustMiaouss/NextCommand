package com.itsjustmiaouss.nextcommand.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.itsjustmiaouss.nextcommand.Main;

public class FeedCommand implements CommandExecutor {
	
	private Main main;

	public FeedCommand(Main main) {
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
		int maxFoodLevel = 20;
		float saturation = 0.6F;
		
		if(args.length == 0) {
			if(!p.hasPermission("nextcommand.feed")) {
				p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
				return true;
			}
				
				if(p.getFoodLevel() < maxFoodLevel) {
					p.setFoodLevel(maxFoodLevel);
					p.setSaturation(saturation);
					p.sendMessage(main.prefix + main.getConfig().getString("feedcommand.fed").replaceAll("&", "§"));
					return true;
				} else {
					p.sendMessage(main.prefixerror + main.getConfig().getString("feedcommand.no-fed").replaceAll("&", "§"));
					return true;
				}
		}
		
		if(args.length >= 1) {
			if(!p.hasPermission("nextcommand.feed.other")) {
			p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
			return true;
			}
			
				if(getOfflinePlayer(args[0]) == null) {
					p.sendMessage(main.prefixerror + main.getConfig().getString("player-not-found").replaceAll("&", "§"));
					return true;
				}
				
				Player t = Bukkit.getPlayer(args[0]);
				if(t.getFoodLevel() < maxFoodLevel) {
					t.setFoodLevel(maxFoodLevel);
					t.setSaturation(saturation);
					t.sendMessage(main.prefix + main.getConfig().getString("feedcommand.fed").replaceAll("&", "§").replace("{PLAYER}", p.getPlayer().getName()));
					p.sendMessage(main.prefix + main.getConfig().getString("feedcommand.fed-target").replaceAll("&", "§").replace("{PLAYER}", t.getPlayer().getName()));
					return true;
				} else {
					p.sendMessage(main.prefixerror + main.getConfig().getString("feedcommand.no-fed-target").replaceAll("&", "§").replace("{PLAYER}", t.getPlayer().getName()));
					return true;
				}
		}
		
		return false;
	}

}
