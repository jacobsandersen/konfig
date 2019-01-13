package com.algorithmjunkie.mc.konfig.core.database;

public class MysqlAuthAdapter extends JdbcAuthAdapter {
    private String type;

    public MysqlAuthAdapter(String type, String username, String password, String schema, String hostname) {
        this(type, username, password, schema, hostname, 3306);
    }

    public MysqlAuthAdapter(String type, String username, String password, String schema, String hostname, int port) {
        super(username, password, schema, hostname, port);
        this.type = type;
    }

    @Override
    public final String getDriverClassName() {
        switch (type) {
            case "mysql":
                return "com.mysql.jdbc.Driver";
            case "mariadb":
                return "org.mariadb.jdbc.Driver";
        }

        return "";
    }

    @Override
    public final String getJdbcUrl() {
        return String.format(
                "jdbc:%s://%s:%d/%s?autoReconnect=true&useSSL=false",
                type,
                getHostName(),
                getPort(),
                getSchemaName()
        );
    }
}
