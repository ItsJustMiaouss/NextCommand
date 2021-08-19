package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permissions;
import com.itsjustmiaouss.nextcommand.utils.permissions.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class HatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0) {

            if(!(sender instanceof Player player)) {
                sender.sendMessage(ConfigManager.getString(Prefixes.ERROR, "not-player"));
                return false;
            }

            if(PermissionsManager.hasPermission(player, Permissions.NEXTCOMMAND_HAT)) {
                hat(player);
            }
        }
        else {
            sender.sendMessage(ConfigManager.getString(Prefixes.ERROR, "command-not-found"));
        }

        return false;
    }

    private void hat(Player player) {
        PlayerInventory playerInventory = player.getInventory();
        ItemStack hand = playerInventory.getItemInMainHand();
        ItemStack head = playerInventory.getHelmet();

        if(hand.getType() == Material.AIR) {
            player.sendMessage(ConfigManager.getString(Prefixes.ERROR, "hat-command.cannot-set"));
            return;
        }

        // if player already has an item on his helmet, we put it in his inventory
        if(head != null && head.getType() != Material.AIR) playerInventory.addItem(head);

        playerInventory.setHelmet(hand);
        playerInventory.setItemInMainHand(null);
        player.sendMessage(ConfigManager.getString(Prefixes.NORMAL, "hat-command.set"));
    }

}
