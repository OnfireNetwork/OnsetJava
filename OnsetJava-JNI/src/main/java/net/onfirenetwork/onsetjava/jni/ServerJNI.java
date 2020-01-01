package net.onfirenetwork.onsetjava.jni;

import net.onfirenetwork.onsetjava.entity.*;
import net.onfirenetwork.onsetjava.jni.entity.*;
import net.onfirenetwork.onsetjava.jni.plugin.PluginManagerJNI;
import net.onfirenetwork.onsetjava.plugin.CommandExecutor;
import net.onfirenetwork.onsetjava.Dimension;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.Server;
import net.onfirenetwork.onsetjava.plugin.PluginManager;
import net.onfirenetwork.onsetjava.plugin.event.Event;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerJNI implements Server {

    private static ServerJNI instance;

    public static ServerJNI getInstance() {
        return instance;
    }

    public static void init(String packageName){
        instance = new ServerJNI(packageName);
        Onset.setServer(instance);
        instance.packageBus.init();
        File pluginFolder = new File("java/plugins");
        if(!pluginFolder.exists())
            pluginFolder.mkdir();
        instance.pluginManager.load(pluginFolder);
    }

    public static Object[] callGlobal(String name, Object... args){
        return LuaAdapter.callGlobalFunction(instance.packageName, name, args);
    }

    private String packageName;
    private List<Player> players = new ArrayList<>();
    public PackageBus packageBus = new PackageBus();
    public PluginManagerJNI pluginManager = new PluginManagerJNI();

    public ServerJNI(String packageName){
        this.packageName = packageName;
    }

    public void addPlayer(Player player){
        if(!players.contains(player))
            players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public String getPackageName(){
        return packageName;
    }

    public String getServerName() {
        return (String) LuaAdapter.callGlobalFunction(packageName, "GetServerName")[0];
    }

    public void setServerName(String name) {
        callGlobal("SetServerName", name);
    }

    public int getMaxPlayers() {
        return (Integer) callGlobal(packageName, "GetMaxPlayers")[0];
    }

    public int getGameVersion() {
        return (Integer) callGlobal(packageName, "GetGameVersion")[0];
    }

    public String getGameVersionString() {
        return (String) callGlobal(packageName, "GetGameVersionString")[0];
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int id){
        for(Player player : players){
            if(player.getId() == id)
                return player;
        }
        return null;
    }

    public Vehicle getVehicle(int id){
        if(!((Boolean) callGlobal("IsValidVehicle", id)[0]))
            return null;
        return new VehicleJNI(id);
    }

    public List<Vehicle> getVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();
        for(int id : ((Map<Object, Integer>) callGlobal("GetAllVehicles")[0]).values()){
            vehicles.add(new VehicleJNI(id));
        }
        return vehicles;
    }

    public NPC getNPC(int id){
        if(!((Boolean) callGlobal("IsValidNPC", id)[0]))
            return null;
        return new NPCJNI(id);
    }

    public List<NPC> getNPCs(){
        List<NPC> npcs = new ArrayList<>();
        for(int id : ((Map<Object, Integer>) callGlobal("GetAllNPC")[0]).values()){
            npcs.add(new NPCJNI(id));
        }
        return npcs;
    }

    public WorldObject getObject(int id){
        if(!((Boolean) callGlobal("IsValidObject", id)[0]))
            return null;
        return new WorldObjectJNI(id);
    }

    public Pickup getPickup(int id){
        if(!((Boolean) callGlobal("IsValidPickup", id)[0]))
            return null;
        return new PickupJNI(id);
    }

    public List<Pickup> getPickups(){
        List<Pickup> pickups = new ArrayList<>();
        for(int id : ((Map<Object, Integer>) callGlobal("GetAllPickups")[0]).values()){
            pickups.add(new PickupJNI(id));
        }
        return pickups;
    }

    public Text3D getText3D(int id){
        if(!((Boolean) callGlobal("IsValidText3D", id)[0]))
            return null;
        return new Text3DJNI(id);
    }

    public List<Text3D> getText3Ds(){
        List<Text3D> texts = new ArrayList<>();
        for(int id : ((Map<Object, Integer>) callGlobal("GetAllText3D")[0]).values()){
            texts.add(new Text3DJNI(id));
        }
        return texts;
    }

    public WorldObject createObject(double x, double y, double z, int model){
        return new WorldObjectJNI((Integer) callGlobal("CreateObject", model, x, y, z)[0]);
    }

    public Vehicle createVehicle(double x, double y, double z, double heading, int model){
        return new VehicleJNI((Integer) callGlobal("CreateVehicle", model, x, y, z, heading)[0]);
    }

    public NPC createNPC(double x, double y, double z, double heading){
        return new NPCJNI((Integer) callGlobal("CreateNPC", x, y, z, heading)[0]);
    }

    public Door createDoor(double x, double y, double z, double heading, int model, boolean enableOpen){
        return new DoorJNI((Integer) callGlobal("CreateDoor", model, x, y, z, heading, enableOpen)[0]);
    }

    public Pickup createPickup(double x, double y, double z, int model) {
        return new PickupJNI((Integer) callGlobal("CreatePickup", model, x, y, z)[0]);
    }

    public Text3D createText3D(String text, double size, double x, double y, double z, double rx, double ry, double rz) {
        return new Text3DJNI((Integer) callGlobal("CreateText3D", text, size, x, y, z, rx, ry, rz)[0]);
    }

    public void createExplosion(int dimension, double x, double y, double z, int type, boolean soundExplosion) {
        callGlobal("CreateExplosion", type, x, y, z, dimension, soundExplosion);
    }

    public void createExplosion(int dimension, double x, double y, double z, int type, boolean soundExplosion, double camShakeRadius) {
        callGlobal("CreateExplosion", type, x, y, z, dimension, soundExplosion, camShakeRadius);
    }

    public void createExplosion(int dimension, double x, double y, double z, int type, boolean soundExplosion, double camShakeRadius, double radialForce) {
        callGlobal("CreateExplosion", type, x, y, z, dimension, soundExplosion, camShakeRadius, radialForce);
    }

    public void broadcast(String message){
        callGlobal("AddPlayerChatAll", message);
    }

    public void print(Object value){
        callGlobal("print", value);
    }

    public Dimension getDimension(int id){
        return new DimensionJNI(id);
    }

    public void registerCommand(String name, CommandExecutor executor) {
        packageBus.registerCommand(name, executor);
    }

    public void callEvent(Event event){
        packageBus.callEvent(event);
    }

    public PluginManager getPluginManager(){
        return pluginManager;
    }

}
