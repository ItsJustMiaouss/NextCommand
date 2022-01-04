package com.itsjustmiaouss.nextcommand.utils.config;

public enum Prefix {
    NONE(""),
    NORMAL("prefix"),
    ERROR("error-prefix");

    private final String path;

    Prefix(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
