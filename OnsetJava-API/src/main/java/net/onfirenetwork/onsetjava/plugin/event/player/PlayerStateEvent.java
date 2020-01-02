package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.enums.PlayerState;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerStateChange")
public class PlayerStateEvent extends Event {
    private Player player;
    private PlayerState newState;
    private PlayerState oldState;
    public PlayerStateEvent(Player player, PlayerState newState, PlayerState oldState){
        this.player = player;
        this.newState = newState;
        this.oldState = oldState;
    }
    public Player getPlayer(){
        return player;
    }
    public PlayerState getNewState() {
        return newState;
    }
    public PlayerState getOldState() {
        return oldState;
    }
}
