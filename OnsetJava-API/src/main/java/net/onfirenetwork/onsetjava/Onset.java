package net.onfirenetwork.onsetjava;

import net.onfirenetwork.onsetjava.entity.NPC;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.entity.WorldObject;

import java.util.List;

public class Onset {
    private static Server server = null;
    public static void setServer(Server server){
        Onset.server = server;
    }
    public static Server getServer(){
        return server;
    }
    public static Player getPlayer(int id){
        return server.getPlayer(id);
    }
    public static List<Player> getPlayers(){
        return server.getPlayers();
    }
    public static Vehicle getVehicle(int id){
        return server.getVehicle(id);
    }
    public static List<Vehicle> getVehicles(){
        return server.getVehicles();
    }
    public static NPC getNPC(int id){
        return server.getNPC(id);
    }
    public static List<NPC> getNPCs(){
        return server.getNPCs();
    }
    public static WorldObject getObject(int id){
        return server.getObject(id);
    }
    public static Dimension getDimension(int id){
        return server.getDimension(id);
    }
    public static void broadcast(String message){
        server.broadcast(message);
    }
    public static void print(Object value){
        server.print(value);
    }
}
