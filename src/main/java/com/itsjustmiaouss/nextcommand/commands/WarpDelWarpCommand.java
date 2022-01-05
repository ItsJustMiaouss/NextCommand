package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WarpDelWarpCommand implements CommandExecutor, TabCompleter {

    private final NextCommand nextCommand;

    public WarpDelWarpCommand(NextCommand nextCommand) {
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

        if(nextCommand.getPermissionsManager().hasPermission(player, Permission.DELWARP)) {
            String warpPath = "warps." + args[0].toLowerCase();

            if(!nextCommand.getConfig().isSet(warpPath)) {
                player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "warp-command.unknown"));
                return false;
            }

            nextCommand.getConfig().set(warpPath, null);
            nextCommand.getConfig().set(warpPath + ".world", null);
            nextCommand.getConfig().set(warpPath + ".x", null);
            nextCommand.getConfig().set(warpPath + ".y", null);
            nextCommand.getConfig().set(warpPath + ".z", null);
            nextCommand.getConfig().set(warpPath + ".yaw", null);
            nextCommand.getConfig().set(warpPath + ".pitch", null);
            nextCommand.saveConfig();

            player.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "warp-command.deleted")
                    .replace("{name}", args[0].toLowerCase()));
        }

        return false;
    }

    List<String> arguments = new ArrayList<>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        arguments.clear();
        ConfigurationSection warpsSection = nextCommand.getConfig().getConfigurationSection("warps");
        arguments.addAll(warpsSection.getKeys(false));

        return arguments;
    }
}
