package com.inzo.technologies.inzoware.mc.parsers;

import com.inzo.technologies.inzoware.mc.parsers.util.FileSystemHelper;

public interface JSONObject {
    String sep = FileSystemHelper.getSeparator();
    String mcFolder = FileSystemHelper.getMinecraftFolder();
    String home = System.getProperty("user.home");

    void handle();

    String getName();
}
