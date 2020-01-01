package net.onfirenetwork.onsetjava.jni;

import net.onfirenetwork.onsetjava.plugin.CommandExecutor;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.EventHandler;
import net.onfirenetwork.onsetjava.plugin.event.EventListener;
import net.onfirenetwork.onsetjava.plugin.event.player.PlayerQuitEvent;
import net.onfirenetwork.onsetjava.plugin.event.player.PlayerServerAuthEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageBus {

    private Map<String, List<CommandExecutor>> commandMap = new HashMap<>();
    private List<Class<? extends Event>> registeredEvents = new ArrayList<>();
    private List<String> registeredRemoteEvents = new ArrayList<>();
    private Map<EventListener, Map<Class<? extends Event>, List<Method>>> handlerMaps = new HashMap<>();

    public void init(){
        registerLuaEvent(PlayerServerAuthEvent.class);
        registerLuaEvent(PlayerQuitEvent.class);
    }

    private void registerLuaEvent(Class<? extends Event> clazz){
        if(registeredEvents.contains(clazz))
            return;
        registeredEvents.add(clazz);
        String luaName = Event.name(clazz);
        if(luaName == null)
            return;
        ServerJNI.callGlobal("RegisterJavaEvent", luaName);
    }

    public void registerRemoteEvent(String name){
        if(registeredRemoteEvents.contains(name))
            return;
        registeredRemoteEvents.add(name);
        ServerJNI.callGlobal("RegisterJavaRemoteEvent", name);
    }

    public void callEvent(Event event){
        Class<? extends Event> eventClass = event.getClass();
        for(EventListener listener : handlerMaps.keySet()){
            if(!handlerMaps.get(listener).containsKey(eventClass)) {
                return;
            }
            for(Method handlerMethod : handlerMaps.get(listener).get(eventClass)){
                try {
                    handlerMethod.invoke(listener, event);
                }catch (Throwable t){
                    t.printStackTrace();
                }
            }
        }
    }

    public void registerListener(EventListener listener){
        Map<Class<? extends Event>, List<Method>> handlerMap = new HashMap<>();
        for(Method method : listener.getClass().getMethods()){
            if(method.getParameterCount()!=1)
                continue;
            if(!method.isAnnotationPresent(EventHandler.class))
                continue;
            if(!Event.class.isAssignableFrom(method.getParameterTypes()[0]))
                continue;
            Class<? extends Event> eventType = (Class<? extends Event>) method.getParameterTypes()[0];
            if(!handlerMap.containsKey(eventType)){
                handlerMap.put(eventType, new ArrayList<>());
                registerLuaEvent(eventType);
            }
            List<Method> handlerMethods = handlerMap.get(eventType);
            handlerMethods.add(method);
            handlerMap.put(eventType, handlerMethods);
        }
        handlerMaps.put(listener, handlerMap);
    }

    public void registerCommand(String name, CommandExecutor executor){
        List<CommandExecutor> executors = commandMap.get(name);
        if(executors == null) {
            executors = new ArrayList<>();
            ServerJNI.callGlobal("RegisterJavaCommand", name);
        }
        if(!executors.contains(executor))
            executors.add(executor);
        commandMap.put(name, executors);
    }

    public void dispatchCommand(Player player, String name, String[] args){
        if(!commandMap.containsKey(name))
            return;
        List<CommandExecutor> executors = commandMap.get(name);
        for(CommandExecutor executor : executors){
            if(executor.onCommand(player, name, args)) {
                return;
            }
        }
    }

}
