package net.onfirenetwork.onsetjava.plugin.event.player;

import net.onfirenetwork.onsetjava.entity.Door;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnPlayerInteractDoor")
public class PlayerInteractDoorEvent extends Event {
    private Player player;
    private Door door;
    private boolean openRequested;
    public PlayerInteractDoorEvent(Player player, Door door, boolean openRequested){
        this.player = player;
        this.door = door;
        this.openRequested = openRequested;
    }

    public Player getPlayer() {
        return player;
    }

    public Door getDoor() {
        return door;
    }

    public boolean isOpenRequested() {
        return openRequested;
    }
}
