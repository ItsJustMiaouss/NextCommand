package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permissions;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        if(PermissionsManager.hasPermission(sender, Permissions.NEXTCOMMAND_BROADCAST)) {
            if(args.length == 0) {
                sender.sendMessage(ConfigManager.getString(Prefixes.ERROR, "command-not-found"));
                return false;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for(String arg : args) {
                stringBuilder.append(arg).append(" ");
            }

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    ConfigManager.getString(Prefixes.NONE, "broadcast-command.format")
                            .replace("{message}", stringBuilder.toString())));
        }

        return false;
    }

}
