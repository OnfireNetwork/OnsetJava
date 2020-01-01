package net.onfirenetwork.onsetjava.jni.plugin;

import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.plugin.Plugin;
import net.onfirenetwork.onsetjava.plugin.PluginInfo;
import net.onfirenetwork.onsetjava.plugin.PluginManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginManagerJNI implements PluginManager {

    private List<Plugin> plugins = new ArrayList<>();
    private Map<Plugin, PluginInfo> infos = new HashMap<>();
    private Map<Plugin, File> files = new HashMap<>();

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
                    Class<Plugin> mainClass = null;
                    JarFile jf = new JarFile(file);
                    Enumeration<JarEntry> en = jf.entries();
                    while (en.hasMoreElements()) {
                        JarEntry element = en.nextElement();
                        if (element.getName().endsWith(".class")) {
                            Class<?> clazz = classLoader.loadClass(element.getName().replace("/", ".").substring(0, element.getName().length() - 6));
                            if (Arrays.asList(clazz.getInterfaces()).contains(Plugin.class)) {
                                mainClass = (Class<Plugin>) clazz;
                            }
                        }
                    }
                    if (mainClass != null) {
                        Plugin instance = mainClass.getConstructor().newInstance();
                        plugins.add(instance);
                        files.put(instance, file);
                        infos.put(instance, instance.info());
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
            for(Plugin plugin : plugins){
                PluginInfo info = infos.get(plugin);
                if(loaded.contains(info.getName()))
                    continue;
                boolean con = false;
                for(String d : info.getDependencies()){
                    if(!loaded.contains(d)){
                        con = true;
                        break;
                    }
                }
                if(con)
                    continue;
                plugin.onLoad();
                loaded.add(info.getName());
            }
            lastSize = loaded.size();
        }
        if(loaded.size() < plugins.size()){
            for(Plugin plugin : new ArrayList<>(plugins)){
                PluginInfo info = infos.get(plugin);
                if(!loaded.contains(info.getName())){
                    List<String> missing = new ArrayList<>();
                    for(String d : info.getDependencies()){
                        if(!loaded.contains(d)){
                            missing.add(d);
                        }
                    }
                    plugins.remove(plugin);
                    infos.remove(plugin);
                    files.remove(plugin);
                    Onset.print("Could not load '"+info.getName()+"' (missing: "+String.join(", ", missing)+")");
                }
            }
        }
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }

    public Plugin getPlugin(String name) {
        return plugins.stream().filter(plugin -> plugin.info().getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public File getFile(Plugin plugin) {
        return files.get(plugin);
    }

    public PluginInfo getInfo(Plugin plugin){
        return infos.get(plugin);
    }

}
