package si.uni_lj.fe.tnuv.slovenskismeh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    private LinearLayout navHome;
    private LinearLayout navEvents;

    private LinearLayout comedianCard1;
    private LinearLayout comedianCard2;
    private LinearLayout comedianCard3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        navHome = findViewById(R.id.nav_home);
        navEvents = findViewById(R.id.nav_events);

        comedianCard1 = findViewById(R.id.comedian_card_1);
        comedianCard2 = findViewById(R.id.comedian_card_2);
        comedianCard3 = findViewById(R.id.comedian_card_3);

        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(MapActivity.this, MainActivity.class);
            startActivity(intent);
        });

        navEvents.setOnClickListener(v -> {
            Intent intent = new Intent(MapActivity.this, EventsActivity.class);
            startActivity(intent);
        });

        comedianCard1.setOnClickListener(v -> openProfile("Vid Valič"));
        comedianCard2.setOnClickListener(v -> openProfile("Martina Ipša"));
        comedianCard3.setOnClickListener(v -> openProfile("Perica Jerković"));
    }

    private void openProfile(String comedianName) {
        Intent intent = new Intent(MapActivity.this, ComedianProfileActivity.class);
        intent.putExtra("comedian_name", comedianName);
        startActivity(intent);
    }
}