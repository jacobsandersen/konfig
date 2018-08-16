package com.algorithmjunkie.mc.konfig.core.database;

import javafx.util.Pair;

public final class RedisAuthAdapter implements AuthAdapter {
    private final String hostname;
    private final int port;

    public RedisAuthAdapter(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public String getDriverClassName() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getHostName() {
        return hostname;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getSchemaName() {
        return null;
    }

    @Override
    public String getJdbcUrl() {
        return null;
    }
}
