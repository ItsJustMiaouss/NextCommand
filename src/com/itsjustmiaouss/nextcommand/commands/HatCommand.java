package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand implements CommandExecutor {
	
	private final Main main;

	public HatCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.getString("console-no-player", main));
			return true;
		}
		
		
		Player p =(Player)sender;
		
		if(args.length == 0) {

			if (!Utils.hasPermission(p, "nextcommand.hat", main)) return false;

			ItemStack hand = p.getInventory().getItemInMainHand();
			ItemStack head = p.getInventory().getHelmet();

			if (hand == null || hand.getType() == Material.AIR) {
				p.sendMessage(Utils.getErrorString("hatcommand.no-placed", main));
				return true;
			}

			p.getInventory().setHelmet(hand);
			p.getInventory().remove(hand);
			p.sendMessage(Utils.getErrorString("hatcommand.placed", main));

			if (head != null && head.getType() != Material.AIR) {
				p.getInventory().addItem(head);
			}

		}	
		return false;
	}

}
