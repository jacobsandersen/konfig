package com.algorithmjunkie.mc.konfig.system.bungee;

import com.algorithmjunkie.mc.konfig.core.BaseKonfig;
import com.google.common.base.Preconditions;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class BungeeKonfig extends BaseKonfig<Configuration> {
    private final Plugin plugin;
    private final ConfigurationProvider provider;
    private Configuration configuration;

    protected BungeeKonfig(Plugin plugin, String name, File directory) {
        super(name, directory);
        this.plugin = plugin;
        init();
        provider = ConfigurationProvider.getProvider(YamlConfiguration.class);
        try {
            configuration = provider.load(getFile());
        } catch (IOException ex) {
            plugin.getLogger().log(Level.WARNING, String.format("Failed to load %s!", name), ex);
        }
    }

    @Override
    public void createFile() {
        Preconditions.checkNotNull(name, "File name cannot be null");
        Preconditions.checkNotNull(directory, "Directory path cannot be null");

        if (!getFile().exists()) {
            plugin.getLogger().info(String.format("Creating configuration %s...", name));
            try {
                Files.copy(
                        plugin.getResourceAsStream(name),
                        getFile().toPath()
                );
            } catch (IOException ex) {
                plugin.getLogger().log(Level.WARNING, String.format("Failed to create configuration %s!", name), ex);
            }
        }
    }

    @Override
    public void saveFile() {
        plugin.getLogger().info(String.format("Saving %s...", name));
        try {
            provider.save(configuration, getFile());
        } catch (IOException ex) {
            plugin.getLogger().log(Level.WARNING, String.format("Failed to save %s! Data may not be accurate.", name), ex);
        }
    }

    @Override
    public void reload() {
        plugin.getLogger().info(String.format("Reloading %s...", name));
        try {
            configuration = provider.load(getFile());
        } catch (IOException ex) {
            plugin.getLogger().log(Level.WARNING, String.format("Failed to reload %s! Data may not be up to date.", name), ex);
        }
    }

    @Override
    public Configuration getBackend() {
        return configuration;
    }

    @Override
    public String getString(String node) {
        return configuration.getString(node);
    }

    @Override
    public List<String> getStringList(String node) {
        return configuration.getStringList(node);
    }

    @Override
    public Boolean getBoolean(String node) {
        return configuration.getBoolean(node);
    }

    @Override
    public List<Boolean> getBooleanList(String node) {
        return configuration.getBooleanList(node);
    }

    @Override
    public Integer getInteger(String node) {
        return configuration.getInt(node);
    }

    @Override
    public List<Integer> getIntegerList(String node) {
        return configuration.getIntList(node);
    }

    @Override
    public Long getLong(String node) {
        return configuration.getLong(node);
    }

    @Override
    public List<Long> getLongList(String node) {
        return configuration.getLongList(node);
    }

    @Override
    public Short getShort(String node) {
        return configuration.getShort(node);
    }

    @Override
    public List<Short> getShortList(String node) {
        return configuration.getShortList(node);
    }

    @Override
    public Float getFloat(String node) {
        return configuration.getFloat(node);
    }

    @Override
    public List<Float> getFloatList(String node) {
        return configuration.getFloatList(node);
    }

    @Override
    public Double getDouble(String node) {
        return configuration.getDouble(node);
    }

    @Override
    public List<Double> getDoubleList(String node) {
        return configuration.getDoubleList(node);
    }

    @Override
    public Map<Object, Object> getMap(String node) {
        Map<Object, Object> ret = new HashMap<>();
        Configuration section = configuration.getSection(node);
        for (String key : section.getKeys()) {
            ret.put(
                    key,
                    section.get(key)
            );
        }
        return ret;
    }

    @Override
    public <T> void set(String node, T value) {
        configuration.set(node, value);
    }
}
