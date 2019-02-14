package lapin.istic.com.lapin_android.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author DESCHAMPS Mathieu
 */
public class Result {



    @SerializedName("point")
    private Point point;
    @SerializedName("picture")
    private String imageBase64;


    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
