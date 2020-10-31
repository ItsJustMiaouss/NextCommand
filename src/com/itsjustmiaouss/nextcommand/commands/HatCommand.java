package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.getString("console-no-player"));
			return true;
		}


		Player player = (Player) sender;

		if (args.length == 0) {

			if (!Utils.hasPermission(player, "nextcommand.hat")) return false;

			ItemStack hand = player.getInventory().getItemInMainHand();
			ItemStack head = player.getInventory().getHelmet();

			if (hand == null || hand.getType() == Material.AIR) {
				player.sendMessage(Utils.getErrorString("hatcommand.no-placed"));
				return true;
			}

			player.getInventory().setHelmet(hand);
			player.getInventory().remove(hand);
			player.sendMessage(Utils.getErrorString("hatcommand.placed"));

			if (head != null && head.getType() != Material.AIR) {
				player.getInventory().addItem(head);
			}

		}	
		return false;
	}

}
