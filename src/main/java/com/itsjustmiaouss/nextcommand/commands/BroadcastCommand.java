package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
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

    private NextCommand nextCommand;
    private ConfigManager configManager;
    private PermissionsManager permissionsManager;

    public BroadcastCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
        this.configManager = nextCommand.getConfigManager();
        this.permissionsManager = nextCommand.getPermissionsManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        if(permissionsManager.hasPermission(sender, Permissions.NEXTCOMMAND_BROADCAST)) {
            if(args.length == 0) {
                sender.sendMessage(configManager.getString(Prefixes.ERROR, "command-not-found"));
                return false;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for(String arg : args) {
                stringBuilder.append(arg).append(" ");
            }

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    configManager.getString(Prefixes.NONE, "broadcast-command.format")
                            .replace("{message}", stringBuilder.toString())));
        }

        return false;
    }

}
