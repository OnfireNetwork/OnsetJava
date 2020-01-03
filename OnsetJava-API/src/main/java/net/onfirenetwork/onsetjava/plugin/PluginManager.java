package net.onfirenetwork.onsetjava.plugin;

import java.util.List;

public interface PluginManager {

    List<Object> getPlugins();
    Object getPlugin(String name);

}
