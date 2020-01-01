package net.onfirenetwork.onsetjava.data;

public class Location extends Vector {
    private double heading;
    public Location(double x, double y, double z, double heading){
        super(x, y, z);
        this.heading = heading;
    }
    public Location(double x, double y, double z){
        this(x, y, z, 0);
    }
    public double getHeading() {
        return heading;
    }
    public void setHeading(double heading){
        this.heading = heading;
    }
}
