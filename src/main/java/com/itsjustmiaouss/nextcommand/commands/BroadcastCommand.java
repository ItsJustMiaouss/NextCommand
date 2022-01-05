package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {

    private final NextCommand nextCommand;

    public BroadcastCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        if(nextCommand.getPermissionsManager().hasPermission(sender, Permission.BROADCAST)) {
            if(args.length == 0) {
                sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "command-not-found"));
                return false;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for(String arg : args) {
                stringBuilder.append(arg).append(" ");
            }

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    nextCommand.getConfigManager().getString(Prefix.NONE, "broadcast-command.format")
                            .replace("{message}", stringBuilder.toString())));
        }

        return false;
    }

}
