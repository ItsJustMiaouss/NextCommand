package com.itsjustmiaouss.nextcommand.utils;

import com.itsjustmiaouss.nextcommand.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class Utils {

    public static boolean hasPermission(Player p, String permission, Main main) {

        if (!p.hasPermission(permission) || !p.hasPermission("nextcommand.*")) {
            p.sendMessage(getErrorString("no-permission", main));
            return false;
        }
        return true;

    }

    public static boolean hasPermissionSender(CommandSender s, String permission, Main main) {

        if (!s.hasPermission(permission) || !s.hasPermission("nextcommand.*")) {
            s.sendMessage(getErrorString("no-permission", main));
            return false;
        }
        return true;

    }

    public static boolean isOfflinePlayer(String name, Player p, Main main) {
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        p.sendMessage(getErrorString("player-not-found", main));
        return false;
    }

    public static String getString(String string, Main main) {
        return main.prefix + main.getConfig().getString(string).replaceAll("&", "§");
    }

    public static String getErrorString(String string, Main main) {
        return main.error_prefix + main.getConfig().getString(string).replaceAll("&", "§");
    }

}
