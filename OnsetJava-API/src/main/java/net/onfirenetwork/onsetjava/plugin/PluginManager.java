package net.onfirenetwork.onsetjava.plugin;

import java.util.List;

public interface PluginManager {

    List<Object> getPlugins();
    Object getPlugin(String name);
    String getResourceName(Object plugin, String name);
    default String getResourceURL(Object plugin, String name){
        return "http://asset/java/"+getResourceName(plugin, name);
    }

}
