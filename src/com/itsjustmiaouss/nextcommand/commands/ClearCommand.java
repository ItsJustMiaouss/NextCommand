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
            sender.sendMessage(Utils.getErrorString("console-no-player", main));
            return true;
        }

        Player p = (Player)sender;

        if (args.length == 0) {

            if (!Utils.hasPermission(p, "nextcommand.clear", main)) return false;

            p.getInventory().clear();
            p.sendMessage(Utils.getString("clearcommand.cleared", main));

        } else if (args.length >= 1) {

            if (!Utils.hasPermission(p, "nextcommand.clear.other", main)) return false;

            if (!Utils.isOfflinePlayer(args[0], p, main)) return false;

            Player t = Bukkit.getPlayer(args[0]);

            t.getInventory().clear();
            p.sendMessage(Utils.getString("clearcommand.cleared-sender", main).replace("{PLAYER}", t.getName()));
            t.sendMessage(Utils.getString("clearcommand.cleared-other", main).replace("{PLAYER}", t.getName()));

        }


        return false;
    }
}
