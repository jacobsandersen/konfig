package com.algorithmjunkie.mc.konfig.core.database;

public final class SecureRedisAuthAdapter implements AuthAdapter {
    private final String hostname;
    private final String password;
    private final int port;

    public SecureRedisAuthAdapter(String hostname, String password, int port) {
        this.hostname = hostname;
        this.password = password;
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
        return password;
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
