package com.algorithmjunkie.mc.konfig.core;

import com.algorithmjunkie.mc.konfig.core.database.AuthAdapter;
import com.algorithmjunkie.mc.konfig.core.database.MysqlAuthAdapter;
import com.algorithmjunkie.mc.konfig.core.database.RedisAuthAdapter;
import com.algorithmjunkie.mc.konfig.core.database.SecureRedisAuthAdapter;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface Konfig {
    File getFile();

    void createFile();

    void saveFile();

    void reload();

    String getString(String node);

    List<String> getStringList(String node);

    Integer getInteger(String node);

    List<Integer> getIntegerList(String node);

    Long getLong(String node);

    List<Long> getLongList(String node);

    Short getShort(String node);

    List<Short> getShortList(String node);

    Float getFloat(String node);

    List<Float> getFloatList(String node);

    Double getDouble(String node);

    List<Double> getDoubleList(String node);

    /**
     * Gets AuthAdapter from Konfig file's root
     * @return an {@link AuthAdapter} or null
     */
    default AuthAdapter getAuthAdapter() {
        String type = getString("type");
        switch (type) {
            case "redis":
                return new RedisAuthAdapter(
                        getString("hostname"),
                        getInteger("port")
                );
            case "rediss":
                return new SecureRedisAuthAdapter(
                        getString("hostname"),
                        getString("password"),
                        getInteger("port")
                );
            case "mysql":
                return new MysqlAuthAdapter(
                        getString("username"),
                        getString("password"),
                        getString("schema"),
                        getString("hostname"),
                        getInteger("port")
                );
            default:
                return null;
        }
    }

    Map<Object, Object> getMap(String node);

    <T> void set(String node, T value);
}