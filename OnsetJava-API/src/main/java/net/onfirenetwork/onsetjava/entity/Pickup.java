package net.onfirenetwork.onsetjava.entity;

import net.onfirenetwork.onsetjava.Dimension;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.data.Vector;

import java.util.List;

public interface Pickup extends AttributeEntity, PropertyEntity {
    int getId();
    int getDimensionId();
    void setDimension(int dimension);
    default void setDimension(Dimension dimension){
        setDimension(dimension.getId());
    }
    default Dimension getDimension(){
        return Onset.getDimension(getDimensionId());
    }
    Vector getScale();
    default void setScale(Vector scale){
        setScale(scale.getX(), scale.getY(), scale.getZ());
    }
    void setScale(double x, double y, double z);
    void setVisibleFor(List<Player> players);
    void setVisibleFor(Player... players);
    void setVisible(Player player, boolean visible);
    void setLocation(Vector location);
    boolean isStreamed(Player player);
    void destroy();
}
