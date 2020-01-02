package net.onfirenetwork.onsetjava.plugin.event.npc;

import net.onfirenetwork.onsetjava.entity.NPC;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnNPCSpawn")
public class NPCSpawnEvent extends Event {
    private NPC npc;
    public NPCSpawnEvent(NPC npc){
        this.npc = npc;
    }
    public NPC getNPC() {
        return npc;
    }
}
