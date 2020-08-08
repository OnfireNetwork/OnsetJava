package net.onfirenetwork.onsetjava.entity;

public interface Timer {
    int getId();
    void destroy();
    void pause();
    void unpause();
    double getRemainingTime();
}
