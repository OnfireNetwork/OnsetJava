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
        return (T) getAttributes().getOrDefault(key, defaultValue);
    }

}
