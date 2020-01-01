package net.onfirenetwork.onsetjava.plugin;

import java.util.List;

public interface PluginManager {

    List<Plugin> getPlugins();
    Plugin getPlugin(String name);

}
