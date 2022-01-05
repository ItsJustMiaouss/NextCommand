package com.itsjustmiaouss.nextcommand.utils.config;

import com.itsjustmiaouss.nextcommand.NextCommand;

public class ConfigManager {

    private final NextCommand nextCommand;

    public ConfigManager(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    /**
     * Get prefix from Prefix enum class with white space
     * @param prefix Prefix enum
     * @return String
     */
   public String getPrefix(Prefix prefix) {
        if(prefix == Prefix.NONE) return "";
        return nextCommand.getConfig().getString(prefix.getPath()).replaceAll("&", "§") + "§r ";
    }

    /**
     * Get string from config and add to it a prefix
     * @param prefix Prefix from Prefix enum
     * @param path Config file path
     * @return String
     */
    public String getString(Prefix prefix, String path) {
        return getPrefix(prefix) + nextCommand.getConfig().getString(path).replaceAll("&", "§");
    }

    /**
     * Get boolean from config path
     * @param path Path from config file
     * @return boolean
     */
    public boolean getBoolean(String path) {
        return nextCommand.getConfig().getBoolean(path);
    }

}
