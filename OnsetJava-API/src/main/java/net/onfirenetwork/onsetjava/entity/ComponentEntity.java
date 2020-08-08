package net.onfirenetwork.onsetjava.entity;

import net.onfirenetwork.onsetjava.component.EntityComponent;

import java.util.ArrayList;
import java.util.List;

public interface ComponentEntity<T extends EntityComponent<?>> extends AttributeEntity {

    default boolean hasComponent(Class<? extends T> type){
        return getComponent(type) != null;
    }

    default void addComponent(T component){
        T old = getComponent((Class<? extends T>) component.getClass());
        if(old != null)
            removeComponent(old);
        getComponents().add(component);
    }

    default void removeComponent(T component){
        getComponents().remove(component);
    }

    default <X extends T> X getComponent(Class<? extends T> type){
        return (X) getComponents().stream().filter(c -> c.getClass().equals(type)).findFirst().orElse(null);
    }

    default List<T> getComponents(){
        return getAttribute("ecs:components", new ArrayList<>());
    }

}
