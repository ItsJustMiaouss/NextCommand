package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand implements CommandExecutor {

    private final Main main;

    public ClearCommand(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(main.prefixerror + main.getConfig().getString("console-no-player").replaceAll("&", "§"));
            return true;
        }

        Player p = (Player)sender;

        if(args.length == 0){

            if(!Utils.hasPermission(p, "nextcommand.clear")){
                p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
                return true;
            }

            p.getInventory().clear();
            p.sendMessage(main.prefix + main.getConfig().getString("clearcommand.cleared").replaceAll("&", "§"));

        } else if(args.length >= 1){

            if(!Utils.hasPermission(p, "nextcommand.clear.other")){
                p.sendMessage(main.prefixerror + main.getConfig().getString("no-permission").replaceAll("&", "§"));
                return true;
            }

            if(Utils.getOfflinePlayer(args[0]) == null){
                p.sendMessage(main.prefixerror + main.getConfig().getString("player-not-found").replaceAll("&", "§"));
                return true;
            }

            Player t = Bukkit.getPlayer(args[0]);

            t.getInventory().clear();
            p.sendMessage(main.prefix + main.getConfig().getString("clearcommand.cleared-sender").replaceAll("&", "§").replace("{PLAYER}", t.getName()));
            t.sendMessage(main.prefix + main.getConfig().getString("clearcommand.cleared-other").replaceAll("&", "§").replace("{PLAYER}", t.getName()));

        }


        return false;
    }
}
