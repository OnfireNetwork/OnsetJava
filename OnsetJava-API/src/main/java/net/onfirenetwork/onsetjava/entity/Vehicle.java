package net.onfirenetwork.onsetjava.entity;

import net.onfirenetwork.onsetjava.Dimension;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.data.Color;
import net.onfirenetwork.onsetjava.data.Location;
import net.onfirenetwork.onsetjava.data.Vector;

public interface Vehicle extends HitEntity, PropertyEntity, AttachmentEntity {
    int getId();
    int getDimensionId();
    void setDimension(int dimension);
    default void setDimension(Dimension dimension){
        setDimension(dimension.getId());
    }
    default Dimension getDimension(){
        return Onset.getDimension(getDimensionId());
    }
    void setHealth(double health);
    double getHealth();
    void setLicensePlate(String licensePlate);
    String getLicensePlate();
    Vector getLocation();
    void setLocation(Vector location);
    Vector getRotation();
    default void setRotation(Vector rotation) {
        setRotation(rotation.getX(), rotation.getY(), rotation.getZ());
    }
    void setRotation(double x, double y, double z);
    void setColor(Color color);
    Color getColor();
    int getModel();
    void enableRespawn(int time, boolean repair);
    void disableRespawn();
    Player getDriver();
    Player getPassenger(int seat);
    int getSeatCount();
    double getHeading();
    void setHeading(double heading);
    void setLocationAndHeading(Location location);
    Location getLocationAndHeading();
    default void setLinearVelocity(Vector velocity){
        setLinearVelocity(velocity.getX(), velocity.getY(), velocity.getZ());
    }
    default void setLinearVelocity(double x, double y, double z){
        setLinearVelocity(x, y, z, false);
    }
    default void setLinearVelocity(Vector velocity, boolean reset){
        setLinearVelocity(velocity.getX(), velocity.getY(), velocity.getZ(), reset);
    }
    void setLinearVelocity(double x, double y, double z, boolean reset);
    default void setAngularVelocity(Vector velocity){
        setAngularVelocity(velocity.getX(), velocity.getY(), velocity.getZ());
    }
    default void setAngularVelocity(double x, double y, double z){
        setAngularVelocity(x, y, z, false);
    }
    default void setAngularVelocity(Vector velocity, boolean reset){
        setAngularVelocity(velocity.getX(), velocity.getY(), velocity.getZ(), reset);
    }
    void setAngularVelocity(double x, double y, double z, boolean reset);
    Vector getVelocity();
    void setHood(double ratio);
    double getHood();
    void setTrunk(double ratio);
    double getTrunk();
    void setNitro(boolean nitro);
    void setEngineOn(boolean engine);
    boolean isEngineOn();
    void setLightOn(boolean light);
    boolean isLightOn();
    void setDamage(int part, double damage);
    double getDamage(int part);
    int getGear();
    boolean isStreamed(Player player);
    void destroy();
}
