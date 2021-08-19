package com.itsjustmiaouss.nextcommand.commands;

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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if(args.length == 0) {

            if(!(sender instanceof Player player)) {
                sender.sendMessage(ConfigManager.getString(Prefixes.ERROR, "not-player"));
                return false;
            }

            if(PermissionsManager.hasPermission(player, Permissions.NEXTCOMMAND_FEED)) {
                feed(player);
            }
        }

        else {

            Player target = Bukkit.getPlayer(args[0]);

            if(PermissionsManager.hasPermission(sender, Permissions.NEXTCOMMAND_FEED_OTHER)) {
                if(PlayerManager.isOnline(target, sender)) {
                    feed(target);
                    sender.sendMessage(ConfigManager.getString(Prefixes.NORMAL, "feed-command.fed-target")
                            .replace("{player}", target.getName()));
                }
            }

        }

        return false;
    }

    private void feed(Player player) {
        player.setFoodLevel(maxFoodLevel);
        player.setSaturation(maxSaturation);
        player.sendMessage(ConfigManager.getString(Prefixes.NORMAL, "feed-command.fed"));
    }

}
