package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCommand implements CommandExecutor {

    private final NextCommand nextCommand;

    public SpawnCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0) {
            if(!(sender instanceof Player player)) {
                sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "not-player"));
                return false;
            }

            if(nextCommand.getPermissionsManager().hasPermission(player, Permission.SPAWN)) {

                // Teleport delay
                int teleportDelay = nextCommand.getConfig().getInt("spawn-command.teleport-delay");

                if(nextCommand.getTeleporting().containsKey(player)) {
                    player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "teleportation-invalid"));
                    return false;
                }

                try {
                    player.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "teleportation"));
                    spawnPlayer(teleportDelay, player);
                } catch (Exception e) {
                    player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "exception")
                            .replace("{error}", e.getMessage()));
                }

            }
        }

        else {
            Player target = Bukkit.getPlayer(args[0]);

            if(nextCommand.getPermissionsManager().hasPermission(sender, Permission.SPAWN_OTHER)) {
                if(nextCommand.getPlayerManager().isOnline(target, sender)) {

                    // Teleport delay
                    int teleportDelay = nextCommand.getConfig().getInt("spawn-command.teleport-delay");
                    if(nextCommand.getTeleporting().containsKey(target)) return false;

                    try {
                        target.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "teleportation"));
                        sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "spawn-command.teleportation-target")
                                .replace("{player}", target.getName()));
                        spawnPlayer(teleportDelay, target);
                    } catch (Exception e) {
                        sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "exception")
                                .replace("{error}", e.getMessage()));
                    }

                }
            }

        }

        return false;
    }

    private void spawnPlayer(int delay, Player player) throws Exception {
        String world = nextCommand.getConfig().getString("spawn-command.location.world");
        double x = nextCommand.getConfig().getDouble("spawn-command.location.x");
        double y = nextCommand.getConfig().getDouble("spawn-command.location.y");
        double z = nextCommand.getConfig().getDouble("spawn-command.location.z");
        float yaw = (float) nextCommand.getConfig().getDouble("spawn-command.location.yaw");
        float pitch = (float) nextCommand.getConfig().getDouble("spawn-command.location.pitch");

        // Keep this line, this will throw an exception
        // if the world isn't valid (if the spawn isn't set)
        Bukkit.getWorld(world);

        nextCommand.getTeleporting().put(player, new BukkitRunnable() {
            @Override
            public void run() {
                if(!nextCommand.getTeleporting().containsKey(player)) return;
                nextCommand.getTeleporting().remove(player);
                player.teleport(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
            }
        }.runTaskLater(nextCommand, delay * 20L));
    }
}
