package net.onfirenetwork.onsetjava.enums;

public enum HitType {
    AIR(1),
    PLAYER(2),
    VEHICLE(3),
    NPC(4),
    OBJECT(5),
    LANDSCAPE(6),
    WATER(7);
    private int id;
    HitType(int id){
        this.id = id;
    }
    public int id(){
        return id;
    }
    public static HitType get(int id){
        for(HitType type : values()){
            if(type.id == id){
                return type;
            }
        }
        return null;
    }
}
