package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.HitEntity;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.enums.HitType;
import net.onfirenetwork.onsetjava.plugin.event.Cancellable;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerWeaponShot")
public class PlayerWeaponShotEvent extends Event implements Cancellable {

    private Player player;
    private int weaponModel;
    private HitType hitType;
    private HitEntity hitEntity;
    private Vector hitLocation;
    private Vector startLocation;
    private Vector hitNormal;
    private boolean cancelled = false;

    public PlayerWeaponShotEvent(Player player, int weaponModel, HitType hitType, HitEntity hitEntity, Vector hitLocation, Vector startLocation, Vector hitNormal){
        this.player = player;
        this.weaponModel = weaponModel;
        this.hitType = hitType;
        this.hitEntity = hitEntity;
        this.hitLocation = hitLocation;
        this.startLocation = startLocation;
        this.hitNormal = hitNormal;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWeaponModel() {
        return weaponModel;
    }

    public HitType getHitType() {
        return hitType;
    }

    public HitEntity getHitEntity() {
        return hitEntity;
    }

    public Vector getHitLocation() {
        return hitLocation;
    }

    public Vector getStartLocation() {
        return startLocation;
    }

    public Vector getHitNormal() {
        return hitNormal;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
