package net.onfirenetwork.onsetjava.plugin.event;

public class UnknownEvent extends Event {
    private String name;
    private Object[] args;
    public UnknownEvent(String name, Object[] args){
        this.name = name;
        this.args = args;
    }
    public String getName() {
        return name;
    }
    public Object[] getArgs() {
        return args;
    }
}
