package lapin.istic.com.lapin_android.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import lapin.istic.com.lapin_android.R;
import lapin.istic.com.lapin_android.model.ApiManager;

/**
 * @author KADRI Noureddine && DESCHAMPS Mathieu
 */
public class MainActivity extends AppCompatActivity {

    public static final String TOPIC = "drone";
    private Button startFlying;
    private Button resultMap;
   // private Button testnk;
    public static ApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiManager = ApiManager.getInstance();
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                        if (!task.isSuccessful()) {
                            Log.w("MainActivity", "get instance failed", task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();
                        Log.d("MainActivity", "token: " + token);
                    }
                });
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Suscription done!";
                        if (!task.isSuccessful()) {
                            msg = "Error during suscription";

                        }
                        Log.w("Main Activity", msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        Intent intent = getIntent();
        if( intent != null && intent.getStringExtra("mission_id") != null )
        {
            Intent nintent =  new Intent(this,ResultMapActivity.class);
            intent.putExtra("mission",intent.getStringExtra("mission_id"));
            intent.putExtra("resultat",intent.getStringExtra("resultat_id"));
            startActivity(nintent);

        }
        startFlying = (Button) findViewById(R.id.start_flying);
       // testnk = (Button) findViewById(R.id.testnk);

        startFlying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LocationActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        resultMap = (Button) findViewById(R.id.show_result);

        resultMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ResultMapActivity.class);
                startActivity(intent);

            }
        });

       /* testnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PostTestingActivity.class);
                startActivityForResult(intent, 0);

            }
        });*/
    }

}
