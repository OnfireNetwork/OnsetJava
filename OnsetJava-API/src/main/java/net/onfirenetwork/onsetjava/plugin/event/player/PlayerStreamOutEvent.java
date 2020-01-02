package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerStreamOut")
public class PlayerStreamOutEvent extends Event {
    private Player player;
    private Player streamedPlayer;
    public PlayerStreamOutEvent(Player player, Player streamedPlayer){
        this.player = player;
        this.streamedPlayer = streamedPlayer;
    }
    public Player getPlayer() {
        return player;
    }
    public Player getStreamedPlayer() {
        return streamedPlayer;
    }
}
