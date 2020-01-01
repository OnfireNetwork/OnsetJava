package net.onfirenetwork.onsetjava;

import net.onfirenetwork.onsetjava.data.Location;
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
    default WorldObject createObject(Vector location, int model){
        return createObject(location.getX(), location.getY(), location.getZ(), model);
    }
    default WorldObject createObject(double x, double y, double z, int model){
        WorldObject e = Onset.getServer().createObject(x, y, z, model);
        e.setDimension(getId());
        return e;
    }
    default Vehicle createVehicle(Location location, int model){
        return createVehicle(location.getX(), location.getY(), location.getZ(), location.getHeading(), model);
    }
    default Vehicle createVehicle(Vector location, double heading, int model){
        return createVehicle(location.getX(), location.getY(), location.getZ(), heading, model);
    }
    default Vehicle createVehicle(double x, double y, double z, double heading, int model){
        Vehicle e = Onset.getServer().createVehicle(x, y, z, heading, model);
        e.setDimension(getId());
        return e;
    }
    default NPC createNPC(Location location){
        return createNPC(location.getX(), location.getY(), location.getZ(), location.getHeading());
    }
    default NPC createNPC(Vector location, double heading){
        return createNPC(location.getX(), location.getY(), location.getZ(), heading);
    }
    default NPC createNPC(double x, double y, double z, double heading){
        NPC e = Onset.getServer().createNPC(x, y, z, heading);
        e.setDimension(getId());
        return e;
    }
    default Door createDoor(Location location, int model){
        return createDoor(location.getX(), location.getY(), location.getZ(), location.getHeading(), model);
    }
    default Door createDoor(Location location, int model, boolean enableOpen){
        return createDoor(location.getX(), location.getY(), location.getZ(), location.getHeading(), model, enableOpen);
    }
    default Door createDoor(Vector location, double heading, int model){
        return createDoor(location.getX(), location.getY(), location.getZ(), heading, model);
    }
    default Door createDoor(Vector location, double heading, int model, boolean enableOpen){
        return createDoor(location.getX(), location.getY(), location.getZ(), heading, model, enableOpen);
    }
    default Door createDoor(double x, double y, double z, double heading, int model){
        return createDoor(x, y, z, heading, model, false);
    }
    default Door createDoor(double x, double y, double z, double heading, int model, boolean enableOpen){
        Door e = Onset.getServer().createDoor(x, y, z, heading, model, enableOpen);
        e.setDimension(getId());
        return e;
    }
    default Pickup createPickup(Vector location, int model){
        return createPickup(location.getX(), location.getY(), location.getZ(), model);
    }
    default Pickup createPickup(double x, double y, double z, int model){
        Pickup e = Onset.getServer().createPickup(x, y, z, model);
        e.setDimension(getId());
        return e;
    }
    default Text3D createText3D(String text, double size, Vector location, Vector rotation){
        return createText3D(text, size, location.getX(), location.getY(), location.getZ(), rotation.getX(), rotation.getY(), rotation.getZ());
    }
    default Text3D createText3D(String text, double size, double x, double y, double z, double rx, double ry, double rz){
        Text3D e = Onset.getServer().createText3D(text, size, x, y, z, rx, ry, rz);
        e.setDimension(getId());
        return e;
    }
}
