package net.onfirenetwork.onsetjava.enums;

public enum DamageType {
    WEAPON(1),
    EXPLOSION(2),
    FIRE(3),
    FALL(4),
    VEHICLE_COLLISION(5);
    private int id;
    DamageType(int id){
        this.id = id;
    }
    public int id(){
        return id;
    }
    public static DamageType get(int id){
        for(DamageType type : values()){
            if(type.id == id){
                return type;
            }
        }
        return null;
    }
}
