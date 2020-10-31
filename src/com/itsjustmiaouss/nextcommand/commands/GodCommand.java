package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {

	private static final Main main = Main.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.getErrorString("console-no-player"));
			return true;
		}


		Player player = (Player) sender;

		if (args.length == 0) {

			if (!Utils.hasPermission(player, "nextcommand.god")) return false;

			if (main.godPlayer.contains(player)) {
				main.godPlayer.remove(player);
				player.sendMessage(Utils.getString("godcommand.god-deactivated"));
			} else {
				main.godPlayer.add(player);
				player.sendMessage(Utils.getString("godcommand.god-activated"));
			}

		}
		
		if(args.length >= 1) {

			if (!Utils.hasPermission(player, "nextcommand.god.other")) return false;

			if (!Utils.isOfflinePlayer(args[0], player)) return false;

			Player target = Bukkit.getPlayer(args[0]);
			if (main.godPlayer.contains(target)) {
				main.godPlayer.remove(target);
				target.sendMessage(Utils.getString("godcommand.god-deactivated"));
				player.sendMessage(Utils.getString("godcommand.god-deactivated-sender").replace("{PLAYER}", target.getPlayer().getName()));
			} else {
				main.godPlayer.add(target);
				target.sendMessage(Utils.getString("godcommand.god-activated").replaceAll("&", "§"));
				player.sendMessage(Utils.getString("godcommand.god-activated-sender").replaceAll("&", "§").replace("{PLAYER}", target.getPlayer().getName()));
			}
			return true;

		}
		
		return false;
	}

}
