package com.itsjustmiaouss.nextcommand.utils;

import com.itsjustmiaouss.nextcommand.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {

    private static final Main main = Main.getInstance();

    public static boolean hasPermission(Player player, String permission) {
        if (!player.hasPermission(permission) || !player.hasPermission("nextcommand.*")) {
            player.sendMessage(getErrorString("no-permission"));
            return false;
        }
        return true;
    }

    public static boolean hasPermission(CommandSender sender, String permission) {
        if (!sender.hasPermission(permission) || !sender.hasPermission("nextcommand.*")) {
            sender.sendMessage(getErrorString("no-permission"));
            return false;
        }
        return true;
    }

    public static boolean isOfflinePlayer(String name, Player player) {
        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        player.sendMessage(getErrorString("player-not-found"));
        return false;
    }

    public static String getString(String string) {
        return main.prefix + main.getConfig().getString(string).replaceAll("&", "§");
    }

    public static String getErrorString(String string) {
        return main.error_prefix + main.getConfig().getString(string).replaceAll("&", "§");
    }

}
