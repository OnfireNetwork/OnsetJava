package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerSpawn")
public class PlayerSpawnEvent extends Event {
    private Player player;
    public PlayerSpawnEvent(Player player){
        this.player = player;
    }
    public Player getPlayer(){
        return player;
    }
}
