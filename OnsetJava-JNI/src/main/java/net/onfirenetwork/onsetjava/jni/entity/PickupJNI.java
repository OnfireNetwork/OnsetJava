package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.Pickup;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.jni.ServerJNI;

import java.util.ArrayList;
import java.util.List;

public class PickupJNI implements Pickup {
    private int id;
    public PickupJNI(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public int getDimensionId(){
        return (Integer) ServerJNI.callGlobal("GetPickupDimension", id)[0];
    }
    public void setDimension(int dimension){
        ServerJNI.callGlobal("SetPickupDimension", id, dimension);
    }
    public void destroy(){
        ServerJNI.callGlobal("DestroyPickup", id);
    }
    public Vector getScale() {
        Object[] coords = ServerJNI.callGlobal("GetPickupScale", id);
        return new Vector((Double) coords[0], (Double) coords[1], (Double) coords[2]);
    }
    public void setScale(double x, double y, double z) {
        ServerJNI.callGlobal("SetPickupScale", x, y, z);
    }
    public boolean equals(Object obj) {
        if(obj.getClass() != PickupJNI.class)
            return false;
        PickupJNI other = (PickupJNI) obj;
        return other.id == id;
    }
    public void setProperty(String key, Object value, boolean sync){
        ServerJNI.callGlobal("SetPickupPropertyValue", id, key, value, sync);
    }
    public Object getProperty(String key){
        return ServerJNI.callGlobal("GetPickupPropertyValue", id, key)[0];
    }
    public void setVisibleFor(List<Player> players){
        List<Integer> table = new ArrayList<>();
        for(Player player : players){
            table.add(player.getId());
        }
        ServerJNI.callGlobal("SetPickupVisibleForPlayers", id, table);
    }
    public void setVisibleFor(Player... players){
        List<Integer> table = new ArrayList<>();
        for(Player player : players){
            table.add(player.getId());
        }
        ServerJNI.callGlobal("SetPickupVisibleForPlayers", id, table);
    }
    public void setVisible(Player player, boolean visible){
        ServerJNI.callGlobal("SetPickupVisibility", id, player.getId(), visible);
    }
}
