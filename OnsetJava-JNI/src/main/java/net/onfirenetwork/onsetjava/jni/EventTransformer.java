package net.onfirenetwork.onsetjava.jni;

import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.HitEntity;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.enums.DamageType;
import net.onfirenetwork.onsetjava.enums.HitType;
import net.onfirenetwork.onsetjava.enums.PlayerState;
import net.onfirenetwork.onsetjava.jni.entity.*;
import net.onfirenetwork.onsetjava.plugin.event.ClientConnectionRequestEvent;
import net.onfirenetwork.onsetjava.plugin.event.Event;
import net.onfirenetwork.onsetjava.plugin.event.UnknownEvent;
import net.onfirenetwork.onsetjava.plugin.event.npc.*;
import net.onfirenetwork.onsetjava.plugin.event.player.*;
import net.onfirenetwork.onsetjava.plugin.event.vehicle.VehicleDamageEvent;
import net.onfirenetwork.onsetjava.plugin.event.vehicle.VehicleRespawnEvent;
import net.onfirenetwork.onsetjava.plugin.event.vehicle.VehicleStreamInEvent;
import net.onfirenetwork.onsetjava.plugin.event.vehicle.VehicleStreamOutEvent;

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
            case "OnPlayerSpawn": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null)
                    return new PlayerSpawnEvent(player);
            } break;
            case "OnPlayerQuit": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null){
                    ServerJNI.getInstance().removePlayer(player);
                    return new PlayerQuitEvent(player);
                }
            } break;
            case "OnPlayerChat": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null)
                    return new PlayerChatEvent(player, (String) args[1]);
            } break;
            case "OnPlayerDamage": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null)
                    return new PlayerDamageEvent(player, DamageType.get((Integer) args[1]),(Double) args[2]);
            } break;
            case "OnPlayerStateChange": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null)
                    return new PlayerStateEvent(player, PlayerState.get((Integer) args[1]), PlayerState.get((Integer) args[2]));
            } break;
            case "OnPlayerChatCommand": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null)
                    return new PlayerCommandEvent(player, (String) args[1], (Boolean) args[2]);
            } break;
            case "OnPlayerPickupHit": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null)
                    return new PlayerPickupHitEvent(player, Onset.getPickup((Integer) args[1]));
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
            case "OnPlayerEnterVehicle": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null){
                    return new PlayerEnterVehicleEvent(player, Onset.getVehicle((Integer) args[1]), (Integer) args[2]);
                }
            } break;
            case "OnPlayerLeaveVehicle": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                if (player != null){
                    return new PlayerExitVehicleEvent(player, Onset.getVehicle((Integer) args[1]), (Integer) args[2]);
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
            case "OnPlayerStreamIn": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                Player streamedPlayer = ServerJNI.getInstance().getPlayer((Integer) args[1]);
                if (player != null && streamedPlayer != null){
                    return new PlayerStreamInEvent(player, streamedPlayer);
                }
            } break;
            case "OnPlayerStreamOut": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[0]);
                Player streamedPlayer = ServerJNI.getInstance().getPlayer((Integer) args[1]);
                if (player != null && streamedPlayer != null){
                    return new PlayerStreamOutEvent(player, streamedPlayer);
                }
            } break;
            case "OnVehicleStreamIn": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[1]);
                if (player != null)
                    return new VehicleStreamInEvent(Onset.getVehicle((Integer) args[0]), player);
            } break;
            case "OnVehicleStreamOut": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[1]);
                if (player != null)
                    return new VehicleStreamOutEvent(Onset.getVehicle((Integer) args[0]), player);
            } break;
            case "OnVehicleRespawn": {
                return new VehicleRespawnEvent(Onset.getVehicle((Integer) args[0]));
            }
            case "OnVehicleDamage": {
                Vehicle vehicle = ServerJNI.getInstance().getVehicle((Integer) args[0]);
                if (vehicle != null) {
                    if (args.length == 4) {
                        return new VehicleDamageEvent(vehicle, (Double) args[1], (Integer) args[2], (Double) args[3]);
                    } else {
                        return new VehicleDamageEvent(vehicle, (Double) args[1], -1, 0);
                    }
                }
            } break;
            case "OnNPCStreamIn": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[1]);
                if (player != null)
                    return new NPCStreamInEvent(Onset.getNPC((Integer) args[0]), player);
            } break;
            case "OnNPCStreamOut": {
                Player player = ServerJNI.getInstance().getPlayer((Integer) args[1]);
                if (player != null)
                    return new NPCStreamOutEvent(Onset.getNPC((Integer) args[0]), player);
            } break;
            case "OnNPCSpawn": {
                return new NPCSpawnEvent(Onset.getNPC((Integer) args[0]));
            }
            case "OnNPCDeath": {
                return new NPCDeathEvent(Onset.getNPC((Integer) args[0]), Onset.getPlayer((Integer) args[0]));
            }
            case "OnNPCReachTarget": {
                return new NPCReachTargetEvent(Onset.getNPC((Integer) args[0]));
            }
            case "OnClientConnectionRequest": {
                return new ClientConnectionRequestEvent((String) args[0], (Integer) args[1]);
            }
        }
        return new UnknownEvent(name, args);
    }

}
