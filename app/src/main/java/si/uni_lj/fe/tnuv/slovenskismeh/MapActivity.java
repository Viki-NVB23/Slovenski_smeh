package si.uni_lj.fe.tnuv.slovenskismeh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LinearLayout navHome;
    private LinearLayout navEvents;

    private LinearLayout comedianCard1;
    private LinearLayout comedianCard2;
    private LinearLayout comedianCard3;

    private GoogleMap mMap;

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

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map_fragment_container);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void openProfile(String comedianName) {
        Intent intent = new Intent(MapActivity.this, ComedianProfileActivity.class);
        intent.putExtra("comedian_name", comedianName);
        startActivity(intent);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ljubljana = new LatLng(46.0569, 14.5058);
        LatLng maribor = new LatLng(46.5547, 15.6459);

        mMap.addMarker(new MarkerOptions().position(ljubljana).title("Vid Valič"));
        mMap.addMarker(new MarkerOptions().position(maribor).title("Martina Ipša"));
        mMap.addMarker(new MarkerOptions().position(ljubljana).title("Perica Jerković"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.1512, 14.9955), 7.0f));
    }
}