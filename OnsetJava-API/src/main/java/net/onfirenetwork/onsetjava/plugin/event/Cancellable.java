package net.onfirenetwork.onsetjava.plugin.event;

public interface Cancellable {
    void setCancelled(boolean cancelled);
    boolean isCancelled();
    default void cancel(){
        setCancelled(true);
    }
}
