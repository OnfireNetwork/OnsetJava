package net.onfirenetwork.onsetjava.entity;

import java.util.Map;

public interface PropertyEntity {
    default void setProperty(String key, Object value){
        setProperty(key, value, false);
    }
    void setProperty(String key, Object value, boolean sync);
    default String getPropertyString(String key){
        return (String) getProperty(key);
    }
    default int getPropertyInt(String key){
        return (Integer) getProperty(key);
    }
    default double getPropertyNumber(String key){
        return (Double) getProperty(key);
    }
    default boolean getPropertyBool(String key){
        return (Boolean) getProperty(key);
    }
    default Object[] getPropertyArray(String key){
        Map<Integer, Object> map = getPropertyMap(key);
        Object[] arr = new Object[map.size()];
        for(Integer i : map.keySet()){
            arr[i-1] = map.get(i);
        }
        return arr;
    }
    default String[] getPropertyStringArray(String key){
        return (String[]) getPropertyArray(key);
    }
    default Integer[] getPropertyIntArray(String key){
        return (Integer[]) getPropertyArray(key);
    }
    default Map getPropertyMap(String key){
        return (Map) getProperty(key);
    }
    Object getProperty(String key);
}
