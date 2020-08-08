package net.onfirenetwork.onsetjava.component;

import net.onfirenetwork.onsetjava.entity.ComponentEntity;

public abstract class EntityComponent<T extends ComponentEntity<?>> {
    private T entity;
    public void setEntity(T entity){
        this.entity = entity;
    }
    public T getEntity(){
        return entity;
    }
}
