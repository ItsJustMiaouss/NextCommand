package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
	
	private final Main main;

	public FeedCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.getErrorString("console-no-player", main));
			return true;
		}
		
		Player p =(Player)sender;
		int maxFoodLevel = 20;
		float saturation = 0.6F;
		
		if(args.length == 0) {

			if (!Utils.hasPermission(p, "nextcommand.feed", main)) return false;

			if (p.getFoodLevel() < maxFoodLevel) {
				p.setFoodLevel(maxFoodLevel);
				p.setSaturation(saturation);
				p.sendMessage(Utils.getString("feedcommand.fed", main));
			} else {
				p.sendMessage(Utils.getErrorString("feedcommand.no-fed", main));
			}

		}
		
		if(args.length >= 1) {

			if (!Utils.hasPermission(p, "nextcommand.feed.other", main)) return false;

			if (!Utils.isOfflinePlayer(args[0], p, main)) return false;

			Player t = Bukkit.getPlayer(args[0]);

			if (t.getFoodLevel() < maxFoodLevel) {
				t.setFoodLevel(maxFoodLevel);
				t.setSaturation(saturation);
				t.sendMessage(Utils.getString("feedcommand.fed", main).replaceAll("&", "§").replace("{PLAYER}", p.getPlayer().getName()));
				p.sendMessage(Utils.getString("eedcommand.fed-target", main).replaceAll("&", "§").replace("{PLAYER}", t.getPlayer().getName()));
			} else {
				p.sendMessage(Utils.getErrorString("feedcommand.no-fed-target", main).replaceAll("&", "§").replace("{PLAYER}", t.getPlayer().getName()));
			}

		}
		
		return false;
	}

}
