package com.itsjustmiaouss.nextcommand.utils.config;

import com.itsjustmiaouss.nextcommand.NextCommand;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private final NextCommand nextCommand;
    private final FileConfiguration config;

    public ConfigManager(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
        this.config = nextCommand.getConfig();
    }

    /**
     * Get prefix from Prefix enum class with white space
     * @param prefix Prefix enum
     * @return String
     */
   public String getPrefix(Prefixes prefix) {
        if(prefix == Prefixes.NONE) return "";
        return config.getString(prefix.getPath()).replaceAll("&", "§") + "§r ";
    }

    /**
     * Get string from config file and add to it a prefix
     * @param prefix Prefix from Prefix enum
     * @param path Config file path
     * @return String
     */
    public String getString(Prefixes prefix, String path) {
        return getPrefix(prefix) + config.getString(path).replaceAll("&", "§");
    }

    /**
     * Get boolean from config file path
     * @param path Path from config file
     * @return boolean
     */
    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

}
