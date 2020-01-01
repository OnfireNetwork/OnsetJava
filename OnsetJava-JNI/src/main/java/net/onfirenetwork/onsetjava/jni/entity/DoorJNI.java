package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.entity.Door;
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
    public void setOpen(boolean open){
        ServerJNI.callGlobal("SetDoorOpen", id, open);
    }
    public boolean isOpen(){
        return (Boolean) ServerJNI.callGlobal("IsDoorOpen", id)[0];
    }
    public boolean equals(Object obj) {
        if(obj.getClass() != DoorJNI.class)
            return false;
        DoorJNI other = (DoorJNI) obj;
        return other.id == id;
    }
}
