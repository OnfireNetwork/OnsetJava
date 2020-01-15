package net.onfirenetwork.onsetjava.jni;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class OnsetJavaConfig {
    private String language = "en";
    public String getLanguage(){
        return language;
    }
    public void save(File file){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(new GsonBuilder().setPrettyPrinting().create().toJson(this).getBytes(StandardCharsets.UTF_8));
            fos.flush();
            fos.close();
        }catch (Exception ex){}
    }
    public static OnsetJavaConfig load(File file){
        if(!file.exists()){
            OnsetJavaConfig config = new OnsetJavaConfig();
            config.save(file);
            return config;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            InputStream is = new FileInputStream(file);
            while (is.available() > 0){
                byte[] data = new byte[Math.min(is.available(), 1024)];
                int amount = is.read(data);
                baos.write(data, 0, amount);
            }
            is.close();
        }catch (Exception ex){}
        return new Gson().fromJson(new String(baos.toByteArray(), StandardCharsets.UTF_8), OnsetJavaConfig.class);
    }
}
