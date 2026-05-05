package si.uni_lj.fe.tnuv.slovenskismeh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LinearLayout navHome;
    private LinearLayout navEvents;

    private ImageView iconHome;
    private ImageView iconMap;
    private ImageView iconEvents;
    private TextView textHome;
    private TextView textMap;
    private TextView textEvents;

    private LinearLayout comedianCard1;
    private LinearLayout comedianCard2;
    private LinearLayout comedianCard3;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Back button
        LinearLayout btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        navHome = findViewById(R.id.nav_home);
        navEvents = findViewById(R.id.nav_events);

        iconHome = findViewById(R.id.icon_home);
        iconMap = findViewById(R.id.icon_map);
        iconEvents = findViewById(R.id.icon_events);
        textHome = findViewById(R.id.text_home);
        textMap = findViewById(R.id.text_map);
        textEvents = findViewById(R.id.text_events);

        // Set map navigation as active (orange)
        iconMap.setColorFilter(getResources().getColor(android.R.color.holo_orange_dark), android.graphics.PorterDuff.Mode.SRC_IN);
        textMap.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));

        comedianCard1 = findViewById(R.id.comedian_card_1);
        comedianCard2 = findViewById(R.id.comedian_card_2);
        comedianCard3 = findViewById(R.id.comedian_card_3);

        // Load comedian images
        ImageView imgComedian1 = findViewById(R.id.img_comedian_1);
        ImageView imgComedian2 = findViewById(R.id.img_comedian_2);
        ImageView imgComedian3 = findViewById(R.id.img_comedian_3);

        // Vid Valič
        Glide.with(this)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS2uGG0ympfwwIFoOF0Z6_eBBL7F-WQi04rZg&s")
                .centerCrop()
                .into(imgComedian1);

        // Martina Ipša
        Glide.with(this)
                .load("https://govorise.metropolitan.si/media/cache/upload/Photo/2014/02/19/s08_18_ipsa_xxx_i988x656.jpg")
                .centerCrop()
                .into(imgComedian2);

        // Perica Jerković
        Glide.with(this)
                .load("https://www.metropolitan.si/media/cache/upload/Photo/2024/12/09/perica-jerkovic_biggalleryimage.png")
                .centerCrop()
                .into(imgComedian3);

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