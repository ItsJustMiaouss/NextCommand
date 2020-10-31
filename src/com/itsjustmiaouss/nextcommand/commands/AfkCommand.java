package com.itsjustmiaouss.nextcommand.commands;

import com.itsjustmiaouss.nextcommand.Main;
import com.itsjustmiaouss.nextcommand.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AfkCommand implements CommandExecutor {

    private final Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.getErrorString("console-no-player"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {

            if (!Utils.hasPermission(player, "nextcommand.afk")) return false;

            if (main.afkPlayer.contains(player)) {
                main.afkPlayer.remove(player);
                removeAFK(player);
            } else {
                main.afkPlayer.add(player);
                player.sendMessage(Utils.getString("afkcommand.afk-enabled"));
                Bukkit.broadcastMessage(Utils.getString("afkcommand.afk-enabled-broadcast").replace("{PLAYER}", player.getName()));
            }

        }

        return false;
    }

    public static void removeAFK(Player player) {
        player.sendMessage(Utils.getString("afkcommand.afk-disabled"));
        Bukkit.broadcastMessage(Utils.getString("afkcommand.afk-disabled-broadcast").replace("{PLAYER}", player.getName()));
    }

}
