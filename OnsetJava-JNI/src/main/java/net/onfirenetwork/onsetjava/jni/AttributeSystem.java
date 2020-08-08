package net.onfirenetwork.onsetjava.jni;

import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.entity.*;

import java.util.*;
import java.util.stream.Collectors;

public class AttributeSystem {

    private static Map<Integer, Map<String, Object>> playerAttributes = new HashMap<>();
    private static Map<Integer, Map<String, Object>> vehicleAttributes = new HashMap<>();
    private static Map<Integer, Map<String, Object>> doorAttributes = new HashMap<>();
    private static Map<Integer, Map<String, Object>> npcAttributes = new HashMap<>();
    private static Map<Integer, Map<String, Object>> pickupAttributes = new HashMap<>();
    private static Map<Integer, Map<String, Object>> worldObjectAttributes = new HashMap<>();
    private static Map<Integer, Map<String, Object>> text3DAttributes = new HashMap<>();

    public static Map<String, Object> getPlayerAttributes(int id){
        if(!playerAttributes.containsKey(id))
            playerAttributes.put(id, new HashMap<>());
        return playerAttributes.get(id);
    }

    public static Map<String, Object> getVehicleAttributes(int id){
        if(!vehicleAttributes.containsKey(id))
            vehicleAttributes.put(id, new HashMap<>());
        return vehicleAttributes.get(id);
    }

    public static Map<String, Object> getDoorAttributes(int id){
        if(!doorAttributes.containsKey(id))
            doorAttributes.put(id, new HashMap<>());
        return doorAttributes.get(id);
    }

    public static Map<String, Object> getNPCAttributes(int id){
        if(!npcAttributes.containsKey(id))
            npcAttributes.put(id, new HashMap<>());
        return npcAttributes.get(id);
    }

    public static Map<String, Object> getPickupAttributes(int id){
        if(!pickupAttributes.containsKey(id))
            pickupAttributes.put(id, new HashMap<>());
        return pickupAttributes.get(id);
    }

    public static Map<String, Object> getObjectAttributes(int id){
        if(!worldObjectAttributes.containsKey(id))
            worldObjectAttributes.put(id, new HashMap<>());
        return worldObjectAttributes.get(id);
    }

    public static Map<String, Object> getText3DAttributes(int id){
        if(!text3DAttributes.containsKey(id))
            text3DAttributes.put(id, new HashMap<>());
        return text3DAttributes.get(id);
    }

    public static void cleanupVehicles(){
        List<Integer> ids = Onset.getVehicles().stream().map(Vehicle::getId).collect(Collectors.toList());
        for(int id : new HashSet<>(vehicleAttributes.keySet())){
            if(!ids.contains(id))
                vehicleAttributes.remove(id);
        }
    }

    public static void cleanupDoors(){
        List<Integer> ids = Onset.getDoors().stream().map(Door::getId).collect(Collectors.toList());
        for(int id : new HashSet<>(doorAttributes.keySet())){
            if(!ids.contains(id))
                doorAttributes.remove(id);
        }
    }

    public static void cleanupNPCs(){
        List<Integer> ids = Onset.getNPCs().stream().map(NPC::getId).collect(Collectors.toList());
        for(int id : new HashSet<>(npcAttributes.keySet())){
            if(!ids.contains(id))
                npcAttributes.remove(id);
        }
    }

    public static void cleanupPickups(){
        List<Integer> ids = Onset.getPickups().stream().map(Pickup::getId).collect(Collectors.toList());
        for(int id : new HashSet<>(pickupAttributes.keySet())){
            if(!ids.contains(id))
                pickupAttributes.remove(id);
        }
    }

    public static void cleanupWorldObjects(){
        List<Integer> ids = Onset.getObjects().stream().map(WorldObject::getId).collect(Collectors.toList());
        for(int id : new HashSet<>(worldObjectAttributes.keySet())){
            if(!Onset.getServer().isValidObject(id))
                worldObjectAttributes.remove(id);
        }
    }

    public static void cleanupText3Ds(){
        List<Integer> ids = Onset.getText3Ds().stream().map(Text3D::getId).collect(Collectors.toList());
        for(int id : new HashSet<>(text3DAttributes.keySet())){
            if(!ids.contains(id))
                text3DAttributes.remove(id);
        }
    }

    public static void cleanup(){
        cleanupVehicles();
        cleanupDoors();
        cleanupNPCs();
        cleanupPickups();
        cleanupText3Ds();
        cleanupWorldObjects();
    }

    public static void destroyedPlayer(Integer id){
        playerAttributes.remove(id);
    }

    public static void destroyedVehicle(Integer id){
        vehicleAttributes.remove(id);
    }

    public static void destroyedDoor(Integer id){
        doorAttributes.remove(id);
    }

    public static void destroyedNPC(Integer id){
        npcAttributes.remove(id);
    }

    public static void destroyedPickup(Integer id){
        pickupAttributes.remove(id);
    }

    public static void destroyedWorldObject(Integer id){
        worldObjectAttributes.remove(id);
    }

    public static void destroyedText3D(Integer id){
        text3DAttributes.remove(id);
    }

}
