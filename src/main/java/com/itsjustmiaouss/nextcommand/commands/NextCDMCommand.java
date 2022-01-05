package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class NextCDMCommand implements CommandExecutor, TabCompleter {

    private final NextCommand nextCommand;

    public NextCDMCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0) {
            if(nextCommand.getPermissionsManager().hasPermission(sender, Permission.BASE)) {

                String pluginVersion = nextCommand.getDescription().getVersion();

                sender.sendMessage(nextCommand.getConfigManager().getPrefix(Prefix.NORMAL) +
                        "§aNextCommand §7was created by §aItsJustMiaouss§7. Version §a" + pluginVersion + "§7.");
            }
        }
        else if(args[0].equalsIgnoreCase("reload")) {
            if(nextCommand.getPermissionsManager().hasPermission(sender, Permission.RELOAD)) {

                nextCommand.reloadConfig();
                sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "config-reload"));
            }
        }
        else {
            sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "command-not-found"));
        }

        return false;
    }

    List<String> arguments = new ArrayList<>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        if(arguments.isEmpty()) { arguments.add("reload"); }
        return arguments;
    }

}
