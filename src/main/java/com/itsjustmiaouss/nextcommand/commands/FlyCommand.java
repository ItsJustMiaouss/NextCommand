package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private final NextCommand nextCommand;

    public FlyCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0) {

            if(!(sender instanceof Player player)) {
                sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "not-player"));
                return false;
            }

            if(nextCommand.getPermissionsManager().hasPermission(player, Permission.FLY)) {
                toggleFly(player);
            }

        }
        else {
            Player target = Bukkit.getPlayer(args[0]);

            if(nextCommand.getPermissionsManager().hasPermission(sender, Permission.FLY_OTHER)) {
                if(nextCommand.getPlayerManager().isOnline(target, sender)) {
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
            player.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "fly-command.fly-disabled"));
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "fly-command.fly-enabled"));
        }
    }

    private void toggleFlyTarget(Player target, CommandSender sender) {
        if(target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.setFlying(false);
            target.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "fly-command.fly-disabled"));
            sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "fly-command.fly-disabled-target")
                    .replace("{player}", target.getName()));
        } else {
            target.setAllowFlight(true);
            target.setFlying(true);
            target.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "fly-command.fly-enabled"));
            sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "fly-command.fly-enabled-target")
                    .replace("{player}", target.getName()));
        }
    }

}
