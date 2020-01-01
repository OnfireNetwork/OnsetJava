package net.onfirenetwork.onsetjava.plugin;

public class PluginInfo {

    private String name;
    private String version;
    private String author;
    private String[] dependencies;

    public PluginInfo(String name, String version, String author, String... dependencies){
        this.name = name;
        this.version = version;
        this.author = author;
        this.dependencies = dependencies;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public String[] getDependencies() {
        return dependencies;
    }

    public String toString() {
        return name + " (" + version + " by " + author + ")";
    }

}
