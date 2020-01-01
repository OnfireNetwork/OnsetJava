package net.onfirenetwork.onsetjava;

import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.*;

import java.util.List;

public interface Dimension {
    int getId();
    List<Player> getPlayers();
    List<Vehicle> getVehicles();
    List<NPC> getNPCs();
    List<Pickup> getPickups();
    List<Text3D> getText3Ds();
    default void createExplosion(Vector location, int type){
        createExplosion(location.getX(), location.getY(), location.getZ(), type);
    }
    default void createExplosion(double x, double y, double z, int type){
        createExplosion(x, y, z, type, true);
    }
    default void createExplosion(Vector location, int type, boolean soundExplosion){
        createExplosion(location.getX(), location.getY(), location.getZ(), type, soundExplosion);
    }
    default void createExplosion(double x, double y, double z, int type, boolean soundExplosion){
        Onset.getServer().createExplosion(getId(), x, y, z, type, soundExplosion);
    }
    default void createExplosion(int dimension, Vector location, int type, boolean soundExplosion, double camShakeRadius){
        createExplosion(location.getX(), location.getY(), location.getZ(), type, soundExplosion, camShakeRadius);
    }
    default void createExplosion(double x, double y, double z, int type, boolean soundExplosion, double camShakeRadius){
        Onset.getServer().createExplosion(getId(), x, y, z, type, soundExplosion, camShakeRadius);
    }
    default void createExplosion(int dimension, Vector location, int type, boolean soundExplosion, double camShakeRadius, double radialForce){
        createExplosion(location.getX(), location.getY(), location.getZ(), type, soundExplosion, camShakeRadius, radialForce);
    }
    default void createExplosion(double x, double y, double z, int type, boolean soundExplosion, double camShakeRadius, double radialForce){
        Onset.getServer().createExplosion(getId(), x, y, z, type, soundExplosion, camShakeRadius, radialForce);
    }
}
