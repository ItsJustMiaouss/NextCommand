package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permissions;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NextCDMCommand implements CommandExecutor {

    private NextCommand nextCommand = NextCommand.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0) {
            if(PermissionsManager.hasPermission(sender, Permissions.NEXTCOMMAND_BASE)) {

                String pluginVersion = nextCommand.getDescription().getVersion();

                sender.sendMessage(ConfigManager.getPrefix(Prefixes.NORMAL) +
                        "§aNextCommand §7was created by §aItsJustMiaouss§7. Version §a" + pluginVersion + "§7.");
            }
        }
        else if(args.length >= 1 && args[0].equalsIgnoreCase("reload")) {
            if(PermissionsManager.hasPermission(sender, Permissions.NEXTCOMMAND_RELOAD)) {
                sender.sendMessage(ConfigManager.getString(Prefixes.NORMAL, "config-reloaded"));
                nextCommand.reloadConfig();
            }
        }
        else {
            sender.sendMessage(ConfigManager.getString(Prefixes.ERROR, "command-not-found"));
        }

        return false;
    }

}
