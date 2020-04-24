package net.onfirenetwork.onsetjava.entity;

import net.onfirenetwork.onsetjava.Dimension;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.data.Location;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.enums.Animation;

public interface NPC extends HitEntity, PropertyEntity, AttachmentEntity {
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
    default void setAnimation(Animation animation){
        setAnimation(animation.name());
    }
    void setAnimation(String animation);
    Vector getLocation();
    void setLocation(Vector location);
    double getHeading();
    void setHeading(double heading);
    void setLocationAndHeading(Location location);
    Location getLocationAndHeading();
    void setRagdoll(boolean ragdoll);
    default void move(Vector location){
        move(location.getX(), location.getY(), location.getZ());
    }
    void move(double x, double y, double z);
    default void move(Vector location, double speed){
        move(location.getX(), location.getY(), location.getZ(), speed);
    }
    void move(double x, double y, double z, double speed);
    void follow(Player player);
    void follow(Player player, double speed);
    void follow(Vehicle vehicle);
    void follow(Vehicle vehicle, double speed);
    boolean isStreamed(Player player);
    void destroy();
    void setRespawnTime(int time);
}
