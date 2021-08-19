package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permissions;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private NextCommand nextCommand;
    private ConfigManager config;
    private PermissionsManager permissionsManager;

    public SpawnCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
        this.config = nextCommand.getConfigManager();
        this.permissionsManager = nextCommand.getPermissionsManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0) {
            if(!(sender instanceof Player player)) {
                sender.sendMessage(config.getString(Prefixes.ERROR, "not-player"));
                return false;
            }

            if(permissionsManager.hasPermission(player, Permissions.NEXTCOMMAND_SPAWN)) {

                player.sendMessage(config.getString(Prefixes.NORMAL, "spawn-command.teleportation"));

                String world = nextCommand.getConfig().getString("spawn-command.location.world");
                double x = nextCommand.getConfig().getDouble("spawn-command.location.x");
                double y = nextCommand.getConfig().getDouble("spawn-command.location.y");
                double z = nextCommand.getConfig().getDouble("spawn-command.location.z");
                float yaw = (float) nextCommand.getConfig().getDouble("spawn-command.location.yaw");
                float pitch = (float) nextCommand.getConfig().getDouble("spawn-command.location.pitch");

                try {
                    player.teleport(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
                } catch (Exception e) {
                    player.sendMessage(config.getString(Prefixes.ERROR, "exception")
                            .replace("{error}", e.getMessage()));
                }

            }
        }

        return false;
    }
}
