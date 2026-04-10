package si.uni_lj.fe.tnuv.slovenskismeh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class EventsActivity extends AppCompatActivity {

    private LinearLayout navHome;
    private LinearLayout navMap;

    private LinearLayout eventCard1;
    private LinearLayout eventCard2;
    private LinearLayout eventCard3;
    private LinearLayout eventCard4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        navHome = findViewById(R.id.nav_home);
        navMap = findViewById(R.id.nav_map);

        eventCard1 = findViewById(R.id.event_card_1);
        eventCard2 = findViewById(R.id.event_card_2);
        eventCard3 = findViewById(R.id.event_card_3);
        eventCard4 = findViewById(R.id.event_card_4);

        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(EventsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        navMap.setOnClickListener(v -> {
            Intent intent = new Intent(EventsActivity.this, MapActivity.class);
            startActivity(intent);
        });

        eventCard1.setOnClickListener(v -> openEventDetail("Vid Valič: Stradanje"));
        eventCard2.setOnClickListener(v -> openEventDetail("Open Mic: Fresh Blood"));
        eventCard3.setOnClickListener(v -> openEventDetail("Roast: Public Figures"));
        eventCard4.setOnClickListener(v -> openEventDetail("Tadej Toš v živo"));
    }

    private void openEventDetail(String eventTitle) {
        Intent intent = new Intent(EventsActivity.this, EventDetailActivity.class);
        intent.putExtra("event_title", eventTitle);
        startActivity(intent);
    }
}