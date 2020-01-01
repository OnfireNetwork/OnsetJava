package net.onfirenetwork.onsetjava.plugin;

import net.onfirenetwork.onsetjava.entity.Player;

public interface CommandExecutor {
    boolean onCommand(Player player, String name, String[] args);
}
