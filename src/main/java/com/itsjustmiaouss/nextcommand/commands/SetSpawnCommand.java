package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permissions;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private NextCommand nextCommand;
    private ConfigManager config;
    private PermissionsManager permissionsManager;

    public SetSpawnCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
        this.config = nextCommand.getConfigManager();
        this.permissionsManager = nextCommand.getPermissionsManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player player)) {
            sender.sendMessage(config.getString(Prefixes.ERROR, "not-player"));
            return false;
        }

        if(args.length >= 1) {
            sender.sendMessage(config.getString(Prefixes.ERROR, "not-player"));
        }

        if(permissionsManager.hasPermission(player, Permissions.NEXTCOMMAND_SETSPAWN)) {
            Location playerLocation = player.getLocation();

            nextCommand.getConfig().set("spawn-command.location.world", playerLocation.getWorld().getName());
            nextCommand.getConfig().set("spawn-command.location.x", playerLocation.getX());
            nextCommand.getConfig().set("spawn-command.location.y", playerLocation.getY());
            nextCommand.getConfig().set("spawn-command.location.z", playerLocation.getZ());
            nextCommand.getConfig().set("spawn-command.location.yaw", playerLocation.getYaw());
            nextCommand.getConfig().set("spawn-command.location.pitch", playerLocation.getPitch());

            nextCommand.saveConfig();

            player.sendMessage(config.getString(Prefixes.NORMAL, "spawn-command.spawn-set"));
        }


        return false;
    }
}
