package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Pickup;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerPickupHit")
public class PlayerPickupHitEvent extends Event {
    private Player player;
    private Pickup pickup;
    public PlayerPickupHitEvent(Player player, Pickup pickup){
        this.player = player;
        this.pickup = pickup;
    }
    public Player getPlayer(){
        return player;
    }
    public Pickup getPickup() {
        return pickup;
    }
}
