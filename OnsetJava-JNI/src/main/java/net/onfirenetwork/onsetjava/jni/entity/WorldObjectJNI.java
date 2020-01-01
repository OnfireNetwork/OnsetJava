package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.entity.WorldObject;
import net.onfirenetwork.onsetjava.jni.ServerJNI;

public class WorldObjectJNI implements WorldObject {
    private int id;
    public WorldObjectJNI(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public int getDimensionId(){
        return (Integer) ServerJNI.callGlobal("GetObjectDimension", id)[0];
    }
    public void setDimension(int dimension){
        ServerJNI.callGlobal("SetObjectDimension", id, dimension);
    }
    public boolean equals(Object obj) {
        if(obj.getClass() != WorldObjectJNI.class)
            return false;
        WorldObjectJNI other = (WorldObjectJNI) obj;
        return other.id == id;
    }
}
