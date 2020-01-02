package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.enums.DamageType;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerDamage")
public class PlayerDamageEvent extends Event {
    private Player player;
    private DamageType damageType;
    private double damage;
    public PlayerDamageEvent(Player player, DamageType damageType, double damage){
        this.player = player;
        this.damageType = damageType;
        this.damage = damage;
    }
    public Player getPlayer(){
        return player;
    }
    public DamageType getDamageType(){
        return damageType;
    }
    public double getDamage() {
        return damage;
    }
}
