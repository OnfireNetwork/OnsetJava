package net.onfirenetwork.onsetjava.jni.plugin;

import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.jni.HashHelper;
import net.onfirenetwork.onsetjava.plugin.Plugin;
import net.onfirenetwork.onsetjava.plugin.PluginManager;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginManagerJNI implements PluginManager {

    private List<Object> plugins = new ArrayList<>();
    private Map<Object, Plugin> infos = new HashMap<>();
    private Map<Object, File> files = new HashMap<>();
    private Map<Object, String> resourceHashes = new HashMap<>();

    public void load(File pluginFolder) {
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
            URLClassLoader classLoader = new URLClassLoader(urls);
            Onset.print("Found " + pluginFiles.size() + " Plugin Files!");
            for (File file : pluginFiles) {
                try {
                    Class<?> mainClass = null;
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
                                mainClass = clazz;
                            }
                        }
                    }
                    if (mainClass != null) {
                        Object instance = mainClass.getConstructor().newInstance();
                        plugins.add(instance);
                        files.put(instance, file);
                        resourceHashes.put(instance, HashHelper.md5(file.getName()));
                        infos.put(instance, mainClass.getAnnotationsByType(Plugin.class)[0]);
                    }
                } catch (Exception ex) {
                    Onset.print("Failed to load '" + file.getName() + "'!");
                }
            }
        } catch (MalformedURLException ex) {
        }
        int lastSize = -1;
        List<String> loaded = new ArrayList<>();
        while (loaded.size() < plugins.size() && lastSize != loaded.size()){
            for(Object plugin : plugins){
                Plugin info = infos.get(plugin);
                if(loaded.contains(info.name()))
                    continue;
                boolean con = false;
                for(String d : info.depend()){
                    if(!loaded.contains(d)){
                        con = true;
                        break;
                    }
                }
                if(con)
                    continue;
                try {
                    plugin.getClass().getMethod("onLoad").invoke(plugin);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {}
                loaded.add(info.name());
            }
            lastSize = loaded.size();
        }
        if(loaded.size() < plugins.size()){
            for(Object plugin : new ArrayList<>(plugins)){
                Plugin info = infos.get(plugin);
                if(!loaded.contains(info.name())){
                    List<String> missing = new ArrayList<>();
                    for(String d : info.depend()){
                        if(!loaded.contains(d)){
                            missing.add(d);
                        }
                    }
                    plugins.remove(plugin);
                    infos.remove(plugin);
                    files.remove(plugin);
                    Onset.print("Could not load '"+info.name()+"' (missing: "+String.join(", ", missing)+")");
                }
            }
        }
    }

    public void enable(){
        plugins.forEach(plugin -> {
            try {
                plugin.getClass().getMethod("onEnable").invoke(plugin);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public void disable(){
        plugins.forEach(plugin -> {
            try {
                plugin.getClass().getMethod("onDisable").invoke(plugin);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {}
        });
    }

    public List<Object> getPlugins() {
        return plugins;
    }

    public Object getPlugin(String name) {
        return plugins.stream().filter(plugin -> infos.get(plugin).name().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public File getFile(Object plugin) {
        return files.get(plugin);
    }

    public Plugin getInfo(Object plugin){
        return infos.get(plugin);
    }

    public String getResourceName(Object plugin, String name){
        if(!resourceHashes.containsKey(plugin))
            return null;
        return resourceHashes.get(plugin)+"/"+name;
    }

}
