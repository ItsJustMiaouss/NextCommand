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

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        double maxHealth = 20D;

        if(args.length == 0) {

            if(!(sender instanceof Player player)) {
                sender.sendMessage(ConfigManager.getString(Prefixes.ERROR, "not-player"));
                return false;
            }

            if(PermissionsManager.hasPermission(player, Permissions.NEXTCOMMAND_HEAL)) {
                player.setHealth(maxHealth);
                player.sendMessage(ConfigManager.getString(Prefixes.NORMAL, "heal-command.healed"));
            }
        }

        else {

            Player target = Bukkit.getPlayer(args[0]);

            if(PermissionsManager.hasPermission(sender, Permissions.NEXTCOMMAND_HEAL_OTHER)) {
                if(PlayerManager.isOnline(target, sender)) {
                    target.setHealth(maxHealth);
                    sender.sendMessage(ConfigManager.getString(Prefixes.NORMAL, "heal-command.healed-target")
                            .replace("{player}", target.getName()));
                    target.sendMessage(ConfigManager.getString(Prefixes.NORMAL, "heal-command.healed"));
                }
            }

        }

        return false;
    }
}
