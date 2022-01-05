package com.itsjustmiaouss.nextcommand.utils.permissions;

public enum Permission {
    ALL("*"),
    BASE("base"),
    RELOAD("reload"),
    BROADCAST("broadcast"),
    FEED("feed"),
    FEED_OTHER("feed.other"),
    HEAL("heal"),
    HEAL_OTHER("heal.other"),
    FLY("fly"),
    FLY_OTHER("fly.other"),
    HAT("hat"),
    CHATCOLOR("chatcolor"),
    INVSEE("invesee"),
    SETSPAWN("setspawn"),
    SPAWN("spawn"),
    SPAWN_OTHER("spawn.other"),
    BYPASS_COMMAND_PROTECTION("bypasscommandprotection"),
    PING("ping"),
    PING_OTHER("ping.other");

    private final String node;

    Permission(String node) {
        this.node = node;
    }

    public String getNode() {
        return "nextcommand." + node;
    }
}
