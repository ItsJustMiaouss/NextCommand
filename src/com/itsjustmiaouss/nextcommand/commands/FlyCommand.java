package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
	
	private final Main main;

	public FlyCommand(Main main) {
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

			if (!Utils.hasPermission(p, "nextcommand.fly", main)) return false;

			if (p.getAllowFlight()) {
				p.setAllowFlight(false);
				p.setFlying(false);
				p.sendMessage(Utils.getString("flycommand.fly-disabled", main));
			} else {
				p.setAllowFlight(true);
				p.setFlying(true);
				p.sendMessage(Utils.getString("flycommand.fly-enabled", main));
			}

		}
		
		if(args.length >= 1) {

			if (!Utils.hasPermission(p, "nextcommand.fly.other", main)) return false;

			if (!Utils.isOfflinePlayer(args[0], p, main)) return false;

			Player t = Bukkit.getPlayer(args[0]);
			if (t.getAllowFlight()) {
				t.setAllowFlight(false);
				t.setFlying(false);
				t.sendMessage(Utils.getString("flycommand.fly-disabled", main));
				p.sendMessage(Utils.getString("flycommand.fly-disabled-sender", main).replace("{PLAYER}", t.getPlayer().getName()));
			} else {
				t.setAllowFlight(true);
				t.setFlying(true);
				t.sendMessage(Utils.getString("flycommand.fly-enabled", main));
				p.sendMessage(Utils.getString("flycommand.fly-enabled-sender", main).replace("{PLAYER}", t.getPlayer().getName()));
			}

		}
		
		return false;
	}

}
