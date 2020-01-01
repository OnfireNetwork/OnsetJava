package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.jni.ServerJNI;

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
    public boolean equals(Object obj) {
        if(obj.getClass() != VehicleJNI.class)
            return false;
        VehicleJNI other = (VehicleJNI) obj;
        return other.id == id;
    }
}
