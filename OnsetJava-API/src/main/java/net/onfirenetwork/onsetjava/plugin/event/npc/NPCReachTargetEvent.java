package net.onfirenetwork.onsetjava.plugin.event.npc;

import net.onfirenetwork.onsetjava.entity.NPC;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnNPCReachTarget")
public class NPCReachTargetEvent extends Event {
    private NPC npc;
    public NPCReachTargetEvent(NPC npc){
        this.npc = npc;
    }
    public NPC getNPC() {
        return npc;
    }
}
