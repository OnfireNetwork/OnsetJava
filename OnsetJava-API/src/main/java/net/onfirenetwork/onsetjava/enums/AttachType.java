package net.onfirenetwork.onsetjava.enums;

public enum AttachType {
    NONE(0),
    PLAYER(1),
    VEHICLE(2),
    OBJECT(3),
    NPC(4);
    private int id;
    AttachType(int id){
        this.id = id;
    }
    public int id(){
        return id;
    }
    public static AttachType get(int id){
        for(AttachType type : values()){
            if(type.id == id){
                return type;
            }
        }
        return null;
    }
}
