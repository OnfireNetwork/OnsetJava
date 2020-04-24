package net.onfirenetwork.onsetjava.entity;

import net.onfirenetwork.onsetjava.Dimension;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.data.Vector;

import java.util.List;

public interface Text3D extends PropertyEntity {
    int getId();
    int getDimensionId();
    void setDimension(int dimension);
    default void setDimension(Dimension dimension){
        setDimension(dimension.getId());
    }
    default Dimension getDimension(){
        return Onset.getDimension(getDimensionId());
    }
    void attach(AttachmentEntity entity, Vector offset);
    void attach(AttachmentEntity entity, Vector offset, Vector rotation);
    void attach(AttachmentEntity entity, Vector offset, Vector rotation, String socket);
    void setVisible(Player player, boolean visible);
    void setText(String text);
    void destroy();
    boolean isStreamed(Player player);
    void setLocation(Vector location);
}
