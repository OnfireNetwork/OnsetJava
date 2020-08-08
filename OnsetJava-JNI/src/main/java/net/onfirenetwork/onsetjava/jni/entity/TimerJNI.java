package net.onfirenetwork.onsetjava.jni.entity;

import net.onfirenetwork.onsetjava.entity.Timer;
import net.onfirenetwork.onsetjava.jni.ServerJNI;

public class TimerJNI implements Timer {

    private int id;

    public TimerJNI(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void destroy(){
        ServerJNI.getInstance().packageBus.destroyTimer(id);
    }

    public void pause(){
        ServerJNI.callGlobal("PauseTimer", id);
    }

    public void unpause(){
        ServerJNI.callGlobal("UnpauseTimer", id);
    }

    public double getRemainingTime(){
        return (Double) ServerJNI.callGlobal("GetTimerRemainingTime", id)[0];
    }

}
