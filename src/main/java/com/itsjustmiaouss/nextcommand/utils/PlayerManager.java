package com.itsjustmiaouss.nextcommand.utils;

import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefixes;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerManager {

    /**
     * Checks if a player is online
     * @param player Searched player
     * @param executor Player that search for online player
     * @return boolean
     */
    public static boolean isOnline(Player player, CommandSender executor) {
        if(player != null) {
            return true;
        } else {
            executor.sendMessage(ConfigManager.getString(Prefixes.ERROR, "player-not-found"));
            return false;
        }
    }

}