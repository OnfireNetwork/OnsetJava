package net.onfirenetwork.onsetjava.plugin.event;

@LuaEvent("OnClientConnectionRequest")
public class ClientConnectionRequestEvent extends Event implements Cancellable {
    private String ip;
    private int port;
    private boolean cancelled = false;
    public ClientConnectionRequestEvent(String ip, int port){
        this.ip = ip;
        this.port = port;
    }
    public String getIp() {
        return ip;
    }
    public int getPort() {
        return port;
    }
    public boolean isCancelled() {
        return cancelled;
    }
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
