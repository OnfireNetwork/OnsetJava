package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerServerAuth")
public class PlayerServerAuthEvent extends Event {
    private Player player;
    public PlayerServerAuthEvent(Player player){
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }
}
