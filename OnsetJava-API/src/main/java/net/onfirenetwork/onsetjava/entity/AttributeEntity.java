package net.onfirenetwork.onsetjava.entity;

import java.util.Map;

public interface AttributeEntity {

    Map<String, Object> getAttributes();
    default void setAttribute(String key, Object value){
        if(value == null)
            getAttributes().remove(key);
        else
            getAttributes().put(key, value);
    }
    default <T> T getAttribute(String key){
        Object value = getAttributes().get(key);
        return value == null ? null : (T) value;
    }
    default <T> T getAttribute(String key, T defaultValue){
        T value = getAttribute(key);
        if(value == null){
            value = defaultValue;
            setAttribute(key, value);
        }
        return value;
    }
    
    default void putAt(String key, Object value){
        setAttribute(key, value);
    }

    default <T> T getAt(String key){
        return getAttribute(key);
    }

}
