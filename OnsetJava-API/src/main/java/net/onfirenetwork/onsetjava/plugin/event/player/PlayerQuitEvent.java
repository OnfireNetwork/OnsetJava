package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerQuit")
public class PlayerQuitEvent extends Event {
    private Player player;
    public PlayerQuitEvent(Player player){
        this.player = player;
    }
    public Player getPlayer(){
        return player;
    }
}
