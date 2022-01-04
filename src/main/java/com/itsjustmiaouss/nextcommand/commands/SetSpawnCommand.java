package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private final NextCommand nextCommand;

    public SetSpawnCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player player)) {
            sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "not-player"));
            return false;
        }

        if(args.length >= 1) {
            sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "not-player"));
        }

        if(nextCommand.getPermissionsManager().hasPermission(player, Permission.NEXTCOMMAND_SETSPAWN)) {
            Location playerLocation = player.getLocation();

            nextCommand.getConfig().set("spawn-command.location.world", playerLocation.getWorld().getName());
            nextCommand.getConfig().set("spawn-command.location.x", playerLocation.getX());
            nextCommand.getConfig().set("spawn-command.location.y", playerLocation.getY());
            nextCommand.getConfig().set("spawn-command.location.z", playerLocation.getZ());
            nextCommand.getConfig().set("spawn-command.location.yaw", playerLocation.getYaw());
            nextCommand.getConfig().set("spawn-command.location.pitch", playerLocation.getPitch());

            nextCommand.saveConfig();

            player.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "spawn-command.spawn-set"));
        }


        return false;
    }
}
