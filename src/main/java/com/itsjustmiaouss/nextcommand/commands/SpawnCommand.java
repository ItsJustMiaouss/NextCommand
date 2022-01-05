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

                try {
                    spawnPlayer(player);
                    player.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "spawn-command.teleportation"));
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

                    try {
                        spawnPlayer(target);
                        target.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "spawn-command.teleportation"));
                        sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "spawn-command.teleportation-target")
                                .replace("{player}", target.getName()));
                    } catch (Exception e) {
                        sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "exception")
                                .replace("{error}", e.getMessage()));
                    }

                }
            }

        }

        return false;
    }

    private void spawnPlayer(Player player) throws Exception {
        String world = nextCommand.getConfig().getString("spawn-command.location.world");
        double x = nextCommand.getConfig().getDouble("spawn-command.location.x");
        double y = nextCommand.getConfig().getDouble("spawn-command.location.y");
        double z = nextCommand.getConfig().getDouble("spawn-command.location.z");
        float yaw = (float) nextCommand.getConfig().getDouble("spawn-command.location.yaw");
        float pitch = (float) nextCommand.getConfig().getDouble("spawn-command.location.pitch");

        player.teleport(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
    }
}
