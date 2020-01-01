package net.onfirenetwork.onsetjava.enums;

public enum PlayerState {
    NONE(0),
    ONFOOT(1),
    DRIVER(2),
    PASSENGER(3),
    ENTER_VEHICLE(4),
    EXIT_VEHICLE(6);
    private int id;
    PlayerState(int id){
        this.id = id;
    }
    public int id(){
        return id;
    }
    public static PlayerState get(int id){
        for(PlayerState state : values()){
            if(state.id == id){
                return state;
            }
        }
        return null;
    }
}
