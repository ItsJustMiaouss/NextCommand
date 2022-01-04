package com.itsjustmiaouss.nextcommand.utils.permissions;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import org.bukkit.command.CommandSender;

public class PermissionsManager {

    private final NextCommand nextCommand;

    public PermissionsManager(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    /**
     * Returns whether player has permission
     * @param sender Command Sender should have the permission
     * @param permission Permission from Permission enum
     * @return boolean
     */
    public boolean hasPermission(CommandSender sender, Permission permission) {
        if(sender.hasPermission(permission.getNode()) || sender.hasPermission(Permission.NEXTCOMMAND_ALL.getNode())) {
            return true;
        } else {
            sender.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "no-permission")
                    .replace("{permission}", permission.getNode()));
            return false;
        }
    }

    /**
     * Returns whether player has permission
     * This method do not send a no-permission message to player
     * @param sender Command Sender should have the permission
     * @param permission Permission from Permission enum
     * @return boolean
     */
    public boolean hasPermissionRaw(CommandSender sender, Permission permission) {
        return sender.hasPermission(permission.getNode()) || sender.hasPermission(Permission.NEXTCOMMAND_ALL.getNode());
    }

}
