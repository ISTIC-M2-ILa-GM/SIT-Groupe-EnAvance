package lapin.istic.com.lapin_android.activities;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import lapin.istic.com.lapin_android.R;

/**
 * @author KADRI Noureddine
 */
public class MainActivity extends AppCompatActivity {
    private Button startFlying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseInstanceId.getInstance().getInstanceId()
                          .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                              @Override
                              public void onComplete(@NonNull Task<InstanceIdResult> task) {

                                  if( !task.isSuccessful()){
                                      Log.w("MainActivity", "get instance failed", task.getException() );
                                      return;
                                  }
                                  String token = task.getResult().getToken();
                                  Log.d("MainActivity","token: "+token);
                              }
                          });
        startFlying = (Button) findViewById(R.id.start_flying);

        startFlying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), LocationActivity.class);
                    startActivityForResult(intent, 0);
            }
        });
    }
}
