package com.itsjustmiaouss.nextcommand.utils.config;

public enum Prefixes {
    NONE(""),
    NORMAL("prefix"),
    ERROR("error-prefix");

    private String path;

    Prefixes(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
