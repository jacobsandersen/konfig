package com.algorithmjunkie.mc.konfig.system.core.database;

public class MysqlAuthAdapter extends JdbcAuthAdapter {
    public MysqlAuthAdapter(String username, String password, String schema, String hostname) {
        this(username, password, schema, hostname, 3306);
    }

    public MysqlAuthAdapter(String username, String password, String schema, String hostname, int port) {
        super(username, password, schema, hostname, port);
    }

    @Override
    public final String getDriverClassName() {
        return "com.mysql.jdbc.Driver";
    }

    @Override
    public final String getJdbcUrl() {
        return String.format(
                "jdbc:mysql://%s:%d/%s?autoReconnect=true&useSSL=false",
                getHostName(),
                getPort(),
                getSchemaName()
        );
    }
}
