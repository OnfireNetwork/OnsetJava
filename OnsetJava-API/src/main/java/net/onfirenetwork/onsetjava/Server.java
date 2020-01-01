package net.onfirenetwork.onsetjava;

import net.onfirenetwork.onsetjava.data.Location;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.*;
import net.onfirenetwork.onsetjava.plugin.PluginManager;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.CommandExecutor;

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
    void registerCommand(String name, CommandExecutor executor);
    void callEvent(Event event);
    Dimension getDimension(int id);
    void broadcast(String message);
    void print(Object value);
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

}
