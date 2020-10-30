package com.itsjustmiaouss.nextcommand.commands;

import com.google.common.collect.Lists;
import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SpawnCommand implements CommandExecutor, TabCompleter {
	
	private final Main main;

	public SpawnCommand(Main main) {
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

			if (!Utils.hasPermission(p, "nextcommand.spawn", main)) return false;

			String w = main.getConfig().getString("spawncommand.location.world");
			double x = main.getConfig().getDouble("spawncommand.location.x");
			double y = main.getConfig().getDouble("spawncommand.location.y");
			double z = main.getConfig().getDouble("spawncommand.location.z");
			float yaw = (float) main.getConfig().getDouble("spawncommand.location.yaw");
			float pitch = (float) main.getConfig().getDouble("spawncommand.location.pitch");
			if (w == "") {
				p.sendMessage(Utils.getErrorString("spawncommand.no-spawn", main));
				return true;
			}
			try {
				p.sendMessage(Utils.getString("spawncommand.teleportation", main));
				p.teleport(new Location(Bukkit.getWorld(w), x, y, z, yaw, pitch));
			} catch (Exception e) {
				p.sendMessage(Utils.getErrorString("exception", main).replace("{ERROR}", e.getMessage()));
			}

		}

		if(args.length >= 1) {

			if (!Utils.hasPermission(p, "nextcommand.spawn.set", main)) return false;

			main.getConfig().set("spawncommand.location.world", p.getWorld().getName());
			main.getConfig().set("spawncommand.location.x", p.getLocation().getX());
			main.getConfig().set("spawncommand.location.y", p.getLocation().getY());
			main.getConfig().set("spawncommand.location.z", p.getLocation().getZ());
			main.getConfig().set("spawncommand.location.yaw", p.getLocation().getYaw());
			main.getConfig().set("spawncommand.location.pitch", p.getLocation().getPitch());
			main.saveConfig();
			p.sendMessage(Utils.getString("spawncommand.spawn-set", main));
		}

		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> s1 = Arrays.asList("set");
		List<String> flist = Lists.newArrayList();
		if(args.length == 1) {
			for(String s : s1) {
				if(s.toLowerCase().startsWith(args[0].toLowerCase())) flist.add(s);
			}
			return flist;
		}
		return null;
	}

}
