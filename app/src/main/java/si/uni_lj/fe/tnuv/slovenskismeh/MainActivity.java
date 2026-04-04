package si.uni_lj.fe.tnuv.slovenskismeh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout comedian1;
    private LinearLayout comedian2;
    private LinearLayout comedian3;
    private LinearLayout comedian4;

    private LinearLayout navMap;
    private LinearLayout navEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comedian1 = findViewById(R.id.comedian_1);
        comedian2 = findViewById(R.id.comedian_2);
        comedian3 = findViewById(R.id.comedian_3);
        comedian4 = findViewById(R.id.comedian_4);

        navMap = findViewById(R.id.nav_map);
        navEvents = findViewById(R.id.nav_events);

        comedian1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ComedianProfileActivity.class);
            startActivity(intent);
        });

        comedian2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ComedianProfileActivity.class);
            startActivity(intent);
        });

        comedian3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ComedianProfileActivity.class);
            startActivity(intent);
        });

        comedian4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ComedianProfileActivity.class);
            startActivity(intent);
        });

        navMap.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        });

        navEvents.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EventsActivity.class);
            startActivity(intent);
        });
    }
}