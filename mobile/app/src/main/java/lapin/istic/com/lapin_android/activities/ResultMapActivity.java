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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_map);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        listPoint = new ArrayList<>();
        dbHandler = new DBHandler(this);
        listPoint = dbHandler.getPoints();
        Intent intent = getIntent();
        final String resultId = intent.getStringExtra("resultat");
        String missionId = intent.getStringExtra("mission");
        ApiManager apiManager = ApiManager.getInstance();
        String base64encode = " data:image/jpeg;base64,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";
        createImage("test", base64encode);
        Callback<Result> callback = new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    result = response.body();
                    String base64encode = result.getImageBase64();
                    String imageName = "image_" + resultId
                    createImage(imageName, base64encode);
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
                createMarker(point.getX(), point.getY(), "Hauteur: " + point.getZ(), "[" + point.getX() + ", " + point.getY() + "]");
            }
        }
    }

    private void createMarker(double latitude, double longitude, String title, String snippet) {
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "photos_lapin" + File.separator + "test.jpg";
        Bitmap b = BitmapFactory.decodeFile(filePath);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 84, 84, false);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet))
                .setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));
    }

    private void createImage(String imageName, String base64encode) {
        Bitmap bitmap = ImageParser.convertToImage(base64encode);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "photos_lapin" + File.separator;
        File photosLapin = new File(filePath);
        if (!photosLapin.exists()) {
            photosLapin.mkdir();
        }
        File file = new File(filePath + imageName + ".jpg");
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
