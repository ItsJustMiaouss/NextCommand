package com.itsjustmiaouss.nextcommand.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.itsjustmiaouss.nextcommand.Main;

public class SpawnCommand implements CommandExecutor, TabCompleter {
	
	private Main main;

	public SpawnCommand(Main main) {
		this.main = main;
	}
	
	public OfflinePlayer getOfflinePlayer(String name) {
		for(OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			if(player.getName().equals(name)) return player;
		}
		return null;
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(main.prefixerror + main.getConfig().getString("console-no-player").replaceAll("&", "§"));
			return true;
		}
		
		Player p =(Player)sender;
		
		if(args.length == 0) {
			if(!p.hasPermission("nextcommand.spawn")) {
				p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
				return true;
			}
					String w = main.getConfig().getString("spawncommand.location.World");
					Double x = main.getConfig().getDouble("spawncommand.location.X");
					Double y = main.getConfig().getDouble("spawncommand.location.Y");
					Double z = main.getConfig().getDouble("spawncommand.location.Z");
					Float yaw = (float) main.getConfig().getDouble("spawncommand.location.Yaw");
					Float pitch = (float) main.getConfig().getDouble("spawncommand.location.Pitch");
					if(w == "") {
						p.sendMessage(main.prefixerror + main.getConfig().getString("spawncommand.no-spawn").replaceAll("&", "§"));
						return true;
					}
					try {
						p.sendMessage(main.prefix + main.getConfig().getString("spawncommand.teleportation").replaceAll("&", "§"));
						p.teleport(new Location(Bukkit.getWorld(w), x, y, z, yaw, pitch));
					} catch (Exception e) {
						p.sendMessage(main.prefixerror + main.getConfig().getString("exception").replaceAll("&", "§").replace("{ERROR}", e.getMessage()));
					}
		}
		
		if(args.length >= 1) {
			if(!p.hasPermission("nextcommand.spawn.set")) {
			p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
			return true;
			}
			
			main.getConfig().set("spawncommand.location.World", p.getWorld().getName());
			main.getConfig().set("spawncommand.location.X", p.getLocation().getX());
			main.getConfig().set("spawncommand.location.Y", p.getLocation().getY());
			main.getConfig().set("spawncommand.location.Z", p.getLocation().getZ());
			main.getConfig().set("spawncommand.location.Yaw", p.getLocation().getYaw());
			main.getConfig().set("spawncommand.location.Pitch", p.getLocation().getPitch());
			main.saveConfig();
			p.sendMessage(main.prefix + main.getConfig().getString("spawncommand.spawn-set").replaceAll("&", "§"));
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
