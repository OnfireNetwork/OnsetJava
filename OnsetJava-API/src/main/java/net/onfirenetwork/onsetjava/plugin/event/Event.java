package net.onfirenetwork.onsetjava.plugin.event;

public abstract class Event {
    public static String name(Class<? extends Event> clazz){
        LuaEvent[] annotations = clazz.getDeclaredAnnotationsByType(LuaEvent.class);
        if(annotations.length == 1){
            return annotations[0].value();
        }
        return null;
    }
    public static String name(Event event){
        return name(event.getClass());
    }
}
