package net.onfirenetwork.onsetjava.jni.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.onfirenetwork.onsetjava.i18n.I18NPlugin;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class I18NPluginImpl implements I18NPlugin {
    private Map<String, String> stringMap = new HashMap<>();
    public String translate(String key, Object... args){
        if(!stringMap.containsKey(key)){
            return null;
        }
        String string = stringMap.get(key);
        for(int i=0; i<args.length; i++){
            string = string.replace("{"+(i+1)+"}", String.valueOf(args[i]));
        }
        return string;
    }
    public I18NPluginImpl(InputStream stream){
        this(new Gson().fromJson(new String(readFully(stream), StandardCharsets.UTF_8), JsonObject.class));
    }
    public I18NPluginImpl(JsonObject source){
        for(String s : source.keySet()){
            stringMap.put(s, source.get(s).getAsString());
        }
    }
    public Map<String, String> getStringMap(){
        return stringMap;
    }
    private static byte[] readFully(InputStream stream){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            while (stream.available() > 0){
                byte[] data = new byte[Math.min(stream.available(), 1024)];
                int amount = stream.read(data);
                baos.write(data, 0, amount);
            }
            stream.close();
        }catch (Exception e){}
        return baos.toByteArray();
    }
}
