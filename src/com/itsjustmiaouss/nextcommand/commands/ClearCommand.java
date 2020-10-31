package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand implements CommandExecutor {

    private final Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(Utils.getErrorString("console-no-player"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {

            if (!Utils.hasPermission(player, "nextcommand.clear")) return false;

            player.getInventory().clear();

            if (main.getConfig().getBoolean("clearcommand.clear-exp")) {
                player.setExp(0F);
                player.setLevel(0);
            }

            player.sendMessage(Utils.getString("clearcommand.cleared"));

        } else if (args.length >= 1) {

            if (!Utils.hasPermission(player, "nextcommand.clear.other")) return false;

            if (!Utils.isOfflinePlayer(args[0], player)) return false;

            Player target = Bukkit.getPlayer(args[0]);

            target.getInventory().clear();

            if (main.getConfig().getBoolean("clearcommand.clear-exp")) {
                target.setExp(0F);
                target.setLevel(0);
            }

            player.sendMessage(Utils.getString("clearcommand.cleared-sender").replace("{PLAYER}", target.getName()));
            target.sendMessage(Utils.getString("clearcommand.cleared-other").replace("{PLAYER}", target.getName()));

        }


        return false;
    }

}
