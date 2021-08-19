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

public class FeedCommand implements CommandExecutor {

    int maxFoodLevel = 20;
    float maxSaturation = 0.6F;

    private NextCommand nextCommand;
    private ConfigManager configManager;
    private PermissionsManager permissionsManager;
    private PlayerManager playerManager;

    public FeedCommand(NextCommand nextCommand) {
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

            if(permissionsManager.hasPermission(player, Permissions.NEXTCOMMAND_FEED)) {
                feed(player);
            }
        }

        else {

            Player target = Bukkit.getPlayer(args[0]);

            if(permissionsManager.hasPermission(sender, Permissions.NEXTCOMMAND_FEED_OTHER)) {
                if(playerManager.isOnline(target, sender)) {
                    feed(target);
                    sender.sendMessage(configManager.getString(Prefixes.NORMAL, "feed-command.fed-target")
                            .replace("{player}", target.getName()));
                }
            }

        }

        return false;
    }

    private void feed(Player player) {
        player.setFoodLevel(maxFoodLevel);
        player.setSaturation(maxSaturation);
        player.sendMessage(configManager.getString(Prefixes.NORMAL, "feed-command.fed"));
    }

}
