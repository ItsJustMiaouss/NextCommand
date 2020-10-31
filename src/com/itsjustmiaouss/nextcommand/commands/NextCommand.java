package com.itsjustmiaouss.nextcommand.commands;


import com.google.common.collect.Lists;
import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class NextCommand implements CommandExecutor, TabCompleter {

	private static final Main main = Main.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length == 0) {
			sender.sendMessage(main.prefix + "§7The plugin §aNextCommand §7was developed by §aItsJustMiaouss§7.");
			return true;
		}

		if (args.length >= 1) {

			if (!Utils.hasPermission(sender, "nextcommand.reload")) return false;

			switch (args[0]) {
				case "reload":
					main.reloadConfig();
					sender.sendMessage(Utils.getString("config-reloaded"));
					break;
				case "help":
					sender.sendMessage(main.prefix + "§7If you need help or if you found a bug, please create an issue on GitHub (§ahttps://github.com/ItsJustMiaouss/NextCommand§7).");
					break;
			}

		}
		
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> s1 = Arrays.asList("reload", "help");
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
