package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permissions;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InvseeCommand implements CommandExecutor {

    private NextCommand nextCommand;
    private ConfigManager config;
    private PermissionsManager permissionsManager;

    public InvseeCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
        this.config = nextCommand.getConfigManager();
        this.permissionsManager = nextCommand.getPermissionsManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player player)) {
            sender.sendMessage(config.getString(Prefixes.ERROR, "not-player"));
            return false;
        }

        if(args.length == 0) {
            player.sendMessage(config.getString(Prefixes.ERROR, "command-not-found"));
            return false;
        }
        else {
            Player target = Bukkit.getPlayer(args[0]);

            if(permissionsManager.hasPermission(player, Permissions.NEXTCOMMAND_INVSEE)) {
                Inventory targetInventory = target.getInventory();
                player.openInventory(targetInventory);
            }
        }

        return false;
    }
}
