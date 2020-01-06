package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;

public class PlayerRemoteEvent extends Event {
    private Player player;
    private String name;
    private Object[] args;
    public PlayerRemoteEvent(Player player, String name, Object[] args){
        this.player = player;
        this.name = name;
        this.args = args;
    }
    public Player getPlayer(){
        return player;
    }
    public String getName() {
        return name;
    }
    public Object[] getArgs(){
        return args;
    }
}
