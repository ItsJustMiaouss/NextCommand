package com.itsjustmiaouss.nextcommand.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.itsjustmiaouss.nextcommand.Main;

public class HatCommand implements CommandExecutor {
	
	private Main main;

	public HatCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(main.prefixerror + main.getConfig().getString("console-no-player").replaceAll("&", "§"));
			return true;
		}
		
		
		Player p =(Player)sender;
		
		if(args.length == 0) {
			if(!p.hasPermission("nextcommand.hat")) {
				p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
				return true;
			}

				ItemStack hand = p.getInventory().getItemInMainHand();
				ItemStack head = p.getInventory().getHelmet();
				
				if(hand == null || hand.getType() == Material.AIR) {
					p.sendMessage(main.prefixerror + main.getConfig().getString("hatcommand.no-placed").replaceAll("&", "§"));
					return true;
				}
				
				p.getInventory().setHelmet(hand);
				p.getInventory().remove(hand);
				p.sendMessage(main.prefixerror + main.getConfig().getString("hatcommand.placed").replaceAll("&", "§"));
				
				if(head != null && head.getType() != Material.AIR) {
					p.getInventory().addItem(head);
				}
		}	
		return false;
	}

}
