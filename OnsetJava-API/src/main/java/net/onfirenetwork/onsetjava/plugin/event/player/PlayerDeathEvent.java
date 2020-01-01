package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerDeath")
public class PlayerDeathEvent extends Event {
    private Player player;
    private Player killer;
    public PlayerDeathEvent(Player player, Player killer){
        this.player = player;
        this.killer = killer;
    }
    public Player getPlayer() {
        return player;
    }
    public Player getKiller() {
        return killer;
    }
    public boolean isSuicide(){
        return killer == null;
    }
}
