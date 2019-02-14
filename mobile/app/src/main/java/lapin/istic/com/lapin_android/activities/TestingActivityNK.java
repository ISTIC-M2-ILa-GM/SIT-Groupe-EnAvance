package lapin.istic.com.lapin_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import lapin.istic.com.lapin_android.R;
import lapin.istic.com.lapin_android.model.DronePath;
import lapin.istic.com.lapin_android.model.MainApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Noureddine KADRI
 */
public class TestingActivityNK extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText;
    private EditText jobEditText;
    private Button saveUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_nk);
        nameEditText = findViewById(R.id.name);
        jobEditText = findViewById(R.id.job);
        saveUserButton = findViewById(R.id.save_user);
        saveUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_user:
                DronePath dronePath = new DronePath();
                dronePath.setId( nameEditText.getText().toString());
                MainApplication.apiManager.createUser(dronePath, new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String responseDrone = response.body();
                        if (response.isSuccessful() && responseDrone != null) {
                            Toast.makeText(TestingActivityNK.this,
                                    String.format("Mission %s with job %s was created at %s with id %s",
                                            responseDrone,
                                            responseDrone),
                                    Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            Toast.makeText(TestingActivityNK.this,
                                    String.format("Response is %s", String.valueOf(response.code()))
                                    , Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(TestingActivityNK.this,
                                "Error is " + t.getMessage()
                                , Toast.LENGTH_LONG).show();
                    }
                });

                break;
        }
    }
}
