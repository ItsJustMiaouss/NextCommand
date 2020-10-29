package com.itsjustmiaouss.nextcommand.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {

    public static boolean hasPermission(Player p, String permission){
        return p.hasPermission(permission) || p.hasPermission("nextcommand.*");
    }

    public static boolean hasPermissionSender(CommandSender s, String permission){
        return s.hasPermission(permission) || s.hasPermission("nextcommand.*");
    }

}
