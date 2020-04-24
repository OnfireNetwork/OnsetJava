package net.onfirenetwork.onsetjava.plugin.event.vehicle;

import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnVehicleDamage")
public class VehicleDamageEvent extends Event {
    private Vehicle vehicle;
    private double damage;
    private int damageIndex;
    private double morphDamage;
    public VehicleDamageEvent(Vehicle vehicle, double damage, int damageIndex, double morphDamage){
        this.vehicle = vehicle;
        this.damage = damage;
        this.damageIndex = damageIndex;
        this.morphDamage = morphDamage;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public double getDamage() {
        return damage;
    }
    public int getDamageIndex() {
        return damageIndex;
    }
    public double getMorphDamage() {
        return morphDamage;
    }
}
