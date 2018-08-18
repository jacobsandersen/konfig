package com.algorithmjunkie.mc.konfig.core.database;

public abstract class JdbcAuthAdapter implements AuthAdapter {
    private final String username;
    private final String password;
    private final String schema;
    private final String hostname;
    private final int port;

    JdbcAuthAdapter(String username, String password, String schema, String hostname, int port) {
        this.username = username;
        this.password = password;
        this.schema = schema;
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public final String getUsername() {
        return username;
    }

    @Override
    public final String getPassword() {
        return password;
    }

    @Override
    public final String getHostName() {
        return hostname;
    }

    @Override
    public final int getPort() {
        return port;
    }

    @Override
    public final String getSchemaName() {
        return schema;
    }
}
