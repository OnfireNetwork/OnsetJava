package net.onfirenetwork.onsetjava.plugin.event.npc;

import net.onfirenetwork.onsetjava.entity.NPC;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnNPCStreamOut")
public class NPCStreamOutEvent extends Event {
    private NPC npc;
    private Player player;
    public NPCStreamOutEvent(NPC npc, Player player){
        this.player = player;
        this.npc = npc;
    }
    public Player getPlayer() {
        return player;
    }
    public NPC getNPC() {
        return npc;
    }
}
