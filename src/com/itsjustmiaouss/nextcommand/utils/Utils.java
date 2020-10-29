package com.itsjustmiaouss.nextcommand.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {

    public static boolean hasPermission(Player p, String permission){
        return p.hasPermission(permission) || p.hasPermission("nextcommand.*");
    }

    public static boolean hasPermissionSender(CommandSender s, String permission){
        return s.hasPermission(permission) || s.hasPermission("nextcommand.*");
    }

    public static OfflinePlayer getOfflinePlayer(String name) {
        for(OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if(player.getName().equals(name)) return player;
        }
        return null;
    }

}
