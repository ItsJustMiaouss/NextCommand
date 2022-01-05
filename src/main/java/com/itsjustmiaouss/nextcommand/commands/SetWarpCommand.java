package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class SetWarpCommand implements CommandExecutor {

    private final NextCommand nextCommand;

    public SetWarpCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(!(sender instanceof Player player)) {
            sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "not-player"));
            return false;
        }

        if(args.length == 0) {
            player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "command-not-found"));
            return false;
        }

        if(nextCommand.getPermissionsManager().hasPermission(player, Permission.SETWARP)) {
            String warpPath = "warps." + args[0].toLowerCase();

            if(nextCommand.getConfig().isSet(warpPath)) {
                player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "warp-command.exists")
                        .replace("{name}", args[0].toLowerCase()));
                return false;
            }

            Location location = player.getLocation();

            nextCommand.getConfig().set(warpPath + ".world", location.getWorld().getName());
            nextCommand.getConfig().set(warpPath + ".x", location.getX());
            nextCommand.getConfig().set(warpPath + ".y", location.getY());
            nextCommand.getConfig().set(warpPath + ".z", location.getZ());
            nextCommand.getConfig().set(warpPath + ".yaw", location.getYaw());
            nextCommand.getConfig().set(warpPath + ".pitch", location.getPitch());
            nextCommand.saveConfig();

            player.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "warp-command.saved")
                    .replace("{name}", args[0].toLowerCase()));
        }

        return false;
    }
}
