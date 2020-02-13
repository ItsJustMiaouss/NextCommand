package com.itsjustmiaouss.nextcommand.commands;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.itsjustmiaouss.nextcommand.Main;

import net.md_5.bungee.api.ChatColor;

public class BroadcastCommand implements CommandExecutor {
	
	private Main main;

	public BroadcastCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		

		if(!(sender instanceof Player)) {
			sender.sendMessage(main.prefixerror + main.getConfig().getString("console-no-player").replaceAll("&", "§"));
			return true;
		}
		
		Player p =(Player)sender;
		
		if(!p.hasPermission("nextcommand.broadcast")) {
			p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
			return true;
		}
		
		if(args.length == 0) {
			p.sendMessage(main.prefixerror + "§7/broadcast <message>");
			return true;
		}
		
		if(args.length >= 1) {
			StringBuilder sb = new StringBuilder();
			for(String arg : args) {
				sb.append(arg + " ");
			}
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("broadcastcommand.broadcast").replaceAll("&", "§").replace("{MESSAGE}", sb)));
			return true;
		}
		
		return false;
	}
}
