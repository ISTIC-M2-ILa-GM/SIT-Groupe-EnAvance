package lapin.istic.com.lapin_android.components;

public class Point2 {
    private double latitude;
    private double longitude;
    private double hauteur;

    public Point2(double latitude, double longitude, double hauteur) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.hauteur = hauteur;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    @Override
    public String toString() {
        return "Point2{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", hauteur=" + hauteur +
                '}';
    }


}
