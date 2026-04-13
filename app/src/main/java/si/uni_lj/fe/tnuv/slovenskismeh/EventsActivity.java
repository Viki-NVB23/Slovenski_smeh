package si.uni_lj.fe.tnuv.slovenskismeh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventsActivity extends AppCompatActivity {

    private LinearLayout navHome;
    private LinearLayout navMap;

    private ImageView iconHome;
    private ImageView iconMap;
    private ImageView iconEvents;
    private TextView textHome;
    private TextView textMap;
    private TextView textEvents;

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

        iconHome = findViewById(R.id.icon_home);
        iconMap = findViewById(R.id.icon_map);
        iconEvents = findViewById(R.id.icon_events);
        textHome = findViewById(R.id.text_home);
        textMap = findViewById(R.id.text_map);
        textEvents = findViewById(R.id.text_events);

        // Set events navigation as active (orange)
        iconEvents.setColorFilter(getResources().getColor(android.R.color.holo_orange_dark), android.graphics.PorterDuff.Mode.SRC_IN);
        textEvents.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));

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