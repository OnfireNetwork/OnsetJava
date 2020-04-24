package net.onfirenetwork.onsetjava.jni.cli;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.onfirenetwork.onsetjava.jni.HashHelper;
import net.onfirenetwork.onsetjava.plugin.Plugin;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.*;
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

    private static Plugin getInfo(File file){
        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
            JarFile jf = new JarFile(file);
            Enumeration<JarEntry> en = jf.entries();
            while (en.hasMoreElements()) {
                JarEntry element = en.nextElement();
                if (element.getName().endsWith(".class")) {
                    if(element.getName().equals("module-info.class")){
                        continue;
                    }
                    Class<?> clazz = classLoader.loadClass(element.getName().replace("/", ".").substring(0, element.getName().length() - 6));
                    if (clazz.isAnnotationPresent(Plugin.class)) {
                        Plugin plugin = clazz.getAnnotationsByType(Plugin.class)[0];
                        classLoader.close();
                        jf.close();
                        return plugin;
                    }
                }
            }
            classLoader.close();
        } catch (Exception ex) {
        }
        return null;
    }

    private static void writePluginsScript(Map<String, String> pluginHashes){
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        sb.append("local pluginHashes = {");
        for(String key : pluginHashes.keySet()){
            if(!first){
                sb.append(",");
            }else{
                first = false;
            }
            sb.append("\n\t[\"");
            sb.append(key);
            sb.append("\"] = \"");
            sb.append(pluginHashes.get(key));
            sb.append("\"");
        }
        sb.append("\n}\nfunction GetPluginHash(name)\n\treturn pluginHashes[name]\nend");
        try {
            FileOutputStream fos = new FileOutputStream(new File("packages/java/plugins.lua"));
            fos.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            fos.flush();
            fos.close();
        }catch (IOException ex){}
    }

    private static void exportResources(File pluginFolder){
        List<String> files = new ArrayList<>();
        List<String> client = new ArrayList<>();
        List<File> pluginFiles = new ArrayList<>();
        Map<String, String> pluginHashes = new HashMap<>();
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
                Plugin info = getInfo(file);
                if(info == null)
                    continue;
                String pluginHash = HashHelper.md5(file.getName());
                pluginHashes.put(info.name(), pluginHash);
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
                        if(element.getName().endsWith("/"))
                            continue;
                        if(element.getName().startsWith("files/")){
                            String name = element.getName().substring(6);
                            files.add(pluginHash+"/"+name);
                            File targetFile = new File(resourceFolder, name);
                            mkdir(targetFile.getAbsoluteFile().getParentFile());
                            FileOutputStream fos = new FileOutputStream(targetFile);
                            InputStream is = jf.getInputStream(element);
                            while (is.available() > 0){
                                byte[] data = new byte[Math.min(4096, is.available())];
                                int r = is.read(data);
                                fos.write(data, 0, r);
                                fos.flush();
                            }
                            is.close();
                            fos.close();
                        }
                        if(element.getName().startsWith("client/")){
                            String name = element.getName().substring(7);
                            client.add(pluginHash+"/"+name);
                            File targetFile = new File(resourceFolder, name);
                            mkdir(targetFile.getAbsoluteFile().getParentFile());
                            FileOutputStream fos = new FileOutputStream(targetFile);
                            InputStream is = jf.getInputStream(element);
                            while (is.available() > 0){
                                byte[] data = new byte[Math.min(4096, is.available())];
                                int r = is.read(data);
                                fos.write(data, 0, r);
                                fos.flush();
                            }
                            is.close();
                            fos.close();
                        }
                    }
                } catch (Exception ex) {
                }
            }
        } catch (MalformedURLException ex) {
        }
        writePluginsScript(pluginHashes);
        writePackageConfig(files, client);
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
                int r = is.read(data);
                fos.write(data, 0, r);
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
                int r = is.read(data);
                fos.write(data, 0, r);
                fos.flush();
            }
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writePackageConfig(List<String> files, List<String> client){
        JsonArray filesJson = new JsonArray();
        for(String file : files)
            filesJson.add(file);
        JsonArray clientFiles = new JsonArray();
        clientFiles.add("client.lua");
        clientFiles.add("plugins.lua");
        for(String file : client)
            clientFiles.add(file);
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
            exportResources(pluginFolder);
        }
    }

    public static void main(String [] args){
        install();
    }

}
