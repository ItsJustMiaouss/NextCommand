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

	private static final Main main = Main.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.getErrorString("console-no-player"));
			return true;
		}

		Player player = (Player) sender;

		if (args.length == 0) {

			if (!Utils.hasPermission(player, "nextcommand.spawn")) return false;

			String world = main.getConfig().getString("spawncommand.location.world");
			double x = main.getConfig().getDouble("spawncommand.location.x");
			double y = main.getConfig().getDouble("spawncommand.location.y");
			double z = main.getConfig().getDouble("spawncommand.location.z");
			float yaw = (float) main.getConfig().getDouble("spawncommand.location.yaw");
			float pitch = (float) main.getConfig().getDouble("spawncommand.location.pitch");
			if (world == "") {
				player.sendMessage(Utils.getErrorString("spawncommand.no-spawn"));
				return true;
			}
			try {
				player.sendMessage(Utils.getString("spawncommand.teleportation"));
				player.teleport(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
			} catch (Exception e) {
				player.sendMessage(Utils.getErrorString("exception").replace("{ERROR}", e.getMessage()));
			}

		}

		if(args.length >= 1) {

			if (!Utils.hasPermission(player, "nextcommand.spawn.set")) return false;

			main.getConfig().set("spawncommand.location.world", player.getWorld().getName());
			main.getConfig().set("spawncommand.location.x", player.getLocation().getX());
			main.getConfig().set("spawncommand.location.y", player.getLocation().getY());
			main.getConfig().set("spawncommand.location.z", player.getLocation().getZ());
			main.getConfig().set("spawncommand.location.yaw", player.getLocation().getYaw());
			main.getConfig().set("spawncommand.location.pitch", player.getLocation().getPitch());
			main.saveConfig();
			player.sendMessage(Utils.getString("spawncommand.spawn-set"));
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
