package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.data.Location;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.NPC;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.enums.AttachType;
import net.onfirenetwork.onsetjava.jni.ServerJNI;

public class NPCJNI implements NPC {
    private int id;
    public NPCJNI(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public int getDimensionId(){
        return (Integer) ServerJNI.callGlobal("GetNPCDimension", id)[0];
    }
    public void setDimension(int dimension){
        ServerJNI.callGlobal("SetNPCDimension", id, dimension);
    }
    public boolean equals(Object obj) {
        if(obj.getClass() != NPCJNI.class)
            return false;
        NPCJNI other = (NPCJNI) obj;
        return other.id == id;
    }
    public Vector getLocation() {
        Object[] coords = ServerJNI.callGlobal("GetNPCLocation", id);
        return new Vector((Double) coords[0], (Double) coords[1], (Double) coords[2]);
    }
    public void setLocation(Vector location) {
        ServerJNI.callGlobal("SetNPCLocation", location.getX(), location.getY(), location.getZ());
    }
    public Location getLocationAndHeading() {
        Object[] coords = ServerJNI.callGlobal("GetNPCLocation", id);
        return new Location((Double) coords[0], (Double) coords[1], (Double) coords[2], getHeading());
    }
    public void setLocationAndHeading(Location location) {
        setLocation(location);
        setHeading(location.getHeading());
    }
    public double getHeading() {
        return (Double) ServerJNI.callGlobal("GetNPCHeading", id)[0];
    }
    public void setHeading(double heading) {
        ServerJNI.callGlobal("SetNPCHeading", heading);
    }
    public void setHealth(double health){
        ServerJNI.callGlobal("SetNPCHealth", id, health);
    }
    public double getHealth(){
        return (Double) ServerJNI.callGlobal("GetNPCHealth", id)[0];
    }
    public void setAnimation(String animation){
        ServerJNI.callGlobal("SetNPCAnimation", id, animation);
    }
    public void setRagdoll(boolean ragdoll){
        ServerJNI.callGlobal("SetNPCRagdoll", id, ragdoll);
    }
    public void destroy(){
        ServerJNI.callGlobal("DestroyNPC", id);
    }
    public void setProperty(String key, Object value, boolean sync){
        ServerJNI.callGlobal("SetNPCPropertyValue", id, key, value, sync);
    }
    public Object getProperty(String key){
        return ServerJNI.callGlobal("GetNPCPropertyValue", id, key)[0];
    }
    public AttachType getAttachType(){
        return AttachType.NPC;
    }
    public void move(double x, double y, double z){
        ServerJNI.callGlobal("SetNPCTargetLocation", id, x, y, z);
    }
    public void move(double x, double y, double z, double speed){
        ServerJNI.callGlobal("SetNPCTargetLocation", id, x, y, z, speed);
    }
    public void follow(Player player){
        ServerJNI.callGlobal("SetNPCFollowPlayer", id, player.getId());
    }
    public void follow(Player player, double speed){
        ServerJNI.callGlobal("SetNPCFollowPlayer", id, player.getId(), speed);
    }
    public void follow(Vehicle vehicle){
        ServerJNI.callGlobal("SetNPCFollowVehicle", id, vehicle.getId());
    }
    public void follow(Vehicle vehicle, double speed){
        ServerJNI.callGlobal("SetNPCFollowVehicle", id, vehicle.getId(), speed);
    }
    public boolean isStreamed(Player player){
        return (Boolean) ServerJNI.callGlobal("IsNPCStreamedIn", player.getId(), id)[0];
    }
}
