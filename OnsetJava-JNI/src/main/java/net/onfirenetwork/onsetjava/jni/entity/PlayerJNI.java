package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.clientdsl.LF;
import net.onfirenetwork.onsetjava.data.Location;
import net.onfirenetwork.onsetjava.data.NetworkStats;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.data.Weapon;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.enums.AttachType;
import net.onfirenetwork.onsetjava.enums.PlayerState;
import net.onfirenetwork.onsetjava.jni.ServerJNI;
import net.onfirenetwork.onsetjava.jni.data.NetworkStatsJNI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void setWeaponSlot(int slot){
        ServerJNI.callGlobal("EquipPlayerWeaponSlot", id, slot);
    }

    public int getWeaponSlot(){
        return (Integer) ServerJNI.callGlobal("GetPlayerEquippedWeaponSlot", id)[0];
    }

    public void setWeaponStat(int model, String stat, Object value){
        ServerJNI.callGlobal("SetPlayerWeaponStat", id, model, stat, value);
    }

    public boolean isDead(){
        return (Boolean) ServerJNI.callGlobal("IsPlayerDead", id)[0];
    }

    public void setRespawnTime(int time){
        ServerJNI.callGlobal("SetPlayerRespawnTime", id, time);
    }

    public int getRespawnTime(){
        return (Integer) ServerJNI.callGlobal("GetPlayerRespawnTime", id)[0];
    }

    public int getPing(){
        return (Integer) ServerJNI.callGlobal("GetPlayerPing", id)[0];
    }

    public String getGUID(){
        return (String) ServerJNI.callGlobal("GetPlayerGUID", id)[0];
    }

    public int getGameVersion(){
        return (Integer) ServerJNI.callGlobal("GetPlayerGameVersion", id)[0];
    }

    public PlayerState getState(){
        return PlayerState.get((Integer) ServerJNI.callGlobal("GetPlayerState", id)[0]);
    }

    public void setParachute(boolean parachute){
        ServerJNI.callGlobal("AttachPlayerParachute", id, parachute);
    }

    public void setHealth(double health){
        ServerJNI.callGlobal("SetPlayerHealth", id, health);
    }

    public double getHealth(){
        return (Double) ServerJNI.callGlobal("GetPlayerHealth", id)[0];
    }

    public void setArmor(double armor){
        ServerJNI.callGlobal("SetPlayerArmor", id, armor);
    }

    public double getArmor(){
        return (Double) ServerJNI.callGlobal("GetPlayerArmor", id)[0];
    }

    public String getAddress(){
        return (String) ServerJNI.callGlobal("GetPlayerIP", id)[0];
    }

    public String getLocale(){
        return (String) ServerJNI.callGlobal("GetPlayerLocale", id)[0];
    }

    public void setAnimation(String animation){
        ServerJNI.callGlobal("SetPlayerAnimation", id, animation);
    }

    public void setHeadSize(double size){
        ServerJNI.callGlobal("SetPlayerHeadSize", id, size);
    }

    public double getHeadSize(){
        return (Double) ServerJNI.callGlobal("GetPlayerHeadSize", id)[0];
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

    public void setSpectator(boolean spectator){
        ServerJNI.callGlobal("SetPlayerSpectate", id, spectator);
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

    public void execute(LF script){
        execute(script.toCode(true));
    }

    public void setProperty(String key, Object value, boolean sync){
        ServerJNI.callGlobal("SetPlayerPropertyValue", id, key, value, sync);
    }

    public Object getProperty(String key){
        return ServerJNI.callGlobal("GetPlayerPropertyValue", id, key)[0];
    }

    public AttachType getAttachType(){
        return AttachType.PLAYER;
    }

    public boolean isStreamed(Player player){
        return (Boolean) ServerJNI.callGlobal("IsPlayerStreamedIn", player.getId(), id)[0];
    }

    public NetworkStats getNetworkStats(){
        return new NetworkStatsJNI((Map<String, Object>) ServerJNI.callGlobal("GetPlayerNetworkStats", id)[0]);
    }

    public List<Player> getStreamedPlayers(){
        List<Player> players = new ArrayList<>();
        Map<Integer, Integer> table = (Map<Integer, Integer>) ServerJNI.callGlobal("GetStreamedPlayersForPlayer", id)[0];
        for(int id : table.values()){
            Player player = Onset.getPlayer(id);
            if(player != null){
                players.add(player);
            }
        }
        return players;
    }

}
