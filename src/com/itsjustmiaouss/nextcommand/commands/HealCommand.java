package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.getErrorString("console-no-player"));
			return true;
		}

		Player player = (Player) sender;

		int maxHealthLevel = 20;

		if (args.length == 0) {

			if (!Utils.hasPermission(player, "nextcommand.heal")) return false;

			if (player.getHealth() < maxHealthLevel) {
				player.setHealth(maxHealthLevel);
				player.sendMessage(Utils.getString("healcommand.healed"));
			} else {
				player.sendMessage(Utils.getErrorString("healcommand.no-healed"));
			}

		}
		
		if(args.length >= 1) {

			if (!Utils.hasPermission(player, "nextcommand.heal.other")) return false;

			if (!Utils.isOfflinePlayer(args[0], player)) return false;

			Player target = Bukkit.getPlayer(args[0]);

			if (target.getHealth() < maxHealthLevel) {
				target.setHealth(maxHealthLevel);
				target.sendMessage(Utils.getString("healcommand.healed").replace("{PLAYER}", player.getPlayer().getName()));
				player.sendMessage(Utils.getString("healcommand.healed-target").replace("{PLAYER}", target.getPlayer().getName()));
			} else {
				player.sendMessage(Utils.getErrorString("healcommand.no-healed-target").replace("{PLAYER}", target.getPlayer().getName()));
			}
		}
		
		return false;
	}

}
