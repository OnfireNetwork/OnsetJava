package net.onfirenetwork.onsetjava.plugin.event.npc;

import net.onfirenetwork.onsetjava.entity.NPC;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnNPCDeath")
public class NPCDeathEvent extends Event {
    private NPC npc;
    private Player killer;
    public NPCDeathEvent(NPC npc, Player killer){
        this.npc = npc;
        this.killer = killer;
    }
    public NPC getNPC() {
        return npc;
    }
    public Player getKiller() {
        return killer;
    }
}
