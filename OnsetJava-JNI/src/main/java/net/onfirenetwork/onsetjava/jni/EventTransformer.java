package net.onfirenetwork.onsetjava.jni;

import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.HitEntity;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.enums.HitType;
import net.onfirenetwork.onsetjava.jni.entity.*;
import net.onfirenetwork.onsetjava.plugin.event.ClientConnectionRequestEvent;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.UnknownEvent;
import net.onfirenetwork.onsetjava.plugin.event.player.*;

public class EventTransformer {

    public static Event transform(String name, Object[] args){
        switch (name){
            case "OnPlayerServerAuth": {
                Player player = new PlayerJNI((Integer) args[0]);
                ServerJNI.getInstance().addPlayer(player);
                return new PlayerServerAuthEvent(player);
            }
            case "OnPlayerSteamAuth": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null)
                    return new PlayerSteamAuthEvent(player);
            }
            case "OnPlayerJoin": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null)
                    return new PlayerJoinEvent(player);
            } break;
            case "OnPlayerQuit": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null){
                    ServerJNI.getInstance().removePlayer(player);
                    return new PlayerQuitEvent(player);
                }
            } break;
            case "OnPlayerDeath": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null){
                    int killerId = (Integer) args[1];
                    if(killerId != 0 && killerId != player.getId())
                        return new PlayerDeathEvent(player, ServerJNI.getInstance().getPlayer(killerId));
                    return new PlayerDeathEvent(player, null);
                }
            } break;
            case "OnPlayerWeaponShot": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null){
                    HitType hitType = HitType.get((Integer) args[2]);
                    HitEntity hitEntity = null;
                    switch (hitType){
                        case PLAYER:
                            hitEntity = ServerJNI.getInstance().getPlayer((Integer) args[3]);
                            break;
                        case OBJECT:
                            hitEntity = new WorldObjectJNI((Integer) args[3]);
                            break;
                        case VEHICLE:
                            hitEntity = new VehicleJNI((Integer) args[3]);
                            break;
                        case NPC:
                            hitEntity = new NPCJNI((Integer) args[3]);
                            break;
                    }
                    return new PlayerWeaponShotEvent(player, (Integer) args[1], hitType, hitEntity, new Vector((Double) args[4], (Double) args[5], (Double) args[6]), new Vector((Double) args[7], (Double) args[8], (Double) args[9]), new Vector((Double) args[10], (Double) args[11], (Double) args[12]));
                }
            } break;
            case "OnPlayerInteractDoor": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null){
                    return new PlayerInteractDoorEvent(player, new DoorJNI((Integer) args[1]), (Boolean) args[2]);
                }
            } break;
            case "OnClientConnectionRequest": {
                return new ClientConnectionRequestEvent((String) args[0], (Integer) args[1]);
            }
        }
        return new UnknownEvent(name, args);
    }

}
