package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.getErrorString("console-no-player"));
			return true;
		}

		Player player = (Player) sender;

		if (args.length == 0) {

			if (!Utils.hasPermission(player, "nextcommand.fly")) return false;

			if (player.getAllowFlight()) {
				player.setAllowFlight(false);
				player.setFlying(false);
				player.sendMessage(Utils.getString("flycommand.fly-disabled"));
			} else {
				player.setAllowFlight(true);
				player.setFlying(true);
				player.sendMessage(Utils.getString("flycommand.fly-enabled"));
			}

		}
		
		if(args.length >= 1) {

			if (!Utils.hasPermission(player, "nextcommand.fly.other")) return false;

			if (!Utils.isOfflinePlayer(args[0], player)) return false;

			Player target = Bukkit.getPlayer(args[0]);
			if (target.getAllowFlight()) {
				target.setAllowFlight(false);
				target.setFlying(false);
				target.sendMessage(Utils.getString("flycommand.fly-disabled"));
				player.sendMessage(Utils.getString("flycommand.fly-disabled-sender").replace("{PLAYER}", target.getPlayer().getName()));
			} else {
				target.setAllowFlight(true);
				target.setFlying(true);
				target.sendMessage(Utils.getString("flycommand.fly-enabled"));
				player.sendMessage(Utils.getString("flycommand.fly-enabled-sender").replace("{PLAYER}", target.getPlayer().getName()));
			}

		}
		
		return false;
	}

}
