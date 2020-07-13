package net.onfirenetwork.onsetjava.jni;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.i18n.I18N;
import net.onfirenetwork.onsetjava.i18n.I18NPlugin;
import net.onfirenetwork.onsetjava.plugin.event.Cancellable;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.player.PlayerRemoteEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuaAdapter {

    private static Thread mainThread;

    public static native void callEvent(String name, Object... args);
    public static Object[] callGlobal(String packageName, String name, Object... args){
        if(!Thread.currentThread().equals(mainThread))
            throw new RuntimeException("You can't call lua functions outside the main thread!");
        List<Object> returnValues = new ArrayList<>();
        callGlobalFunction(packageName, name, args, returnValues);
        return returnValues.toArray();
    }
    private static native Object[] callGlobalFunction(String packageName, String name, Object[] args, List<Object> returnValues);
    public static void init(String packageName){
        mainThread = Thread.currentThread();
        ServerJNI.init(packageName);
    }
    private static Object[] tableArray(Map<Integer, Object> table){
        Object[] objects = new Object[table.size()];
        for(Integer i : table.keySet()){
            objects[i-1] = table.get(i);
        }
        return objects;
    }
    public static Boolean luaEvent(String name, Map<Integer, Object> args){
        try {
            Event event = EventTransformer.transform(name, tableArray(args));
            ServerJNI.getInstance().callEvent(event);
            if(event instanceof Cancellable){
                return !((Cancellable) event).isCancelled();
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
        return true;
    }
    public static void luaRemoteEvent(String name, Map<Integer, Object> args){
        Object[] argsArray = tableArray(args);
        int playerId = (Integer) argsArray[0];
        Object[] eventArgs = new Object[argsArray.length-1];
        for(int i=0; i<eventArgs.length; i++){
            eventArgs[i] = argsArray[i+1];
        }
        Player player = ServerJNI.getInstance().getPlayer(playerId);
        if(player == null)
            return;
        if(name.equals("RequestTranslation")){
            for(String pn : I18N.getKnownPlugins()){
                I18NPlugin pl = I18N.get(pn);
                List<Map<String, String>> array = new ArrayList<>();
                Map<String, String> object = new HashMap<>();
                for(String k : pl.keys()){
                    object.put(k, pl.raw(k));
                    if(object.size() == 1000){
                        array.add(object);
                        object = new HashMap<>();
                    }
                }
                array.add(object);
                player.callRemoteEvent("__java_i18n__", pn, array);
            }
            return;
        }
        ServerJNI.getInstance().callEvent(new PlayerRemoteEvent(player, name, eventArgs));
    }
    public static void luaCommand(String name, Map<Integer, Object> args){
        Object[] argsArray = tableArray(args);
        int playerId = (Integer) argsArray[0];
        String[] cmdArgs = new String[argsArray.length-1];
        for(int i=0; i<cmdArgs.length; i++){
            cmdArgs[i] = (String) argsArray[i+1];
        }
        Player player = ServerJNI.getInstance().getPlayer(playerId);
        if(player == null)
            return;
        ServerJNI.getInstance().packageBus.dispatchCommand(player, name, cmdArgs);
    }
    public static Object callExportFunction(String name, Map<Integer, Object> argsMap){
        return ServerJNI.getInstance().packageBus.callExportFunction(name, argsMap);
    }
    public static void callDelay(Integer id){
        ServerJNI.getInstance().packageBus.callDelay(id);
    }
    public static void callTimer(Integer id){
        ServerJNI.getInstance().packageBus.callTimer(id);
    }
}
