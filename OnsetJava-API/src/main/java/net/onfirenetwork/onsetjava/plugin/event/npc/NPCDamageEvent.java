package net.onfirenetwork.onsetjava.plugin.event.npc;

import net.onfirenetwork.onsetjava.entity.NPC;
import net.onfirenetwork.onsetjava.enums.DamageType;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.LuaEvent;

@LuaEvent("OnNPCDamage")
public class NPCDamageEvent extends Event {
    private NPC npc;
    private DamageType damageType;
    private double damage;
    public NPCDamageEvent(NPC npc, DamageType damageType, double damage){
        this.npc = npc;
        this.damageType = damageType;
        this.damage = damage;
    }
    public NPC getNPC() {
        return npc;
    }
    public DamageType getDamageType() {
        return damageType;
    }
    public double getDamage() {
        return damage;
    }
}
