package si.uni_lj.fe.tnuv.slovenskismeh;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
    private LinearLayout eventCard5;
    private LinearLayout eventCard6;

    private EditText editSearchEvents;
    private TextView txtNoResultsEvents;

    private List<Event> allEvents;
    private List<LinearLayout> eventCardsList;
    private List<Comedian> allComedians;
    private static final String FALLBACK_IMAGE = "https://play-lh.googleusercontent.com/ZFEdWR7oVxxKYuuAqWeb2Bs0RliNS66EQBd-jiPH32rKtyKQXZUSGEHQeq1N8ywsvJQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        // Load comedians from JSON
        loadComediansFromJson();

        // Back button
        LinearLayout btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        navHome = findViewById(R.id.nav_home);
        navMap = findViewById(R.id.nav_map);

        iconHome = findViewById(R.id.icon_home);
        iconMap = findViewById(R.id.icon_map);
        iconEvents = findViewById(R.id.icon_events);
        textHome = findViewById(R.id.text_home);
        textMap = findViewById(R.id.text_map);
        textEvents = findViewById(R.id.text_events);

        editSearchEvents = findViewById(R.id.edit_search_events);
        txtNoResultsEvents = findViewById(R.id.txt_no_results_events);

        // Set events navigation as active (orange)
        iconEvents.setColorFilter(getResources().getColor(android.R.color.holo_orange_dark), android.graphics.PorterDuff.Mode.SRC_IN);
        textEvents.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));

        eventCard1 = findViewById(R.id.event_card_1);
        eventCard2 = findViewById(R.id.event_card_2);
        eventCard3 = findViewById(R.id.event_card_3);
        eventCard4 = findViewById(R.id.event_card_4);
        eventCard5 = findViewById(R.id.event_card_5);
        eventCard6 = findViewById(R.id.event_card_6);

        // Initialize events list and cards list
        eventCardsList = new ArrayList<>();
        eventCardsList.add(eventCard1);
        eventCardsList.add(eventCard2);
        eventCardsList.add(eventCard3);
        eventCardsList.add(eventCard4);
        eventCardsList.add(eventCard5);
        eventCardsList.add(eventCard6);

        // Create hardcoded events
        allEvents = new ArrayList<>();
        allEvents.add(new Event("Vid Valič: Stradanje", "Petek, 20. oktober", "20:00", "Kino Šiška",
                "Trg prekomorskih brigad 3, 1000 Ljubljana", "Stand-up Komedija",
                "Pridružite se Vidu Valiču v njegovi najnovejši stand-up uspešnici 'Stradanje'. Svež material, oster humor in nepozaben večer smeha v osrčju Ljubljane. Ne zamudite enega najbolj priljubljenih slovenskih komikov v živo.", "19:30", getComedianImageUrl("Vid Valič")));
        allEvents.add(new Event("Open Mic: Fresh Blood", "Nedelja, 22. oktober", "19:30", "10ka Club",
                "10ka, Ljubljana", "Open Mic",
                "Odkrijte nove komike in svež material na naši Open Mic noči. Odprta zavesa za skoraj režiserje. Žargon, improvisacija in nepredvideno zabavo vam garantiramo.", "19:00", FALLBACK_IMAGE));
        allEvents.add(new Event("Roast: Public Figures", "Sreda, 25. oktober", "21:00", "SNG Maribor",
                "SNG Maribor, Maribor", "Roast Show",
                "Ostra kritika javnih osebnosti s humorjem in ostro satiro. Najboljši komediji Slovenije se soočita s površnimi in pogosto absurdnimi mnenji raznih javnih figur.", "20:30", FALLBACK_IMAGE));
        allEvents.add(new Event("Tadej Toš v živo", "Sobota, 28. oktober", "20:00", "Kulturni dom Celje",
                "Kulturni dom Celje, Celje", "Stand-up Komedija",
                "Tadej Toš prinaša svoj najnovejši stand-up program z veliko smeha in zabave. Njegov ciničen in filozofski tone vas bo nasmejal do solz.", "19:30", getComedianImageUrl("Tadej Toš")));
        allEvents.add(new Event("Uroš Kuzman: Šolski spomini", "Petek, 24. oktober", "19:30", "Prešeren Theater",
                "Prešeren Theater, Ljubljana", "Stand-up Komedija",
                "Uroš Kuzman, doktor matematike in komik, prinaša inteligenten humor o šolskih skupinah in družini. Kombinacija znanstvene natančnosti in tople pripovednosti.", "19:00", getComedianImageUrl("Uroš Kuzman")));
        allEvents.add(new Event("Gašper Bergant: Fejmiči", "Nedelja, 25. oktober", "20:00", "Tivoli Hall",
                "Tivoli Hall, Ljubljana", "Stand-up Komedija",
                "Gašper Bergant, eden najbolj opaznih mlajših komikov, s svojim surovim humorjem in dialektom prineša nepozabno večerjo. Njegove predstave so energične in dirljive.", "19:30", getComedianImageUrl("Gašper Bergant")));

        // Load event images
        loadEventImages();

        // Set up search
        editSearchEvents.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterEvents(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

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
        eventCard5.setOnClickListener(v -> openEventDetail("Uroš Kuzman: Šolski spomini"));
        eventCard6.setOnClickListener(v -> openEventDetail("Gašper Bergant: Fejmiči"));
    }

    private void filterEvents(String query) {
        if (query.isEmpty()) {
            // Show all events
            for (LinearLayout card : eventCardsList) {
                card.setVisibility(View.VISIBLE);
            }
            txtNoResultsEvents.setVisibility(View.GONE);
        } else {
            // Filter events based on query
            String lowerQuery = query.toLowerCase();
            String[] words = lowerQuery.split("\\s+");
            int visibleCount = 0;

            for (int i = 0; i < allEvents.size(); i++) {
                Event event = allEvents.get(i);
                String searchableText = (event.getTitle() + " " + event.getVenue()).toLowerCase();

                boolean match = true;
                for (String word : words) {
                    if (!searchableText.contains(word)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    eventCardsList.get(i).setVisibility(View.VISIBLE);
                    visibleCount++;
                } else {
                    eventCardsList.get(i).setVisibility(View.GONE);
                }
            }

            // Show/hide no results message
            if (visibleCount == 0) {
                txtNoResultsEvents.setVisibility(View.VISIBLE);
            } else {
                txtNoResultsEvents.setVisibility(View.GONE);
            }
        }
    }

    private void openEventDetail(String eventTitle) {
        Intent intent = new Intent(EventsActivity.this, EventDetailActivity.class);
        intent.putExtra("event_title", eventTitle);
        startActivity(intent);
    }

    private void loadComediansFromJson() {
        try {
            InputStream inputStream = getAssets().open("komiki.json");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer);

            Gson gson = new Gson();
            Type type = new TypeToken<List<Comedian>>() {}.getType();
            allComedians = gson.fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
            allComedians = new ArrayList<>();
        }
    }

    private String getComedianImageUrl(String comedianName) {
        if (allComedians == null || allComedians.isEmpty()) {
            return FALLBACK_IMAGE;
        }

        for (Comedian comedian : allComedians) {
            String fullName = comedian.getFullName();
            if (fullName.equalsIgnoreCase(comedianName)) {
                return comedian.getImageUrl();
            }
        }
        return FALLBACK_IMAGE;
    }

    private void loadEventImages() {
        // Load image for event card 1 (Vid Valič: Stradanje)
        ImageView img1 = eventCard1.findViewById(R.id.event_image_1);
        if (img1 != null && allEvents.size() > 0) {
            String imageUrl = allEvents.get(0).getImageUrl();
            if (imageUrl != null) {
                Glide.with(this).load(imageUrl).centerCrop().into(img1);
            }
        }

        // Load image for event card 2 (Open Mic: Fresh Blood)
        ImageView img2 = eventCard2.findViewById(R.id.event_image_2);
        if (img2 != null && allEvents.size() > 1) {
            String imageUrl = allEvents.get(1).getImageUrl();
            if (imageUrl != null) {
                Glide.with(this).load(imageUrl).centerCrop().into(img2);
            }
        }

        // Load image for event card 3 (Roast: Public Figures)
        ImageView img3 = eventCard3.findViewById(R.id.event_image_3);
        if (img3 != null && allEvents.size() > 2) {
            String imageUrl = allEvents.get(2).getImageUrl();
            if (imageUrl != null) {
                Glide.with(this).load(imageUrl).centerCrop().into(img3);
            }
        }

        // Load image for event card 4 (Tadej Toš v živo)
        ImageView img4 = eventCard4.findViewById(R.id.event_image_4);
        if (img4 != null && allEvents.size() > 3) {
            String imageUrl = allEvents.get(3).getImageUrl();
            if (imageUrl != null) {
                Glide.with(this).load(imageUrl).centerCrop().into(img4);
            }
        }

        // Load image for event card 5 (Uroš Kuzman: Šolski spomini)
        ImageView img5 = eventCard5.findViewById(R.id.event_image_5);
        if (img5 != null && allEvents.size() > 4) {
            String imageUrl = allEvents.get(4).getImageUrl();
            if (imageUrl != null) {
                Glide.with(this).load(imageUrl).centerCrop().into(img5);
            }
        }

        // Load image for event card 6 (Gašper Bergant: Fejmiči)
        ImageView img6 = eventCard6.findViewById(R.id.event_image_6);
        if (img6 != null && allEvents.size() > 5) {
            String imageUrl = allEvents.get(5).getImageUrl();
            if (imageUrl != null) {
                Glide.with(this).load(imageUrl).centerCrop().into(img6);
            }
        }
    }
}