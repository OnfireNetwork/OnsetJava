package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.data.Location;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.data.Weapon;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.jni.ServerJNI;

public class PlayerJNI implements Player {

    private int id;

    public PlayerJNI(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return (String) ServerJNI.callGlobal("GetPlayerName", id)[0];
    }

    public void setName(String name) {
        ServerJNI.callGlobal("SetPlayerName", id, name);
    }

    public String getSteamId() {
        return (String) ServerJNI.callGlobal("GetPlayerSteamId", id)[0];
    }

    public void sendMessage(String message) {
        ServerJNI.callGlobal("AddPlayerChat", id, message);
    }

    public void kick(String message) {
        ServerJNI.callGlobal("KickPlayer", id, message);
    }

    public Vector getLocation() {
        Object[] coords = ServerJNI.callGlobal("GetPlayerLocation", id);
        return new Vector((Double) coords[0], (Double) coords[1], (Double) coords[2]);
    }

    public void setLocation(Vector location) {
        ServerJNI.callGlobal("SetPlayerLocation", location.getX(), location.getY(), location.getZ());
    }

    public Location getLocationAndHeading() {
        Object[] coords = ServerJNI.callGlobal("GetPlayerLocation", id);
        return new Location((Double) coords[0], (Double) coords[1], (Double) coords[2], getHeading());
    }

    public void setLocationAndHeading(Location location) {
        setLocation(location);
        setHeading(location.getHeading());
    }

    public void setSpawnLocation(Vector location, double heading){
        ServerJNI.callGlobal("SetPlayerSpawnLocation", id, location.getX(), location.getY(), location.getZ(), heading);
    }

    public double getHeading() {
        return (Double) ServerJNI.callGlobal("GetPlayerHeading", id)[0];
    }

    public void setHeading(double heading) {
        ServerJNI.callGlobal("SetPlayerHeading", heading);
    }

    public int getDimensionId(){
        return (Integer) ServerJNI.callGlobal("GetPlayerDimension", id)[0];
    }

    public void setDimension(int dimension){
        ServerJNI.callGlobal("SetPlayerDimension", id, dimension);
    }

    public void setRagdoll(boolean ragdoll){
        ServerJNI.callGlobal("SetPlayerRagdoll", id, ragdoll);
    }

    public void setVoiceEnabled(boolean enabled){
        ServerJNI.callGlobal("SetPlayerVoiceEnabled", id, enabled);
    }

    public boolean isVoiceEnabled(){
        return (Boolean) ServerJNI.callGlobal("IsPlayerVoiceEnabled", id)[0];
    }

    public boolean isTalking(){
        return (Boolean) ServerJNI.callGlobal("IsPlayerTalking", id)[0];
    }

    public void setSoundDimension(int dimension){
        ServerJNI.callGlobal("SetPlayerVoiceDimension", id, dimension);
    }

    public boolean isAiming(){
        return (Boolean) ServerJNI.callGlobal("IsPlayerAiming", id)[0];
    }

    public boolean isReloading(){
        return (Boolean) ServerJNI.callGlobal("IsPlayerReloading", id)[0];
    }

    public Weapon getWeapon(int slot){
        Object[] weapon = ServerJNI.callGlobal("GetPlayerWeapon", id, slot);
        return new Weapon((Integer) weapon[0], (Integer) weapon[1]);
    }

    public void setWeapon(int slot, int model, int ammo, boolean equip, boolean loaded){
        ServerJNI.callGlobal("SetPlayerWeapon", id, model, ammo, equip, slot, loaded);
    }

    public void enterVehicle(Vehicle vehicle){
        ServerJNI.callGlobal("SetPlayerInVehicle", id, vehicle.getId());
    }

    public void enterVehicle(Vehicle vehicle, int seat){
        ServerJNI.callGlobal("SetPlayerInVehicle", id, vehicle.getId(), seat);
    }

    public void exitVehicle(){
        ServerJNI.callGlobal("RemovePlayerFromVehicle", id);
    }

    public Vehicle getVehicle(){
        int vehicle = (Integer) ServerJNI.callGlobal("GetPlayerVehicle", id)[0];
        if(vehicle == 0)
            return null;
        return new VehicleJNI(vehicle);
    }

    public int getVehicleSeat(){
        return (Integer) ServerJNI.callGlobal("GetPlayerVehicleSeat", id)[0];
    }

    public void callRemoteEvent(String name, Object... args){
        Object[] a = new Object[args.length+2];
        a[0] = id;
        a[1] = name;
        for(int i=0; i<args.length; i++){
            a[i+2] = args[i];
        }
        ServerJNI.callGlobal("CallRemoteEvent", a);
    }

    public void execute(String script){
        ServerJNI.callGlobal("RunClientScript", id, script);
    }

}
