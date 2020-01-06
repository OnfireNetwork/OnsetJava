package net.onfirenetwork.onsetjava.jni.cli;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.onfirenetwork.onsetjava.jni.HashHelper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class OnsetJavaCLI {

    private static void deleteRecursive(File file) {
        if (!file.exists())
            return;
        if (file.isFile()) {
            file.delete();
            return;
        }
        for (File f : file.listFiles()) {
            deleteRecursive(f);
        }
        file.delete();
    }

    private static void mkdir(File file) {
        if (file.exists()) {
            return;
        }
        File parent = file.getAbsoluteFile().getParentFile();
        if (!parent.exists()) {
            mkdir(parent);
        }
        file.mkdir();
    }

    private static List<String> exportResources(File pluginFolder){
        List<String> files = new ArrayList<>();
        List<File> pluginFiles = new ArrayList<>();
        for (File file : pluginFolder.listFiles()) {
            if (file.isDirectory())
                continue;
            if (!file.getName().endsWith(".jar"))
                continue;
            pluginFiles.add(file);
        }
        try {
            URL[] urls = new URL[pluginFiles.size()];
            for (int i = 0; i < urls.length; i++) {
                urls[i] = pluginFiles.get(i).toURI().toURL();
            }
            for (File file : pluginFiles) {
                String pluginHash = HashHelper.md5(file.getName());
                File resourceFolder = new File("packages/java/"+pluginHash);
                if(resourceFolder.exists()){
                    deleteRecursive(resourceFolder);
                }
                mkdir(resourceFolder);
                try {
                    JarFile jf = new JarFile(file);
                    Enumeration<JarEntry> en = jf.entries();
                    while (en.hasMoreElements()) {
                        JarEntry element = en.nextElement();
                        if(!element.getName().startsWith("files/"))
                            continue;
                        if(element.getName().endsWith("/"))
                            continue;
                        String name = element.getName().substring(6);
                        files.add(pluginHash+"/"+name);
                        File targetFile = new File(resourceFolder, name);
                        mkdir(targetFile.getAbsoluteFile().getParentFile());
                        FileOutputStream fos = new FileOutputStream(targetFile);
                        InputStream is = jf.getInputStream(element);
                        while (is.available() > 0){
                            byte[] data = new byte[Math.min(4096, is.available())];
                            is.read(data);
                            fos.write(data);
                            fos.flush();
                        }
                        is.close();
                        fos.close();
                    }
                } catch (Exception ex) {
                }
            }
        } catch (MalformedURLException ex) {
        }
        return files;
    }

    private static void exportPackage(){
        File packageDirectory = new File("packages/java");
        deleteRecursive(packageDirectory);
        mkdir(packageDirectory);
        try {
            InputStream is = OnsetJavaCLI.class.getClassLoader().getResourceAsStream("package/server.lua");
            FileOutputStream fos = new FileOutputStream(new File(packageDirectory, "server.lua"));
            while (is.available() > 0){
                byte[] data = new byte[Math.min(4096, is.available())];
                is.read(data);
                fos.write(data);
                fos.flush();
            }
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream is = OnsetJavaCLI.class.getClassLoader().getResourceAsStream("package/client.lua");
            FileOutputStream fos = new FileOutputStream(new File(packageDirectory, "client.lua"));
            while (is.available() > 0){
                byte[] data = new byte[Math.min(4096, is.available())];
                is.read(data);
                fos.write(data);
                fos.flush();
            }
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writePackageConfig(List<String> files){
        JsonArray filesJson = new JsonArray();
        for(String file : files)
            filesJson.add(file);
        JsonArray clientFiles = new JsonArray();
        clientFiles.add("client.lua");
        JsonArray serverFiles = new JsonArray();
        serverFiles.add("server.lua");
        JsonObject json = new JsonObject();
        json.addProperty("author", "Onfire Network");
        json.addProperty("version", "1.0");
        json.add("server_scripts", serverFiles);
        json.add("client_scripts", clientFiles);
        json.add("files", filesJson);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileOutputStream fos = new FileOutputStream(new File("packages/java/package.json"));
            fos.write(gson.toJson(json).getBytes(StandardCharsets.UTF_8));
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void install(){
        exportPackage();
        File javaFolder = new File("java");
        if(!javaFolder.exists()){
            javaFolder.mkdir();
        }
        File pluginFolder = new File(javaFolder, "plugins");
        if(!pluginFolder.exists()){
            pluginFolder.mkdir();
        }else{
            writePackageConfig(exportResources(pluginFolder));
        }
    }

    public static void main(String [] args){
        install();
    }

}
