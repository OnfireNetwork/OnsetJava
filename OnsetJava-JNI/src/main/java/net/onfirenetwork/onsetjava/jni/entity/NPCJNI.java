package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.entity.NPC;
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
}
