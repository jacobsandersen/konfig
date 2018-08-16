package com.algorithmjunkie.mc.konfig.bukkit;

import com.algorithmjunkie.mc.konfig.core.BaseKonfig;
import com.google.common.base.Preconditions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BukkitKonfig extends BaseKonfig {
    private final JavaPlugin plugin;
    private YamlConfiguration configuration;

    protected BukkitKonfig(JavaPlugin plugin, String name, File directory) {
        super(name, directory);
        this.plugin = plugin;
        init();
        configuration = YamlConfiguration.loadConfiguration(getFile());
    }

    public void createFile() {
        Preconditions.checkNotNull(name, "File name cannot be null");
        if (!getFile().exists()) {
            plugin.getLogger().info(String.format("Creating configuration %s...", name));
            try {
                Files.copy(
                        plugin.getResource(name),
                        getFile().toPath()
                );
            } catch (IOException e) {
                plugin.getLogger().warning(String.format("Failed to create configuration %s!", name));
            }
        }

    }

    public void saveFile() {
        plugin.getLogger().info(String.format("Saving %s...", name));
        try {
            configuration.save(getFile());
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to save %s! Data may not be up-to-date.");
        }
    }

    public void reload() {
        plugin.getLogger().info(String.format("Reloading %s...", name));
        configuration = YamlConfiguration.loadConfiguration(getFile());
    }

    public String getString(String node) {
        return configuration.getString(node);
    }

    public List<String> getStringList(String node) {
        return configuration.getStringList(node);
    }

    public Integer getInteger(String node) {
        return configuration.getInt(node);
    }

    public List<Integer> getIntegerList(String node) {
        return configuration.getIntegerList(node);
    }

    public Long getLong(String node) {
        return configuration.getLong(node);
    }

    public List<Long> getLongList(String node) {
        return configuration.getLongList(node);
    }

    public Short getShort(String node) {
        return (short) configuration.getInt(node);
    }

    public List<Short> getShortList(String node) {
        return configuration.getShortList(node);
    }

    public Float getFloat(String node) {
        return (float) configuration.getDouble(node);
    }

    public List<Float> getFloatList(String node) {
        return configuration.getFloatList(node);
    }

    public Double getDouble(String node) {
        return configuration.getDouble(node);
    }

    public List<Double> getDoubleList(String node) {
        return configuration.getDoubleList(node);
    }

    public Map<Object, Object> getMap(String node) {
        Map<Object, Object> ret = new HashMap<>();
        for (Map<?, ?> map : configuration.getMapList(node)) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                ret.put(entry.getKey(), entry.getValue());
            }
        }
        return ret;
    }

    public <T> void set(String node, T value) {
        configuration.set(node, value);
    }
}
