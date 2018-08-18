package com.algorithmjunkie.mc.konfig.system;

import com.algorithmjunkie.mc.konfig.core.BaseKonfig;
import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SystemKonfig extends BaseKonfig {
    private YAMLConfigurationLoader loader;
    private ConfigurationNode config;

    public SystemKonfig(String name, File directory) {
        super(name, directory);
        init();
        loader = YAMLConfigurationLoader.builder().setPath(getFile().toPath()).build();
        try {
            System.out.println(String.format("Loading %s...", name));
            config = loader.load(ConfigurationOptions.defaults());
        } catch (IOException ex) {
            System.out.println(String.format("Failed to load %s.", name));
            ex.printStackTrace();
        }
    }

    public final YAMLConfigurationLoader getLoader() {
        return loader;
    }

    public final ConfigurationNode getConfig() {
        return config;
    }

    @Override
    public void createFile() {
        Preconditions.checkNotNull(name, "File name cannot be null");
        Preconditions.checkNotNull(directory, "Directory path cannot be null");
        if (!getFile().exists()) {
            try {
                System.out.println(String.format("Creating %s...", name));
                Files.copy(
                        getClass().getClassLoader().getResourceAsStream(name),
                        getFile().toPath()
                );
            } catch (IOException ex) {
                System.err.println(String.format("Failed to create configuration %s.", name));
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void saveFile() {
        try {
            System.out.println(String.format("Saving %s...", name));
            loader.save(config);
        } catch (IOException e) {
            System.err.println(String.format("Failed to save %s.", name));
            e.printStackTrace();
        }
    }

    @Override
    public void reload() {
        try {
            System.out.println(String.format("Reloading %s...", name));
            config = loader.load(ConfigurationOptions.defaults());
        } catch (IOException e) {
            System.err.println(String.format("Failed to reload %s.", name));
            e.printStackTrace();
        }
    }

    @Override
    public String getString(String node) {
        return node(node).getString();
    }

    @Override
    public List<String> getStringList(String node) {
        try {
            return node(node).getList(TypeToken.of(String.class));
        } catch (ObjectMappingException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Integer getInteger(String node) {
        return config.getNode(node).getInt();
    }

    @Override
    public List<Integer> getIntegerList(String node) {
        try {
            return node(node).getList(TypeToken.of(Integer.class));
        } catch (ObjectMappingException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Long getLong(String node) {
        return config.getNode(node).getLong();
    }

    @Override
    public List<Long> getLongList(String node) {
        try {
            return node(node).getList(TypeToken.of(Long.class));
        } catch (ObjectMappingException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Short getShort(String node) {
        return (short) config.getNode(node).getInt();
    }

    @Override
    public List<Short> getShortList(String node) {
        try {
            return node(node).getList(TypeToken.of(Short.class));
        } catch (ObjectMappingException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Float getFloat(String node) {
        return config.getNode(node).getFloat();
    }

    @Override
    public List<Float> getFloatList(String node) {
        try {
            return node(node).getList(TypeToken.of(Float.class));
        } catch (ObjectMappingException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Double getDouble(String node) {
        return config.getNode(node).getDouble();
    }

    @Override
    public List<Double> getDoubleList(String node) {
        try {
            return node(node).getList(TypeToken.of(Double.class));
        } catch (ObjectMappingException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Map<Object, Object> getMap(String node) {
        return node(node).getChildrenMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    @Override
    public <T> void set(String node, T value) {
        node(node).setValue(value);
    }

    private ConfigurationNode node(String name) {
        return config.getNode(name);
    }
}
