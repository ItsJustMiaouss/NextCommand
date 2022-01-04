package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCommand implements CommandExecutor {

    private final NextCommand nextCommand;

    public InvseeCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player player)) {
            sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "not-player"));
            return false;
        }

        if(args.length == 0) {
            player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "command-not-found"));
            return false;
        }
        else {
            Player target = Bukkit.getPlayer(args[0]);

            if(nextCommand.getPermissionsManager().hasPermission(player, Permission.NEXTCOMMAND_INVSEE)) {
                Inventory targetInventory = target.getInventory();
                player.openInventory(targetInventory);
            }
        }

        return false;
    }
}
