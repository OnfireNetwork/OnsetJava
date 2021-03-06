package net.onfirenetwork.onsetjava;

import net.onfirenetwork.onsetjava.entity.*;
import net.onfirenetwork.onsetjava.plugin.CommandExecutor;

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
    public static Pickup getPickup(int id){
        return server.getPickup(id);
    }
    public static List<Pickup> getPickups(){
        return server.getPickups();
    }
    public static List<Door> getDoors() { return server.getDoors(); }
    public static Text3D getText3D(int id){
        return server.getText3D(id);
    }
    public static List<Text3D> getText3Ds(){
        return server.getText3Ds();
    }
    public static List<WorldObject> getObjects(){
        return server.getObjects();
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
    public static void registerCommand(String name, CommandExecutor executor){
        server.registerCommand(name, executor);
    }
    public static void registerListener(Object listener){
        server.registerListener(listener);
    }
    public static void registerRemoteEvent(String name){
        server.registerRemoteEvent(name);
    }
    public static void delay(int millis, Runnable callback){
        server.delay(millis, callback);
    }
    @Deprecated
    public static void timer(int millis, Runnable callback){
        server.timer(millis, callback);
    }
    public static Timer createTimer(int intervalMillis, Runnable runnable){
        return server.createTimer(intervalMillis, runnable);
    }
    public static List<Timer> getTimers(){
        return server.getTimers();
    }
    public static Timer getTimer(int id){
        return server.getTimer(id);
    }
}
