package net.onfirenetwork.onsetjava.jni;

import net.onfirenetwork.onsetjava.Dimension;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class DimensionJNI implements Dimension {
    private int id;
    public DimensionJNI(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public List<Player> getPlayers(){
        return Onset.getPlayers().stream().filter(e -> e.getId() == id).collect(Collectors.toList());
    }
    public List<Vehicle> getVehicles(){
        return Onset.getVehicles().stream().filter(e -> e.getId() == id).collect(Collectors.toList());
    }
    public List<NPC> getNPCs(){
        return Onset.getNPCs().stream().filter(e -> e.getId() == id).collect(Collectors.toList());
    }
    public List<Pickup> getPickups(){
        return Onset.getPickups().stream().filter(e -> e.getId() == id).collect(Collectors.toList());
    }
    public List<Text3D> getText3Ds(){
        return Onset.getText3Ds().stream().filter(e -> e.getId() == id).collect(Collectors.toList());
    }
    public boolean equals(Object obj) {
        if(obj.getClass() != DimensionJNI.class)
            return false;
        DimensionJNI other = (DimensionJNI) obj;
        return other.getId() == id;
    }
}
