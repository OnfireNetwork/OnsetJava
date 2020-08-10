package net.onfirenetwork.onsetjava.component;

import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Vehicle;
import net.onfirenetwork.onsetjava.plugin.event.EventHandler;
import net.onfirenetwork.onsetjava.plugin.event.player.*;
import net.onfirenetwork.onsetjava.plugin.event.vehicle.VehicleDamageEvent;
import net.onfirenetwork.onsetjava.plugin.event.vehicle.VehicleRespawnEvent;

import java.util.List;
import java.util.function.Consumer;

public class EntityComponentSystem {

    private static EntityComponentSystem instance;

    public static void init(){
        if(instance != null)
            return;
        instance = new EntityComponentSystem();
        Onset.registerListener(instance);
    }

    private static void applyEvents(Player player, Consumer<PlayerComponent> consumer){
        List<PlayerComponent> components = player.getComponents();
        components.forEach(c -> c.setEntity(player));
        components.forEach(consumer);
    }

    private static void applyEvents(Vehicle vehicle, Consumer<VehicleComponent> consumer){
        List<VehicleComponent> components = vehicle.getComponents();
        components.forEach(c -> c.setEntity(vehicle));
        components.forEach(consumer);
    }

    @EventHandler
    public void onPlayerSteamAuth(PlayerSteamAuthEvent e){
        applyEvents(e.getPlayer(), PlayerComponent::onSteamAuth);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        applyEvents(e.getPlayer(), PlayerComponent::onJoin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        applyEvents(e.getPlayer(), PlayerComponent::onQuit);
    }

    @EventHandler
    public void onPlayerEnterVehicle(PlayerEnterVehicleEvent e){
        applyEvents(e.getPlayer(), c -> c.onEnterVehicle(e.getVehicle(), e.getSeat()));
        applyEvents(e.getVehicle(), c -> c.onEnter(e.getPlayer(), e.getSeat()));
    }

    @EventHandler
    public void onPlayerExitVehicle(PlayerExitVehicleEvent e){
        applyEvents(e.getPlayer(), c -> c.onExitVehicle(e.getVehicle(), e.getSeat()));
        applyEvents(e.getVehicle(), c -> c.onExit(e.getPlayer(), e.getSeat()));
    }

    @EventHandler
    public void onPlayerRemoteEvent(PlayerRemoteEvent e){
        applyEvents(e.getPlayer(), c -> c.onRemoteEvent(e.getName(), e.getArgs()));
    }

    @EventHandler
    public void onPlayerWeaponShot(PlayerWeaponShotEvent e){
        applyEvents(e.getPlayer(), c -> {
            if(c.onWeaponShot(e.getWeaponModel(), e.getHitType(), e.getHitEntity(), e.getHitLocation(), e.getStartLocation(), e.getHitNormal()))
                e.setCancelled(true);
        });
    }

    @EventHandler
    public void onPlayerStateChange(PlayerStateEvent e){
        applyEvents(e.getPlayer(), c -> c.onStateChange(e.getNewState(), e.getOldState()));
    }

    @EventHandler
    public void onPlayerSpawn(PlayerSpawnEvent e){
        applyEvents(e.getPlayer(), PlayerComponent::onSpawn);
    }

    @EventHandler
    public void onPlayerPickupHit(PlayerPickupHitEvent e){
        applyEvents(e.getPlayer(), c -> c.onPickupHit(e.getPickup()));
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        applyEvents(e.getPlayer(), c -> c.onDeath(e.getKiller()));
    }

    @EventHandler
    public void onPlayerInteractDoor(PlayerInteractDoorEvent e){
        applyEvents(e.getPlayer(), c -> c.onInteractDoor(e.getDoor(), e.isOpenRequested()));
    }

    @EventHandler
    public void onPlayerDamage(PlayerDamageEvent e){
        applyEvents(e.getPlayer(), c -> c.onDamage(e.getDamageType(), e.getDamage()));
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e){
        applyEvents(e.getPlayer(), c -> c.onChat(e.getMessage()));
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandEvent e){
        applyEvents(e.getPlayer(), c -> {
            if(c.onCommand(e.getCommand(), e.doesExist()))
                e.setCancelled(true);
        });
    }

    @EventHandler
    public void onVehicleDamage(VehicleDamageEvent e){
        applyEvents(e.getVehicle(), c -> c.onDamage(e.getDamage(), e.getDamageIndex(), e.getMorphDamage()));
    }

    @EventHandler
    public void onVehicleRespawn(VehicleRespawnEvent e){
        applyEvents(e.getVehicle(), VehicleComponent::onRespawn);
    }

}
