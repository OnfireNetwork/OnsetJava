package net.onfirenetwork.onsetjava.entity;

import net.onfirenetwork.onsetjava.Dimension;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.clientdsl.LF;
import net.onfirenetwork.onsetjava.component.PlayerComponent;
import net.onfirenetwork.onsetjava.data.Location;
import net.onfirenetwork.onsetjava.data.NetworkStats;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.data.Weapon;
import net.onfirenetwork.onsetjava.enums.Animation;
import net.onfirenetwork.onsetjava.enums.PlayerState;
import net.onfirenetwork.onsetjava.enums.WeaponModel;

import java.util.List;

public interface Player extends ComponentEntity<PlayerComponent>, AttributeEntity, HitEntity, PropertyEntity, AttachmentEntity {

    int getId();
    String getName();
    void setName(String name);
    String getSteamId();
    void sendMessage(String message);
    void kick(String message);
    Vector getLocation();
    void setLocation(Vector location);
    double getHeading();
    void setHeading(double heading);
    void setLocationAndHeading(Location location);
    Location getLocationAndHeading();
    default void setSpawnLocation(Location location){
        setSpawnLocation(location, location.getHeading());
    }
    void setSpawnLocation(Vector location, double heading);
    int getDimensionId();
    void setDimension(int dimension);
    default void setDimension(Dimension dimension){
        setDimension(dimension.getId());
    }
    default Dimension getDimension(){
        return Onset.getDimension(getDimensionId());
    }
    void setRagdoll(boolean ragdoll);
    void setVoiceEnabled(boolean enabled);
    boolean isVoiceEnabled();
    boolean isTalking();
    void setSoundDimension(int dimension);
    boolean isAiming();
    boolean isReloading();
    Weapon getWeapon(int slot);
    default void setWeapon(int slot, Weapon weapon){
        setWeapon(slot, weapon.getModel(), weapon.getAmmo());
    }
    default void setWeapon(int slot, int model, int ammo){
        setWeapon(slot, model, ammo, false);
    }
    default void setWeapon(int slot, Weapon weapon, boolean equip){
        setWeapon(slot, weapon.getModel(), weapon.getAmmo(), equip);
    }
    default void setWeapon(int slot, int model, int ammo, boolean equip){
        setWeapon(slot, model, ammo, equip, false);
    }
    default void setWeapon(int slot, Weapon weapon, boolean equip, boolean loaded){
        setWeapon(slot, weapon.getModel(), weapon.getAmmo(), equip, loaded);
    }
    void setVoiceChannel(int channelId, boolean enabled);
    void setWeapon(int slot, int model, int ammo, boolean equip, boolean loaded);
    void setWeaponSlot(int slot);
    int getWeaponSlot();
    default void setWeaponStat(WeaponModel model, String stat, Object value){
        setWeaponStat(model.id(), stat, value);
    }
    void setWeaponStat(int model, String stat, Object value);
    boolean isDead();
    void setRespawnTime(int time);
    int getRespawnTime();
    int getPing();
    String getGUID();
    int getGameVersion();
    PlayerState getState();
    void setParachute(boolean parachute);
    void setHealth(double health);
    double getHealth();
    void setArmor(double armor);
    double getArmor();
    String getAddress();
    String getLocale();
    default void setAnimation(Animation animation){
        setAnimation(animation.name());
    }
    void setAnimation(String animation);
    void setHeadSize(double size);
    double getHeadSize();
    void enterVehicle(Vehicle vehicle);
    void enterVehicle(Vehicle vehicle, int seat);
    void exitVehicle();
    Vehicle getVehicle();
    int getVehicleSeat();
    void setSpectator(boolean spectator);
    void callRemoteEvent(String name, Object... args);
    void execute(String script);
    void execute(LF script);
    boolean isStreamed(Player player);
    NetworkStats getNetworkStats();
    List<Player> getStreamedPlayers();
    void addVoiceChannel(int channel);
    void removeVoiceChannel(int channel);
    void setVoiceDimension(int dimensionId);
}
