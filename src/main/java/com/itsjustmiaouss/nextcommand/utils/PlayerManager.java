package com.itsjustmiaouss.nextcommand.utils;

import com.itsjustmiaouss.nextcommand.NextCommand;
import com.itsjustmiaouss.nextcommand.utils.config.ConfigManager;
import com.itsjustmiaouss.nextcommand.utils.config.Prefix;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerManager {

    private final NextCommand nextCommand;

    public PlayerManager(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    /**
     * Check if a player is online
     * @param player Searched player
     * @param executor Searcher player
     * @return boolean
     */
    public boolean isOnline(Player player, CommandSender executor) {
        if(player != null) {
            return true;
        } else {
            executor.sendMessage(nextCommand.getConfigManager().getString(Prefix.ERROR, "player-not-found"));
            return false;
        }
    }

}
