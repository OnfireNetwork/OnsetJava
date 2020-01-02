package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerLeaveVehicle")
public class PlayerExitVehicleEvent extends Event {
    private Player player;
    private Vehicle vehicle;
    private int seat;
    public PlayerExitVehicleEvent(Player player, Vehicle vehicle, int seat){
        this.player = player;
        this.vehicle = vehicle;
        this.seat = seat;
    }
    public Player getPlayer(){
        return player;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public int getSeat() {
        return seat;
    }
}
