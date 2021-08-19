package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.PlayerManager;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permissions;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private NextCommand nextCommand;
    private ConfigManager configManager;
    private PermissionsManager permissionsManager;
    private PlayerManager playerManager;

    public FlyCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
        this.configManager = nextCommand.getConfigManager();
        this.permissionsManager = nextCommand.getPermissionsManager();
        this.playerManager = nextCommand.getPlayerManager();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0) {

            if(!(sender instanceof Player player)) {
                sender.sendMessage(configManager.getString(Prefixes.ERROR, "not-player"));
                return false;
            }

            if(permissionsManager.hasPermission(player, Permissions.NEXTCOMMAND_FLY)) {
                toggleFly(player);
            }

        }
        else {
            Player target = Bukkit.getPlayer(args[0]);

            if(permissionsManager.hasPermission(sender, Permissions.NEXTCOMMAND_FLY_OTHER)) {
                if(playerManager.isOnline(target, sender)) {
                    toggleFlyTarget(target, sender);
                }
            }
        }

        return false;
    }

    private void toggleFly(Player player) {
        if(player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(configManager.getString(Prefixes.NORMAL, "fly-command.fly-disabled"));
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(configManager.getString(Prefixes.NORMAL, "fly-command.fly-enabled"));
        }
    }

    private void toggleFlyTarget(Player target, CommandSender sender) {
        if(target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.setFlying(false);
            target.sendMessage(configManager.getString(Prefixes.NORMAL, "fly-command.fly-disabled"));
            sender.sendMessage(configManager.getString(Prefixes.NORMAL, "fly-command.fly-disabled-target")
                    .replace("{player}", target.getName()));
        } else {
            target.setAllowFlight(true);
            target.setFlying(true);
            target.sendMessage(configManager.getString(Prefixes.NORMAL, "fly-command.fly-enabled"));
            sender.sendMessage(configManager.getString(Prefixes.NORMAL, "fly-command.fly-enabled-target")
                    .replace("{player}", target.getName()));
        }
    }

}
