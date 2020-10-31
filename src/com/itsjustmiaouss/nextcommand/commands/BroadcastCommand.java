package com.itsjustmiaouss.nextcommand.commands;


import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {

	private static final Main main = Main.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


		if (!Utils.hasPermission(sender, "nextcommand.broadcast")) return false;

		if (args.length == 0) {
			sender.sendMessage(main.error_prefix + "§7/broadcast <message>");
			return true;
		}

		if (args.length >= 1) {
			StringBuilder sb = new StringBuilder();
			for (String arg : args) {
				sb.append(arg + " ");
			}
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("broadcastcommand.broadcast").replaceAll("&", "§").replace("{MESSAGE}", sb)));
		}

		return false;
	}
}
