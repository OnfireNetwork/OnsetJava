package net.onfirenetwork.onsetjava.plugin.event.vehicle;

import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnVehicleRespawn")
public class VehicleRespawnEvent extends Event {
    private Vehicle vehicle;
    public VehicleRespawnEvent(Vehicle vehicle){
        this.vehicle = vehicle;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
}
