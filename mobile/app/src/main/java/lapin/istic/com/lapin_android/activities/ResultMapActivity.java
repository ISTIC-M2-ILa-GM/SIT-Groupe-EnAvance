package lapin.istic.com.lapin_android.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import lapin.istic.com.lapin_android.R;
import lapin.istic.com.lapin_android.model.DronePath;
import lapin.istic.com.lapin_android.model.Point;

public class ResultMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private double height = 0;
    private List<Point> listPoint;
    private Button button1;
    private EditText searchview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_map);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        listPoint = new ArrayList<>();
        button1 = (Button) findViewById(R.id.button1);
        searchview = (EditText) findViewById(R.id.searchView);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = searchview.getText().toString();
                if (!text.matches("")) {
                    height = Double.parseDouble(text);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Change the map type based on the user's selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            case R.id.send_drone:
                DronePath dronePath = new DronePath();
                if (listPoint.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Cannot Send Drone! \n No points selected!", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    //ToDo Sent Drone Path to Service
                    dronePath.setPoints(listPoint);

                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleM) {
        if (googleM == null) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                    .show();
            finish();
        } else {
            googleMap = googleM;
            // and move the map's camera to the same location.
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                        getBaseContext(), "ACCESS LOCATION DENIED", Toast.LENGTH_SHORT).show();
                return;
            }
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(locationGPS.getLatitude(), locationGPS.getLongitude()), 16));
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(
                        getBaseContext(), "ACCESS LOCATION DENIED", Toast.LENGTH_SHORT).show();

                return;
            }
            googleMap.setMyLocationEnabled(true);
            googleMap.setTrafficEnabled(true);
            googleMap.setIndoorEnabled(true);
            googleMap.setBuildingsEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);

            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng point) {
                    //Do your stuff with LatLng here
                    //Then pass LatLng to other activity
                    createMarker(point.latitude, point.longitude, "Hauteur: " + height, "[" + point.latitude + ", " + point.latitude + "]");
                    listPoint.add(new Point(point.latitude, point.longitude, height));
                    for (Point p : listPoint) {
                        Log.d("Points:  ", p.toString());
                    }
                }
            });
        }
    }

    private void createMarker(double latitude, double longitude, String title, String snippet) {
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.test);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 84, 84, false);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet))
                .setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));
    }

}
