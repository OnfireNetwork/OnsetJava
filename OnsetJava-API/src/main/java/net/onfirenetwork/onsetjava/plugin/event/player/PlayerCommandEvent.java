package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Cancellable;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerChatCommand")
public class PlayerCommandEvent extends Event implements Cancellable {
    private Player player;
    private String command;
    private boolean exists;
    private boolean cancelled = false;
    public PlayerCommandEvent(Player player, String command, boolean exists){
        this.player = player;
        this.command = command;
        this.exists = exists;
    }
    public Player getPlayer() {
        return player;
    }
    public String getCommand() {
        return command;
    }
    public boolean doesExist() {
        return exists;
    }
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    public boolean isCancelled() {
        return cancelled;
    }
}
