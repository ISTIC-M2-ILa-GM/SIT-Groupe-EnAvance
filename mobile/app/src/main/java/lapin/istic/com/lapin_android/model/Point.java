package lapin.istic.com.lapin_android.model;

import java.io.Serializable;

/**
 * @author DESCHAMPS Mathieu
 */
public class Point {

    //latitude
    private double x;
    //longitude
    private double y;
    //altitude (in meter)
    private double z;

    private int index;

    public Point(){

    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.index = -1;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Point {" +
                "x = " + x +
                ", y = " + y +
                ", z = " + z +
                ", index = " + index +
                '}';
    }
}
