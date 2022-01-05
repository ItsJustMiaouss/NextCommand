package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    int maxFoodLevel = 20;
    float maxSaturation = 0.6F;

    private final NextCommand nextCommand;

    public FeedCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if(args.length == 0) {

            if(!(sender instanceof Player player)) {
                sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "not-player"));
                return false;
            }

            if(nextCommand.getPermissionsManager().hasPermission(player, Permission.FEED)) {
                feed(player);
            }
        }

        else {

            Player target = Bukkit.getPlayer(args[0]);

            if(nextCommand.getPermissionsManager().hasPermission(sender, Permission.FEED_OTHER)) {
                if(nextCommand.getPlayerManager().isOnline(target, sender)) {
                    feed(target);
                    sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "feed-command.fed-target")
                            .replace("{player}", target.getName()));
                }
            }

        }

        return false;
    }

    private void feed(Player player) {
        player.setFoodLevel(maxFoodLevel);
        player.setSaturation(maxSaturation);
        player.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "feed-command.fed"));
    }

}
