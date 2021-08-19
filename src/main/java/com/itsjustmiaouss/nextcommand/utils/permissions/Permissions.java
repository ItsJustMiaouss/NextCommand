package com.itsjustmiaouss.nextcommand.utils.permissions;

public enum Permissions {
    NEXTCOMMAND_ALL("nextcommand.*"),
    NEXTCOMMAND_BASE("nextcommand.base"),
    NEXTCOMMAND_RELOAD("nextcommand.reload"),
    NEXTCOMMAND_BROADCAST("nextcommand.broadcast"),
    NEXTCOMMAND_FEED("nextcommand.feed"),
    NEXTCOMMAND_FEED_OTHER("nextcommand.feed.other"),
    NEXTCOMMAND_HEAL("nextcommand.heal"),
    NEXTCOMMAND_HEAL_OTHER("nextcommand.heal.other"),
    NEXTCOMMAND_FLY("nextcommand.fly"),
    NEXTCOMMAND_FLY_OTHER("nextcommand.fly.other"),
    NEXTCOMMAND_HAT("nextcommand.hat"),
    NEXTCOMMAND_HAT_OTHER("nextcommand.hat.other"),
    NEXTCOMMAND_CHATCOLOR("nextcommand.chatcolor");

    private String node;

    Permissions(String node) {
        this.node = node;
    }

    public String getNode() {
        return node;
    }
}
