package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.data.Location;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.Door;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.WorldObject;
import net.onfirenetwork.onsetjava.jni.ServerJNI;

public class DoorJNI implements Door {
    private int id;
    public DoorJNI(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public int getDimensionId(){
        return (Integer) ServerJNI.callGlobal("GetDoorDimension", id)[0];
    }
    public void setDimension(int dimension){
        ServerJNI.callGlobal("SetDoorDimension", id, dimension);
    }
    public Vector getLocation() {
        Object[] coords = ServerJNI.callGlobal("GetDoorLocation", id);
        return new Vector((Double) coords[0], (Double) coords[1], (Double) coords[2]);
    }
    public void setLocation(Vector location) {
        ServerJNI.callGlobal("SetDoorLocation", location.getX(), location.getY(), location.getZ());
    }
    public void setOpen(boolean open){
        ServerJNI.callGlobal("SetDoorOpen", id, open);
    }
    public boolean isOpen(){
        return (Boolean) ServerJNI.callGlobal("IsDoorOpen", id)[0];
    }
    public int getModel(){
        return (Integer) ServerJNI.callGlobal("GetDoorModel", id)[0];
    }
    public void destroy(){
        ServerJNI.callGlobal("DestroyDoor", id);
    }
    public boolean equals(Object obj) {
        if(obj.getClass() != DoorJNI.class)
            return false;
        DoorJNI other = (DoorJNI) obj;
        return other.id == id;
    }
    public void setProperty(String key, Object value, boolean sync){
        ServerJNI.callGlobal("SetDoorPropertyValue", id, key, value, sync);
    }
    public Object getProperty(String key){
        return ServerJNI.callGlobal("GetDoorPropertyValue", id, key)[0];
    }
    public double getHeading(){
        Object[] coords = ServerJNI.callGlobal("GetDoorLocation", id);
        return (Double) coords[3];
    }
    public Location getLocationAndHeading(){
        Object[] coords = ServerJNI.callGlobal("GetDoorLocation", id);
        return new Location((Double) coords[0], (Double) coords[1], (Double) coords[2], (Double) coords[3]);
    }
    public boolean isStreamed(Player player){
        return (Boolean) ServerJNI.callGlobal("IsDoorStreamedIn", id, player.getId())[0];
    }
}
