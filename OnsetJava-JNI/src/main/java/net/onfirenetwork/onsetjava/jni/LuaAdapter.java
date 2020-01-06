package net.onfirenetwork.onsetjava.jni;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.ExportFunction;
import net.onfirenetwork.onsetjava.plugin.event.Cancellable;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.player.PlayerRemoteEvent;

import java.util.Map;

public class LuaAdapter {

    public static native void callEvent(String name, Object... args);
    public static native Object[] callGlobalFunction(String packageName, String name, Object... args);
    public static void init(String packageName){
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

}
