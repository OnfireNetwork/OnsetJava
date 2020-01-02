package net.onfirenetwork.onsetjava.plugin.event.vehicle;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnVehicleStreamIn")
public class VehicleStreamInEvent extends Event {
    private Vehicle vehicle;
    private Player player;
    public VehicleStreamInEvent(Vehicle vehicle, Player player){
        this.player = player;
        this.vehicle = vehicle;
    }
    public Player getPlayer() {
        return player;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
}
