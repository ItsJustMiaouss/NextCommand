package com.itsjustmiaouss.nextcommand.commands;


import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.google.common.collect.Lists;
import com.itsjustmiaouss.nextcommand.Main;

public class NextCommand implements CommandExecutor, TabCompleter {
	
	private Main main;

	public NextCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0) {
			sender.sendMessage(main.prefix + "§7The plugin §aNextCommand §7was developed by §aItsJustMiaouss§7.");
			return true;
		}
		
		if(args.length >= 1) {
			String s = args[0];
			switch (s) {
			case "reload":
				if(!sender.hasPermission("nextcommand.reload")) {
					sender.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
					return true;
				}
				main.reloadConfig();
				sender.sendMessage(main.prefix + main.getConfig().getString("config-reloaded").replaceAll("&", "§"));
				break;
			}
		}
		
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> s1 = Arrays.asList("reload");
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
