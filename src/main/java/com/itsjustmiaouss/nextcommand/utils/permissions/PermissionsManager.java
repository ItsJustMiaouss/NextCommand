package com.itsjustmiaouss.nextcommand.utils.permissions;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import org.bukkit.command.CommandSender;

public class PermissionsManager {

    private NextCommand nextCommand;
    private ConfigManager config;

    public PermissionsManager(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
        this.config = nextCommand.getConfigManager();
    }

    /**
     * Returns whether player has permission
     * @param sender Command Sender should have the permission
     * @param permission Permission from Permissions enum
     * @return boolean
     */
    public boolean hasPermission(CommandSender sender, Permissions permission) {
        if(sender.hasPermission(permission.getNode()) || sender.hasPermission(Permissions.NEXTCOMMAND_ALL.getNode())) {
            return true;
        } else {
            sender.sendMessage(config.getString(Prefixes.ERROR, "no-permission"));
            return false;
        }
    }

    /**
     * Returns whether player has permission
     * This method do not send a no-permission message to player
     * @param sender Command Sender should have the permission
     * @param permission Permission from Permissions enum
     * @return boolean
     */
    public boolean hasPermissionRaw(CommandSender sender, Permissions permission) {
        return sender.hasPermission(permission.getNode()) || sender.hasPermission(Permissions.NEXTCOMMAND_ALL.getNode());
    }

}
