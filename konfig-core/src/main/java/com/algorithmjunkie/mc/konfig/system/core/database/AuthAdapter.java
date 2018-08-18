package com.algorithmjunkie.mc.konfig.system.core.database;

public interface AuthAdapter {
    String getDriverClassName();

    String getUsername();

    String getPassword();

    String getHostName();

    int getPort();

    String getSchemaName();

    String getJdbcUrl();
}
