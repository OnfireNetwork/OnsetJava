package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;

public class PlayerRemoteEvent extends Event {
    private Player player;
    private Object[] args;
    public PlayerRemoteEvent(Player player, Object[] args){
        this.player = player;
        this.args = args;
    }
    public Player getPlayer(){
        return player;
    }
    public Object[] getArgs(){
        return args;
    }
}
