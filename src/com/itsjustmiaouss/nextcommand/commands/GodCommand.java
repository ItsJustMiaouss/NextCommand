package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {
	
	private final Main main;

	public GodCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.getErrorString("console-no-player", main));
			return true;
		}
		
		
		Player p =(Player)sender;
		
		if(args.length == 0) {

			if (!Utils.hasPermission(p, "nextcommand.god", main)) return false;

			if (main.godPlayer.contains(p)) {
				main.godPlayer.remove(p);
				p.sendMessage(Utils.getString("godcommand.god-deactivated", main));
			} else {
				main.godPlayer.add(p);
				p.sendMessage(Utils.getString("godcommand.god-activated", main));
			}

		}
		
		if(args.length >= 1) {

			if (!Utils.hasPermission(p, "nextcommand.god.other", main)) return false;

			if (!Utils.isOfflinePlayer(args[0], p, main)) return false;

			Player t = Bukkit.getPlayer(args[0]);
			if (main.godPlayer.contains(t)) {
				main.godPlayer.remove(t);
				t.sendMessage(Utils.getString("godcommand.god-deactivated", main));
				p.sendMessage(Utils.getString("godcommand.god-deactivated-sender", main).replace("{PLAYER}", t.getPlayer().getName()));
			} else {
				main.godPlayer.add(t);
				t.sendMessage(Utils.getString("godcommand.god-activated", main).replaceAll("&", "§"));
				p.sendMessage(Utils.getString("godcommand.god-activated-sender", main).replaceAll("&", "§").replace("{PLAYER}", t.getPlayer().getName()));
			}
			return true;

		}
		
		return false;
	}

}
