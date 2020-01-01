package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerSteamAuth")
public class PlayerSteamAuthEvent extends Event {
    private Player player;
    public PlayerSteamAuthEvent(Player player){
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }
}
