package com.algorithmjunkie.mc.konfig.system.core;

import com.google.common.base.Preconditions;

import java.io.File;

public abstract class BaseKonfig implements Konfig {
    protected final String name;
    protected final File directory;

    public BaseKonfig(String name, File directory) {
        this.name = name;
        this.directory = directory;
    }

    /**
     * Initializes the {@link BaseKonfig}
     */
    protected final void init() {
        if (!(getFile().exists())) {
            if (!directory.exists()) {
                directory.mkdir();
            }

            createFile();
        }
    }

    /**
     * Gets a {@link File} object representing this {@link Konfig}.
     *
     * @return
     */
    public final File getFile() {
        Preconditions.checkNotNull(name, "Name cannot be null");
        Preconditions.checkNotNull(directory, "Directory cannot be null");

        return new File(
                directory,
                name
        );
    }

}
