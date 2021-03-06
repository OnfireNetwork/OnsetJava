package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.data.Color;
import net.onfirenetwork.onsetjava.data.Location;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.Door;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.enums.AttachType;
import net.onfirenetwork.onsetjava.jni.AttributeSystem;
import net.onfirenetwork.onsetjava.jni.ServerJNI;

import java.util.Map;

public class VehicleJNI implements Vehicle {
    private int id;
    public VehicleJNI(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public int getDimensionId(){
        return (Integer) ServerJNI.callGlobal("GetVehicleDimension", id)[0];
    }
    public void setDimension(int dimension){
        ServerJNI.callGlobal("SetVehicleDimension", id, dimension);
    }
    public Vector getLocation() {
        Object[] coords = ServerJNI.callGlobal("GetVehicleLocation", id);
        return new Vector((Double) coords[0], (Double) coords[1], (Double) coords[2]);
    }
    public void setLocation(Vector location) {
        ServerJNI.callGlobal("SetVehicleLocation", id, location.getX(), location.getY(), location.getZ());
    }
    public Location getLocationAndHeading() {
        Object[] coords = ServerJNI.callGlobal("GetVehicleLocation", id);
        return new Location((Double) coords[0], (Double) coords[1], (Double) coords[2], getHeading());
    }
    public void setLocationAndHeading(Location location) {
        setLocation(location);
        setHeading(location.getHeading());
    }
    public double getHeading() {
        return (Double) ServerJNI.callGlobal("GetVehicleHeading", id)[0];
    }
    public void setHeading(double heading) {
        ServerJNI.callGlobal("SetVehicleHeading", heading);
    }
    public Vector getRotation(){
        Object[] coords = ServerJNI.callGlobal("GetVehicleRotation", id);
        return new Vector((Double) coords[0], (Double) coords[1], (Double) coords[2]);
    }
    public void setRotation(double x, double y, double z){
        ServerJNI.callGlobal("SetVehicleRotation", id, x, y, z);
    }
    public void setHealth(double health){
        ServerJNI.callGlobal("SetVehicleHealth", id, health);
    }
    public double getHealth(){
        return (Double) ServerJNI.callGlobal("GetVehicleHealth", id)[0];
    }
    public void setLicensePlate(String licensePlate) {
        ServerJNI.callGlobal("SetVehicleLicensePlate", id, licensePlate);
    }
    public String getLicensePlate(){
        return (String) ServerJNI.callGlobal("GetVehicleLicensePlate", id)[0];
    }
    public void setColor(Color color){
        ServerJNI.callGlobal("SetVehicleColor", id, color.toHexRGB());
    }
    public Color getColor(){
        return Color.fromHex((Integer) ServerJNI.callGlobal("GetVehicleColor", id)[0]);
    }
    public void destroy(){
        ServerJNI.callGlobal("DestroyVehicle", id);
        AttributeSystem.destroyedVehicle(id);
    }
    public int getModel(){
        return (Integer) ServerJNI.callGlobal("GetVehicleModel", id)[0];
    }
    public void enableRespawn(int time, boolean repair){
        ServerJNI.callGlobal("SetVehicleRespawnParams", id, true, time, repair);
    }
    public void disableRespawn(){
        ServerJNI.callGlobal("SetVehicleRespawnParams", id, false);
    }
    public Player getDriver(){
        Object ret = ServerJNI.callGlobal("GetVehicleDriver", id)[0];
        if(ret instanceof Boolean)
            return null;
        return Onset.getPlayer((Integer) ret);
    }
    public Player getPassenger(int seat){
        return Onset.getPlayer((Integer) ServerJNI.callGlobal("GetVehicleDriver", id)[0]);
    }
    public int getSeatCount(){
        return (Integer) ServerJNI.callGlobal("GetVehicleNumberOfSeats", id)[0];
    }
    public void setLinearVelocity(double x, double y, double z, boolean reset){
        ServerJNI.callGlobal("SetVehicleLinearVelocity", id, x, y, z, reset);
    }
    public void setAngularVelocity(double x, double y, double z, boolean reset){
        ServerJNI.callGlobal("SetVehicleAngularVelocity", id, x, y, z, reset);
    }
    public Vector getVelocity() {
        Object[] coords = ServerJNI.callGlobal("GetVehicleVelocity", id);
        return new Vector((Double) coords[0], (Double) coords[1], (Double) coords[2]);
    }
    public void setHood(double ratio){
        ServerJNI.callGlobal("SetVehicleHoodRatio", id, ratio);
    }
    public double getHood(){
        return (Double) ServerJNI.callGlobal("GetVehicleHoodRatio", id)[0];
    }
    public void setTrunk(double ratio){
        ServerJNI.callGlobal("SetVehicleTrunkRatio", id, ratio);
    }
    public double getTrunk(){
        return (Double) ServerJNI.callGlobal("GetVehicleTrunkRatio", id)[0];
    }
    public void setNitro(boolean nitro){
        ServerJNI.callGlobal("AttachVehicleNitro", id, nitro);
    }
    public void setEngineOn(boolean engine){
        if(engine){
            ServerJNI.callGlobal("StartVehicleEngine", id);
        }else{
            ServerJNI.callGlobal("StopVehicleEngine", id);
        }
    }
    public boolean isEngineOn(){
        return (Boolean) ServerJNI.callGlobal("GetVehicleEngineState", id)[0];
    }
    public void setLightOn(boolean light){
        ServerJNI.callGlobal("SetVehicleLightEnabled", id, light);
    }
    public boolean isLightOn(){
        return (Boolean) ServerJNI.callGlobal("GetVehicleLightState", id)[0];
    }
    public void setDamage(int part, double damage){
        ServerJNI.callGlobal("SetVehicleDamage", id, part, damage);
    }
    public double getDamage(int part){
        return (Double) ServerJNI.callGlobal("GetVehicleDamage", id, part)[0];
    }
    public int getGear(){
        return (Integer) ServerJNI.callGlobal("GetVehicleGear", id)[0];
    }
    public boolean equals(Object obj) {
        if(obj.getClass() != VehicleJNI.class)
            return false;
        VehicleJNI other = (VehicleJNI) obj;
        return other.id == id;
    }
    public void setProperty(String key, Object value, boolean sync){
        ServerJNI.callGlobal("SetVehiclePropertyValue", id, key, value, sync);
    }
    public Object getProperty(String key){
        Object[] prop = ServerJNI.callGlobal("GetVehiclePropertyValue", id, key);
        return prop.length == 0?null:prop[0];
    }
    public AttachType getAttachType(){
        return AttachType.VEHICLE;
    }
    public boolean isStreamed(Player player){
        return (Boolean) ServerJNI.callGlobal("IsVehicleStreamedIn", player.getId(), id)[0];
    }
    public Map<String, Object> getAttributes(){
        return AttributeSystem.getVehicleAttributes(id);
    }
}
