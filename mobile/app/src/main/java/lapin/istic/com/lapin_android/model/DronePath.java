package lapin.istic.com.lapin_android.model;

import java.util.List;

/**
 * @author DESCHAMPS Mathieu
 */
public class DronePath {

    private int id;
    private List<Point> points;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
