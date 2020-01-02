package net.onfirenetwork.onsetjava.data;

public interface NetworkStats {

    double getPacketLossTotal();

    double getPacketLossLastSecond();

    int getMessagesInResendBuffer();

    int getBytesInResendBuffer();

    int getBytesSend();

    int getBytesReceived();

    int getBytesResent();

    int getBytesSendTotal();

    int getBytesReceivedTotal();

    int getBytesResentTotal();

    boolean isLimitedByCongestionControl();

    boolean isLimitedByOutgoingBandwidthLimit();

}
