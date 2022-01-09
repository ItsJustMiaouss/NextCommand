package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WarpCommand implements CommandExecutor, TabCompleter {

    private final NextCommand nextCommand;

    public WarpCommand(NextCommand nextCommand) {
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


        if(nextCommand.getPermissionsManager().hasPermission(player, Permission.WARP)) {
            String warpPath = "warps." + args[0].toLowerCase();

            if(!nextCommand.getConfig().isSet(warpPath)) {
                player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "warp-command.unknown"));
                return false;
            }

            World w = Bukkit.getWorld(nextCommand.getConfig().getString(warpPath + ".world"));
            double x = nextCommand.getConfig().getDouble(warpPath + ".x");
            double y = nextCommand.getConfig().getDouble(warpPath + ".y");
            double z = nextCommand.getConfig().getDouble(warpPath + ".z");
            float yaw = (float) nextCommand.getConfig().getDouble(warpPath + ".yaw");
            float pitch = (float) nextCommand.getConfig().getDouble(warpPath + ".pitch");


            // Teleport delay
            int teleportDelay = nextCommand.getConfig().getInt("warp-command.teleport-delay");

            if(nextCommand.getTeleporting().containsKey(player)) {
                player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "teleportation-invalid"));
                return false;
            }

            player.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "teleportation"));

            nextCommand.getTeleporting().put(player, new BukkitRunnable() {
                @Override
                public void run() {
                    if(!nextCommand.getTeleporting().containsKey(player)) return;
                    nextCommand.getTeleporting().remove(player);
                    player.teleport(new Location(w, x, y, z, yaw, pitch));
                }
            }.runTaskLater(nextCommand, teleportDelay * 20L));


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
