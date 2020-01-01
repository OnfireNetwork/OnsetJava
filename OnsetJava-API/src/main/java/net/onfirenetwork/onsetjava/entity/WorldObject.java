package net.onfirenetwork.onsetjava.entity;

import net.onfirenetwork.onsetjava.Dimension;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.enums.AttachType;

public interface WorldObject extends HitEntity, PropertyEntity, AttachmentEntity {
    int getId();
    int getDimensionId();
    void setDimension(int dimension);
    default void setDimension(Dimension dimension){
        setDimension(dimension.getId());
    }
    default Dimension getDimension(){
        return Onset.getDimension(getDimensionId());
    }
    Vector getLocation();
    void setLocation(Vector location);
    Vector getRotation();
    void setRotation(Vector rotation);
    Vector getScale();
    void setScale(Vector scale);
    int getModel();
    void attach(AttachmentEntity entity, Vector offset);
    void attach(AttachmentEntity entity, Vector offset, Vector rotation);
    void attach(AttachmentEntity entity, Vector offset, Vector rotation, String socket);
    void detach();
    boolean isAttached();
    AttachmentEntity getAttachment();
    void setStreamDistance(double distance);
    boolean isMoving();
    default void move(Vector location){
        move(location.getX(), location.getY(), location.getZ());
    }
    void move(double x, double y, double z);
    default void move(Vector location, double speed){
        move(location.getX(), location.getY(), location.getZ(), speed);
    }
    void move(double x, double y, double z, double speed);
    void stop();
    default void setRotateAxis(Vector rotation){
        setRotateAxis(rotation.getX(), rotation.getY(), rotation.getZ());
    }
    void setRotateAxis(double x, double y, double z);
    void destroy();
}
