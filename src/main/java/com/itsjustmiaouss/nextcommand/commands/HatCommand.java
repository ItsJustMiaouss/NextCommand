package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import com.itsjustmiaouss.nextcommand.utils.permissions.Permission;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class HatCommand implements CommandExecutor {

    private final NextCommand nextCommand;

    public HatCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0) {

            if(!(sender instanceof Player player)) {
                sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "not-player"));
                return false;
            }

            if(nextCommand.getPermissionsManager().hasPermission(player, Permission.HAT)) {
                hat(player);
            }
        }
        else {
            sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "command-not-found"));
        }

        return false;
    }

    private void hat(Player player) {
        PlayerInventory playerInventory = player.getInventory();
        ItemStack hand = playerInventory.getItemInMainHand();
        ItemStack head = playerInventory.getHelmet();

        if(hand.getType() == Material.AIR) {
            player.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "hat-command.cannot-set"));
            return;
        }

        // if player already has an item on his helmet, we put it in his inventory
        if(head != null && head.getType() != Material.AIR) playerInventory.addItem(head);

        playerInventory.setHelmet(hand);
        playerInventory.setItemInMainHand(null);
        player.sendMessage(nextCommand.getConfigManager().getString(Prefix.NORMAL, "hat-command.set"));
    }

}
