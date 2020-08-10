package net.onfirenetwork.onsetjava.component;

import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.*;
import net.onfirenetwork.onsetjava.enums.DamageType;
import net.onfirenetwork.onsetjava.enums.HitType;
import net.onfirenetwork.onsetjava.enums.PlayerState;

public abstract class PlayerComponent extends EntityComponent<Player> {

    public Player getPlayer(){
        return (Player) getEntity();
    }

    public void onChat(String message){}
    public boolean onCommand(String message, boolean exists){ return false; }
    public void onDamage(DamageType damageType, double damage){}
    public void onDeath(Player killer){}
    public boolean onWeaponShot(int weaponModel, HitType hitType, HitEntity hitEntity, Vector hitLocation, Vector startLocation, Vector hitNormal){ return false; }
    public void onSpawn(){}
    public void onSteamAuth(){}
    public void onJoin(){}
    public void onQuit(){}
    public void onInteractDoor(Door door, boolean openRequested){}
    public void onStateChange(PlayerState newState, PlayerState oldState){}
    public void onRemoteEvent(String event, Object[] args){}
    public void onPickupHit(Pickup pickup){}
    public void onEnterVehicle(Vehicle vehicle, int seat){}
    public void onExitVehicle(Vehicle vehicle, int seat){}

}
