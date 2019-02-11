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
    private EditText hauteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFlying = (Button) findViewById(R.id.start_flying);
        hauteur = (EditText) findViewById(R.id.hauteur);


        startFlying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h = hauteur.getText().toString();
                if (!h.matches("")) {
                    Intent intent = new Intent(getBaseContext(), LocationActivity.class);
                    intent.putExtra("hauteurIntent", h);
                    startActivityForResult(intent, 0);
                } else {
                    Toast.makeText(
                            getBaseContext(), "Please Input the Height!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
