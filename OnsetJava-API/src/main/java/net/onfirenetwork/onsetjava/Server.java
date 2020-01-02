package net.onfirenetwork.onsetjava;

import net.onfirenetwork.onsetjava.data.Location;
import net.onfirenetwork.onsetjava.data.NetworkStats;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.*;
import net.onfirenetwork.onsetjava.plugin.ExportFunction;
import net.onfirenetwork.onsetjava.plugin.PluginManager;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.CommandExecutor;
import net.onfirenetwork.onsetjava.plugin.event.EventListener;

import java.util.List;

public interface Server {

    String getPackageName();
    String getServerName();
    void setServerName(String name);
    int getMaxPlayers();
    int getGameVersion();
    String getGameVersionString();
    Player getPlayer(int id);
    List<Player> getPlayers();
    Vehicle getVehicle(int id);
    List<Vehicle> getVehicles();
    NPC getNPC(int id);
    List<NPC> getNPCs();
    WorldObject getObject(int id);
    List<Pickup> getPickups();
    Pickup getPickup(int id);
    List<Text3D> getText3Ds();
    Text3D getText3D(int id);
    void registerCommand(String name, CommandExecutor executor);
    void callEvent(Event event);
    <T> T importLuaPackage(String name, Class<T> interfaceClass);
    void addFunctionExport(String name, ExportFunction function);
    Dimension getDimension(int id);
    void broadcast(String message);
    void print(Object value);
    void registerListener(EventListener listener);
    PluginManager getPluginManager();
    default WorldObject createObject(Vector location, int model){
        return createObject(location.getX(), location.getY(), location.getZ(), model);
    }
    WorldObject createObject(double x, double y, double z, int model);
    default Vehicle createVehicle(Location location, int model){
        return createVehicle(location.getX(), location.getY(), location.getZ(), location.getHeading(), model);
    }
    default Vehicle createVehicle(Vector location, double heading, int model){
        return createVehicle(location.getX(), location.getY(), location.getZ(), heading, model);
    }
    Vehicle createVehicle(double x, double y, double z, double heading, int model);
    default NPC createNPC(Location location){
        return createNPC(location.getX(), location.getY(), location.getZ(), location.getHeading());
    }
    default NPC createNPC(Vector location, double heading){
        return createNPC(location.getX(), location.getY(), location.getZ(), heading);
    }
    NPC createNPC(double x, double y, double z, double heading);
    default Door createDoor(Location location, int model){
        return createDoor(location.getX(), location.getY(), location.getZ(), location.getHeading(), model);
    }
    default Door createDoor(Location location, int model, boolean enableOpen){
        return createDoor(location.getX(), location.getY(), location.getZ(), location.getHeading(), model, enableOpen);
    }
    default Door createDoor(Vector location, double heading, int model){
        return createDoor(location.getX(), location.getY(), location.getZ(), heading, model);
    }
    default Door createDoor(Vector location, double heading, int model, boolean enableOpen){
        return createDoor(location.getX(), location.getY(), location.getZ(), heading, model, enableOpen);
    }
    default Door createDoor(double x, double y, double z, double heading, int model){
        return createDoor(x, y, z, heading, model, false);
    }
    Door createDoor(double x, double y, double z, double heading, int model, boolean enableOpen);
    default Pickup createPickup(Vector location, int model){
        return createPickup(location.getX(), location.getY(), location.getZ(), model);
    }
    Pickup createPickup(double x, double y, double z, int model);
    default Text3D createText3D(String text, double size, Vector location, Vector rotation){
        return createText3D(text, size, location.getX(), location.getY(), location.getZ(), rotation.getX(), rotation.getY(), rotation.getZ());
    }
    Text3D createText3D(String text, double size, double x, double y, double z, double rx, double ry, double rz);
    default void createExplosion(int dimension, Vector location, int type){
        createExplosion(dimension, location.getX(), location.getY(), location.getZ(), type);
    }
    default void createExplosion(int dimension, double x, double y, double z, int type){
        createExplosion(dimension, x, y, z, type, true);
    }
    default void createExplosion(int dimension, Vector location, int type, boolean soundExplosion){
        createExplosion(dimension, location.getX(), location.getY(), location.getZ(), type, soundExplosion);
    }
    void createExplosion(int dimension, double x, double y, double z, int type, boolean soundExplosion);
    default void createExplosion(int dimension, Vector location, int type, boolean soundExplosion, double camShakeRadius){
        createExplosion(dimension, location.getX(), location.getY(), location.getZ(), type, soundExplosion, camShakeRadius);
    }
    void createExplosion(int dimension, double x, double y, double z, int type, boolean soundExplosion, double camShakeRadius);
    default void createExplosion(int dimension, Vector location, int type, boolean soundExplosion, double camShakeRadius, double radialForce){
        createExplosion(dimension, location.getX(), location.getY(), location.getZ(), type, soundExplosion, camShakeRadius, radialForce);
    }
    void createExplosion(int dimension, double x, double y, double z, int type, boolean soundExplosion, double camShakeRadius, double radialForce);
    NetworkStats getNetworkStats();
}
