package lapin.istic.com.lapin_android.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;

import lapin.istic.com.lapin_android.db.DBHandler;
import lapin.istic.com.lapin_android.model.*;

import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import lapin.istic.com.lapin_android.R;
import lapin.istic.com.lapin_android.services.DroneService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author KADRI Noureddine
 */
public class    LocationActivity extends AppCompatActivity
        implements OnMapReadyCallback {
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private double height = 0;
    private List<Point> listPoint;
    private Button button1;
    private EditText searchview;
    private  DroneService droneService;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_location);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
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

        //Service
         droneService = new Retrofit.Builder()
                .baseUrl(DroneService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DroneService.class);

         dbHandler = new DBHandler(this);
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
                    for(Point point: listPoint){
                        dbHandler.addPoint(point);
                    }
                    MainActivity.apiManager.createMissionApi(dronePath, new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String responseDrone = response.body();
                            if (response.isSuccessful() && responseDrone != null) {
                                Toast.makeText(LocationActivity.this,
                                        String.format("Mission was created at %s with id %s",
                                                responseDrone),
                                        Toast.LENGTH_LONG)
                                        .show();
                            } else {
                                Toast.makeText(LocationActivity.this,
                                        String.format("Response is %s", String.valueOf(response.code()))
                                        , Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(LocationActivity.this,
                                    "Error is " + t.getMessage()
                                    , Toast.LENGTH_LONG).show();
                        }
                    });



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
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
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
                    int index = 0;
                    for (Point p : listPoint) {
                        p.setIndex(index);
                        Log.d("Points:  ", p.toString());
                        index++;
                    }
                }
            });
        }
    }

    private void createMarker(double latitude, double longitude, String title, String snippet) {

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet));
    }

    
}


