package net.onfirenetwork.onsetjava.jni.data;

import net.onfirenetwork.onsetjava.data.NetworkStats;

import java.util.Map;

public class NetworkStatsJNI implements NetworkStats {

    private Map<String, Object> table;

    public NetworkStatsJNI(Map<String, Object> table){
        this.table = table;
    }

    public double getPacketLossTotal(){
        return (Double) table.get("packetlossTotal");
    }

    public double getPacketLossLastSecond(){
        return (Double) table.get("packetlossLastSecond");
    }

    public int getMessagesInResendBuffer(){
        return (Integer) table.get("messagesInResendBuffer");
    }

    public int getBytesInResendBuffer(){
        return (Integer) table.get("bytesInResendBuffer");
    }

    public int getBytesSend(){
        return (Integer) table.get("bytesSend");
    }

    public int getBytesReceived(){
        return (Integer) table.get("bytesReceived");
    }

    public int getBytesResent(){
        return (Integer) table.get("bytesResent");
    }

    public int getBytesSendTotal(){
        return (Integer) table.get("bytesSendTotal");
    }

    public int getBytesReceivedTotal(){
        return (Integer) table.get("bytesReceivedTotal");
    }

    public int getBytesResentTotal(){
        return (Integer) table.get("bytesResentTotal");
    }

    public boolean isLimitedByCongestionControl(){
        return (Boolean) table.get("isLimitedByCongestionControl");
    }

    public boolean isLimitedByOutgoingBandwidthLimit(){
        return (Boolean) table.get("isLimitedByOutgoingBandwidthLimit");
    }

}
