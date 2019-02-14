package lapin.istic.com.lapin_android.model;

import java.util.List;

/**
 * @author DESCHAMPS Mathieu
 */
public class DronePath {

    private String id;
    private List<Point> points;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
