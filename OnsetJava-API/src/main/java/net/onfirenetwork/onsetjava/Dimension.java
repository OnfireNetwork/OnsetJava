package net.onfirenetwork.onsetjava;

import net.onfirenetwork.onsetjava.entity.Player;
import net.onfirenetwork.onsetjava.entity.Vehicle;

import java.util.List;

public interface Dimension {
    int getId();
    List<Player> getPlayers();
    List<Vehicle> getVehicles();
}
