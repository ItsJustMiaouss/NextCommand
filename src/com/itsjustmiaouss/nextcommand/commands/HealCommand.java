package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
	
	private final Main main;

	public HealCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.getErrorString("console-no-player", main));
			return true;
		}
		
		Player p =(Player)sender;
		int maxHealthLevel = 20;
		
		if(args.length == 0) {

			if (!Utils.hasPermission(p, "nextcommand.heal", main)) return false;

			if (p.getHealth() < maxHealthLevel) {
				p.setHealth(maxHealthLevel);
				p.sendMessage(Utils.getString("healcommand.healed", main));
			} else {
				p.sendMessage(Utils.getErrorString("healcommand.no-healed", main));
			}

		}
		
		if(args.length >= 1) {

			if (!Utils.hasPermission(p, "nextcommand.heal.other", main)) return false;

			if (!Utils.isOfflinePlayer(args[0], p, main)) return false;

			Player t = Bukkit.getPlayer(args[0]);

			if (t.getHealth() < maxHealthLevel) {
				t.setHealth(maxHealthLevel);
				t.sendMessage(Utils.getString("healcommand.healed", main).replace("{PLAYER}", p.getPlayer().getName()));
				p.sendMessage(Utils.getString("healcommand.healed-target", main).replace("{PLAYER}", t.getPlayer().getName()));
			} else {
				p.sendMessage(Utils.getErrorString("healcommand.no-healed-target", main).replace("{PLAYER}", t.getPlayer().getName()));
			}
		}
		
		return false;
	}

}
