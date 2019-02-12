package lapin.istic.com.lapin_android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lapin.istic.com.lapin_android.R;

/**
 * @author KADRI Noureddine
 */
public class MainActivity extends AppCompatActivity {
    private Button startFlying;
    private Button resultMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFlying = (Button) findViewById(R.id.start_flying);

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
    }
}
