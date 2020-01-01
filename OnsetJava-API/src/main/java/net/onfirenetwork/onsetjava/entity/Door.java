package net.onfirenetwork.onsetjava.entity;

import net.onfirenetwork.onsetjava.Dimension;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.data.Vector;

public interface Door extends HitEntity, PropertyEntity {
    int getId();
    int getDimensionId();
    void setDimension(int dimension);
    default void setDimension(Dimension dimension){
        setDimension(dimension.getId());
    }
    default Dimension getDimension(){
        return Onset.getDimension(getDimensionId());
    }
    void setOpen(boolean open);
    default void open(){
        setOpen(true);
    }
    default void close(){
        setOpen(false);
    }
    boolean isOpen();
    Vector getLocation();
    void setLocation(Vector location);
    int getModel();
    void destroy();
}
