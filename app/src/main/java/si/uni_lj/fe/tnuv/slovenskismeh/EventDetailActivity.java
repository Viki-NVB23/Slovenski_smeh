package si.uni_lj.fe.tnuv.slovenskismeh;

import android.os.Bundle;
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

public class EventDetailActivity extends AppCompatActivity {
    private static final String FALLBACK_IMAGE = "https://play-lh.googleusercontent.com/ZFEdWR7oVxxKYuuAqWeb2Bs0RliNS66EQBd-jiPH32rKtyKQXZUSGEHQeq1N8ywsvJQ";
    private List<Comedian> allComedians;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        // Load comedians from JSON
        loadComediansFromJson();

        // Back button
        LinearLayout btnBack = findViewById(R.id.top_navigation);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Get event title from intent
        String eventTitle = getIntent().getStringExtra("event_title");

        // Create events list (same as in EventsActivity)
        List<Event> allEvents = createEventsList();

        // Find the event by title
        Event event = null;
        for (Event e : allEvents) {
            if (e.getTitle().equals(eventTitle)) {
                event = e;
                break;
            }
        }

        // Display event data
        if (event != null) {
            displayEvent(event);
        }
    }

    private void displayEvent(Event event) {
        // Display hero image
        ImageView heroImage = findViewById(R.id.event_hero_image);
        if (heroImage != null && event.getImageUrl() != null) {
            Glide.with(this)
                    .load(event.getImageUrl())
                    .centerCrop()
                    .into(heroImage);
        }

        TextView titleView = findViewById(R.id.event_title);
        TextView dateView = findViewById(R.id.event_date_text);
        TextView doorsView = findViewById(R.id.event_doors_text);
        TextView venueView = findViewById(R.id.event_venue_title);
        TextView addressView = findViewById(R.id.event_venue_address);
        TextView categoryView = findViewById(R.id.event_category_text);
        TextView descriptionView = findViewById(R.id.event_description_text);

        if (titleView != null) titleView.setText(event.getTitle());
        if (dateView != null) dateView.setText(event.getDate() + " • " + event.getTime());
        if (doorsView != null) doorsView.setText("Vrata se odprejo ob " + event.getDoorsOpen());
        if (venueView != null) venueView.setText(event.getVenue());
        if (addressView != null) addressView.setText(event.getAddress());
        if (categoryView != null) categoryView.setText(event.getCategory());
        if (descriptionView != null) descriptionView.setText(event.getDescription());
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

    private List<Event> createEventsList() {
        List<Event> events = new ArrayList<>();
        events.add(new Event("Vid Valič: Stradanje", "Petek, 20. oktober", "20:00", "Kino Šiška",
                "Trg prekomorskih brigad 3, 1000 Ljubljana", "Stand-up Komedija",
                "Pridružite se Vidu Valiču v njegovi najnovejši stand-up uspešnici 'Stradanje'. Svež material, oster humor in nepozaben večer smeha v osrčju Ljubljane. Ne zamudite enega najbolj priljubljenih slovenskih komikov v živo.", "19:30", getComedianImageUrl("Vid Valič")));
        events.add(new Event("Open Mic: Fresh Blood", "Nedelja, 22. oktober", "19:30", "10ka Club",
                "10ka, Ljubljana", "Open Mic",
                "Odkrijte nove komike in svež material na naši Open Mic noči. Odprta zavesa za skoraj režiserje. Žargon, improvisacija in nepredvideno zabavo vam garantiramo.", "19:00", FALLBACK_IMAGE));
        events.add(new Event("Roast: Public Figures", "Sreda, 25. oktober", "21:00", "SNG Maribor",
                "SNG Maribor, Maribor", "Roast Show",
                "Ostra kritika javnih osebnosti s humorjem in ostro satiro. Najboljši komediji Slovenije se soočita s površnimi in pogosto absurdnimi mnenji raznih javnih figur.", "20:30", FALLBACK_IMAGE));
        events.add(new Event("Tadej Toš v živo", "Sobota, 28. oktober", "20:00", "Kulturni dom Celje",
                "Kulturni dom Celje, Celje", "Stand-up Komedija",
                "Tadej Toš prinaša svoj najnovejši stand-up program z veliko smeha in zabave. Njegov ciničen in filozofski tone vas bo nasmejal do solz.", "19:30", getComedianImageUrl("Tadej Toš")));
        events.add(new Event("Uroš Kuzman: Šolski spomini", "Petek, 24. oktober", "19:30", "Prešeren Theater",
                "Prešeren Theater, Ljubljana", "Stand-up Komedija",
                "Uroš Kuzman, doktor matematike in komik, prinaša inteligenten humor o šolskih skupinah in družini. Kombinacija znanstvene natančnosti in tople pripovednosti.", "19:00", getComedianImageUrl("Uroš Kuzman")));
        events.add(new Event("Gašper Bergant: Fejmiči", "Nedelja, 25. oktober", "20:00", "Tivoli Hall",
                "Tivoli Hall, Ljubljana", "Stand-up Komedija",
                "Gašper Bergant, eden najbolj opaznih mlajših komikov, s svojim surovim humorjem in dialektom prineša nepozabno večerjo. Njegove predstave so energične in dirljive.", "19:30", getComedianImageUrl("Gašper Bergant")));
        return events;
    }
}