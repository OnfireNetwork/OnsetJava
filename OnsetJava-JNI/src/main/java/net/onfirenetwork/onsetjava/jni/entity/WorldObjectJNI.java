package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.AttachmentEntity;
import net.onfirenetwork.onsetjava.entity.WorldObject;
import net.onfirenetwork.onsetjava.enums.AttachType;
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
    public void destroy(){
        ServerJNI.callGlobal("DestroyObject", id);
    }
    public Vector getLocation() {
        Object[] coords = ServerJNI.callGlobal("GetObjectLocation", id);
        return new Vector((Double) coords[0], (Double) coords[1], (Double) coords[2]);
    }
    public void setLocation(Vector location) {
        ServerJNI.callGlobal("SetObjectLocation", location.getX(), location.getY(), location.getZ());
    }
    public Vector getRotation() {
        Object[] coords = ServerJNI.callGlobal("GetObjectRotation", id);
        return new Vector((Double) coords[0], (Double) coords[1], (Double) coords[2]);
    }
    public void setRotation(Vector rotation) {
        ServerJNI.callGlobal("SetObjectRotation", rotation.getX(), rotation.getY(), rotation.getZ());
    }
    public Vector getScale() {
        Object[] coords = ServerJNI.callGlobal("GetObjectScale", id);
        return new Vector((Double) coords[0], (Double) coords[1], (Double) coords[2]);
    }
    public void setScale(Vector scale) {
        ServerJNI.callGlobal("SetObjectScale", scale.getX(), scale.getY(), scale.getZ());
    }
    public int getModel(){
        return (Integer) ServerJNI.callGlobal("GetObjectModel", id)[0];
    }
    public void attach(AttachmentEntity entity, Vector offset){
        ServerJNI.callGlobal("SetObjectAttached", id, entity.getAttachType().id(), entity.getId(), offset.getX(), offset.getY(), offset.getZ());
    }
    public void attach(AttachmentEntity entity, Vector offset, Vector rotation){
        ServerJNI.callGlobal("SetObjectAttached", id, entity.getAttachType().id(), entity.getId(), offset.getX(), offset.getY(), offset.getZ(), rotation.getX(), rotation.getY(), rotation.getZ());
    }
    public void attach(AttachmentEntity entity, Vector offset, Vector rotation, String socket){
        ServerJNI.callGlobal("SetObjectAttached", id, entity.getAttachType().id(), entity.getId(), offset.getX(), offset.getY(), offset.getZ(), rotation.getX(), rotation.getY(), rotation.getZ(), socket);
    }
    public void detach(){
        ServerJNI.callGlobal("SetObjectDetached", id);
    }
    public boolean isAttached(){
        return (Boolean) ServerJNI.callGlobal("IsObjectAttached", id)[0];
    }
    public AttachmentEntity getAttachment(){
        Object[] ret = ServerJNI.callGlobal("GetObjectAttachmentInfo", id);
        AttachType type = AttachType.get((Integer) ret[0]);
        if(type == null)
            return null;
        switch (type){
            case PLAYER:
                return Onset.getPlayer((Integer) ret[1]);
            case VEHICLE:
                return Onset.getVehicle((Integer) ret[1]);
            case OBJECT:
                return Onset.getObject((Integer) ret[1]);
            case NPC:
                return Onset.getNPC((Integer) ret[1]);
        }
        return null;
    }
    public void setStreamDistance(double distance){
        ServerJNI.callGlobal("SetObjectStreamDistance", id, distance);
    }
    public boolean equals(Object obj) {
        if(obj.getClass() != WorldObjectJNI.class)
            return false;
        WorldObjectJNI other = (WorldObjectJNI) obj;
        return other.id == id;
    }
    public void setProperty(String key, Object value, boolean sync){
        ServerJNI.callGlobal("SetObjectPropertyValue", id, key, value, sync);
    }
    public Object getProperty(String key){
        return ServerJNI.callGlobal("GetObjectPropertyValue", id, key)[0];
    }
    public AttachType getAttachType(){
        return AttachType.OBJECT;
    }
    public boolean isMoving(){
        return (Boolean) ServerJNI.callGlobal("IsObjectMoving", id)[0];
    }
    public void move(double x, double y, double z){
        ServerJNI.callGlobal("SetObjectMoveTo", id, x, y, z);
    }
    public void move(double x, double y, double z, double speed){
        ServerJNI.callGlobal("SetObjectMoveTo", id, x, y, z, speed);
    }
    public void stop(){
        ServerJNI.callGlobal("StopObjectMove", id);
    }
    public void setRotateAxis(double x, double y, double z){
        ServerJNI.callGlobal("SetObjectRotateAxis", id, x, y, z);
    }
}
