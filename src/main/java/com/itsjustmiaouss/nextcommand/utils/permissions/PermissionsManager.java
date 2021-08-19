package com.itsjustmiaouss.nextcommand.utils.permissions;

import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionsManager {

    /**
     * Returns whether player has permission
     * @param sender Command Sender should have the permission
     * @param permission Permission from Permissions enum
     * @return boolean
     */
    public static boolean hasPermission(CommandSender sender, Permissions permission) {
        if(sender.hasPermission(permission.getNode()) || sender.hasPermission(Permissions.NEXTCOMMAND_ALL.getNode())) {
            return true;
        } else {
            sender.sendMessage(ConfigManager.getString(Prefixes.ERROR, "no-permission"));
            return false;
        }
    }

}
