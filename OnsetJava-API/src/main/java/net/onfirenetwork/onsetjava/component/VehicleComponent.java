package net.onfirenetwork.onsetjava.component;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Vehicle;

public abstract class VehicleComponent extends EntityComponent<Vehicle> {

    public Vehicle getVehicle(){
        return (Vehicle) getEntity();
    }
    public void onEnter(Player player, int seat){}
    public void onExit(Player player, int seat){}
    public void onDamage(double damage, int damageIndex, double morphDamage){}
    public void onRespawn(){}

}
