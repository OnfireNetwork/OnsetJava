package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.AttachmentEntity;
import net.onfirenetwork.onsetjava.entity.Pickup;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Text3D;
import net.onfirenetwork.onsetjava.jni.ServerJNI;

import java.util.ArrayList;
import java.util.List;

public class Text3DJNI implements Text3D {
    private int id;
    public Text3DJNI(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public int getDimensionId(){
        return (Integer) ServerJNI.callGlobal("GetText3DDimension", id)[0];
    }
    public void setDimension(int dimension){
        ServerJNI.callGlobal("SetText3DDimension", id, dimension);
    }
    public void destroy(){
        ServerJNI.callGlobal("DestroyText3D", id);
    }
    public boolean equals(Object obj) {
        if(obj.getClass() != Text3DJNI.class)
            return false;
        Text3DJNI other = (Text3DJNI) obj;
        return other.id == id;
    }
    public void setProperty(String key, Object value, boolean sync){
        ServerJNI.callGlobal("SetText3DPropertyValue", id, key, value, sync);
    }
    public Object getProperty(String key){
        return ServerJNI.callGlobal("GetText3DPropertyValue", id, key)[0];
    }
    public void setVisible(Player player, boolean visible){
        ServerJNI.callGlobal("SetText3DVisibility", id, player.getId(), visible);
    }
    public void attach(AttachmentEntity entity, Vector offset){
        ServerJNI.callGlobal("SetText3DAttached", id, entity.getAttachType().id(), entity.getId(), offset.getX(), offset.getY(), offset.getZ());
    }
    public void attach(AttachmentEntity entity, Vector offset, Vector rotation){
        ServerJNI.callGlobal("SetText3DAttached", id, entity.getAttachType().id(), entity.getId(), offset.getX(), offset.getY(), offset.getZ(), rotation.getX(), rotation.getY(), rotation.getZ());
    }
    public void attach(AttachmentEntity entity, Vector offset, Vector rotation, String socket){
        ServerJNI.callGlobal("SetText3DAttached", id, entity.getAttachType().id(), entity.getId(), offset.getX(), offset.getY(), offset.getZ(), rotation.getX(), rotation.getY(), rotation.getZ(), socket);
    }
    public void setText(String text){
        ServerJNI.callGlobal("SetText3DText", id, text);
    }
}
