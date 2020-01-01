package net.onfirenetwork.onsetjava.enums;

public enum WeaponModel {
    FIST(1),
    DEAGLE(2);
    private int id;
    WeaponModel(int id){
        this.id = id;
    }
    public int id(){
        return this.id;
    }
    public static WeaponModel get(int id){
        for(WeaponModel model : values()){
            if(model.id == id){
                return model;
            }
        }
        return null;
    }
}
