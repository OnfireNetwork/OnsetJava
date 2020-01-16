package net.onfirenetwork.onsetjava.i18n;

import net.onfirenetwork.onsetjava.Onset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class I18N {

    private static String fallback = "translation-error";
    private static Map<String, I18NPlugin> packageMap = new HashMap<>();
    private static Map<String, String> packageNameMap = new HashMap<>();

    public static String translate(String plugin, String key, Object... args){
        if(!packageMap.containsKey(plugin))
            return fallback;
        String ts = packageMap.get(plugin).translate(key, args);
        if(ts == null)
            return fallback;
        return ts;
    }
    public static String translate(Object plugin, String key, Object... args){
        return translate(Onset.getServer().getPluginManager().getInfo(plugin).name(), key, args);
    }
    public static String translate(String key, Object... args){
        for(StackTraceElement e : Thread.currentThread().getStackTrace()){
            for(String packageName : packageNameMap.keySet()){
                if(e.getClassName().startsWith(packageName)){
                    return translate(packageNameMap.get(packageName), key, args);
                }
            }
        }
        return fallback;
    }
    public static String t(String plugin, String key, Object... args){
        return translate(plugin, key, args);
    }
    public static String t(Object plugin, String key, Object... args){
        return translate(plugin, key, args);
    }
    public static String t(String key, Object... args){
        return translate(key, args);
    }
    public static void put(String plugin, I18NPlugin i18NPackage){
        packageMap.put(plugin, i18NPackage);
    }
    public static void registerPackage(Object plugin, String packageName){
        registerPackage(Onset.getServer().getPluginManager().getInfo(plugin).name(), packageName);
    }
    public static void registerPackage(String plugin, String packageName){
        packageNameMap.put(packageName, plugin);
    }
    public static void setFallback(String fallback){
        I18N.fallback = fallback;
    }
    public static List<String> getKnownPlugins(){
        return new ArrayList<>(packageMap.keySet());
    }
    public static I18NPlugin get(String plugin){
        return packageMap.get(plugin);
    }
}
