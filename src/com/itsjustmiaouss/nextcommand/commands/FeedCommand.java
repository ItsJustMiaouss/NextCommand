package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.getErrorString("console-no-player"));
			return true;
		}

		Player player = (Player) sender;

		int maxFoodLevel = 20;
		float saturation = 0.6F;

		if (args.length == 0) {

			if (!Utils.hasPermission(player, "nextcommand.feed")) return false;

			if (player.getFoodLevel() < maxFoodLevel) {
				player.setFoodLevel(maxFoodLevel);
				player.setSaturation(saturation);
				player.sendMessage(Utils.getString("feedcommand.fed"));
			} else {
				player.sendMessage(Utils.getErrorString("feedcommand.no-fed"));
			}

		}
		
		if(args.length >= 1) {

			if (!Utils.hasPermission(player, "nextcommand.feed.other")) return false;

			if (!Utils.isOfflinePlayer(args[0], player)) return false;

			Player target = Bukkit.getPlayer(args[0]);

			if (target.getFoodLevel() < maxFoodLevel) {
				target.setFoodLevel(maxFoodLevel);
				target.setSaturation(saturation);
				target.sendMessage(Utils.getString("feedcommand.fed").replaceAll("&", "§").replace("{PLAYER}", player.getPlayer().getName()));
				player.sendMessage(Utils.getString("eedcommand.fed-target").replaceAll("&", "§").replace("{PLAYER}", target.getPlayer().getName()));
			} else {
				player.sendMessage(Utils.getErrorString("feedcommand.no-fed-target").replaceAll("&", "§").replace("{PLAYER}", target.getPlayer().getName()));
			}

		}
		
		return false;
	}

}
