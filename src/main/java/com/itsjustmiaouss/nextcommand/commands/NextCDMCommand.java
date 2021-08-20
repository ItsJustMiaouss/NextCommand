package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permissions;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class NextCDMCommand implements CommandExecutor, TabCompleter {

    private NextCommand nextCommand;
    private ConfigManager configManager;
    private PermissionsManager permissionsManager;

    public NextCDMCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
        this.configManager = nextCommand.getConfigManager();
        this.permissionsManager = nextCommand.getPermissionsManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0) {
            if(permissionsManager.hasPermission(sender, Permissions.NEXTCOMMAND_BASE)) {

                String pluginVersion = nextCommand.getDescription().getVersion();

                sender.sendMessage(configManager.getPrefix(Prefixes.NORMAL) +
                        "§aNextCommand §7was created by §aItsJustMiaouss§7. Version §a" + pluginVersion + "§7.");
            }
        }
        else if(args[0].equalsIgnoreCase("reload")) {
            if(permissionsManager.hasPermission(sender, Permissions.NEXTCOMMAND_RELOAD)) {

                nextCommand.reloadConfig();
                sender.sendMessage(configManager.getString(Prefixes.NORMAL, "config-reload"));
            }
        }
        else {
            sender.sendMessage(configManager.getString(Prefixes.ERROR, "command-not-found"));
        }

        return false;
    }

    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        if(arguments.isEmpty()) { arguments.add("reload"); }
        return arguments;
    }

}
