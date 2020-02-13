package com.itsjustmiaouss.nextcommand.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.itsjustmiaouss.nextcommand.Main;

public class HealCommand implements CommandExecutor {
	
	private Main main;

	public HealCommand(Main main) {
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
		int maxHealthLevel = 20;
		
		if(args.length == 0) {
			if(!p.hasPermission("nextcommand.feed")) {
				p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
				return true;
			}
				
				if(p.getHealth() < maxHealthLevel) {
					p.setHealth(maxHealthLevel);
					p.sendMessage(main.prefix + main.getConfig().getString("healcommand.healed").replaceAll("&", "§"));
					return true;
				} else {
					p.sendMessage(main.prefixerror + main.getConfig().getString("healcommand.no-healed").replaceAll("&", "§"));
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
				if(t.getHealth() < maxHealthLevel) {
					t.setHealth(maxHealthLevel);
					t.sendMessage(main.prefix + main.getConfig().getString("healcommand.healed").replaceAll("&", "§").replace("{PLAYER}", p.getPlayer().getName()));
					p.sendMessage(main.prefix + main.getConfig().getString("healcommand.healed-target").replaceAll("&", "§").replace("{PLAYER}", t.getPlayer().getName()));
					return true;
				} else {
					p.sendMessage(main.prefixerror + main.getConfig().getString("healcommand.no-healed-target").replaceAll("&", "§").replace("{PLAYER}", t.getPlayer().getName()));
					return true;
				}
		}
		
		return false;
	}

}
