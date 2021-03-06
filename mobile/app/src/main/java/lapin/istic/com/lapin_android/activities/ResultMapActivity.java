package lapin.istic.com.lapin_android.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lapin.istic.com.lapin_android.R;
import lapin.istic.com.lapin_android.db.DBHandler;
import lapin.istic.com.lapin_android.model.ApiManager;
import lapin.istic.com.lapin_android.model.Point;
import lapin.istic.com.lapin_android.model.Result;
import lapin.istic.com.lapin_android.utils.ImageParser;
import lapin.istic.com.lapin_android.utils.TestConstante;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private List<Point> listPoint;
    private Result result;
    private DBHandler dbHandler;
    private ArrayList<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_map);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        listPoint = new ArrayList<>();
        dbHandler = new DBHandler(this);
        Intent intent = getIntent();
        final String resultId = intent.getStringExtra("resultat");
        String missionId = intent.getStringExtra("mission");
        ApiManager apiManager = ApiManager.getInstance();
//        String base64encode = TestConstante.TESTIMAGE;
//        createImage("test", base64encode);
        Callback<Result> callback = new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    result = response.body();
                    String base64encode = result.getImageBase64();
                    String imageName = "image_" + resultId;
                    createImage(imageName, base64encode, result.getPoint());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
            }
        };
        if (resultId != null && missionId != null) {
            apiManager.getResult(missionId, resultId, callback);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
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
            for (Point point : listPoint) {
                for (int i = 0; i < images.size(); i++) {
                    Bitmap b = BitmapFactory.decodeFile(images.get(i));
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, 84, 84, false);
                    createMarker(point.getX(), point.getY(), "Hauteur: " + point.getZ(), "[" + point.getX() + ", " + point.getY() + "]", smallMarker);
                }

            }
        }
    }

    private void createMarker(double latitude, double longitude, String title, String snippet, Bitmap bitmap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet))
                .setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
    }

    private void createImage(String imageName, String base64encode, Point point) {
        Bitmap bitmap = ImageParser.convertToImage(base64encode);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "photos_lapin" + File.separator;
        File photosLapin = new File(filePath);
        if (!photosLapin.exists()) {
            photosLapin.mkdir();
        }
        String imagePath = filePath + imageName + ".jpg";
        dbHandler.addPoint(point);
        File file = new File(imagePath);
        images.add(imagePath);
        listPoint.add(point);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);

        try {
            file.createNewFile();
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(byteArrayOutputStream.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
