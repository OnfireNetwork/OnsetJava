package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerChat")
public class PlayerChatEvent extends Event {
    private Player player;
    private String message;
    public PlayerChatEvent(Player player, String message){
        this.player = player;
        this.message = message;
    }
    public Player getPlayer(){
        return player;
    }
    public String getMessage() {
        return message;
    }
}
