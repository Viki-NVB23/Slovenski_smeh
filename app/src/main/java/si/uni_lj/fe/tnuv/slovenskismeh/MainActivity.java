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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView comediansRecyclerView;
    private EditText editSearch;
    private TextView txtNoResults;
    private LinearLayout navHome;
    private LinearLayout navMap;
    private LinearLayout navEvents;
    private ImageView iconHome;
    private ImageView iconMap;
    private ImageView iconEvents;
    private TextView textHome;
    private TextView textMap;
    private TextView textEvents;

    private List<Comedian> allComedians;
    private List<Comedian> filteredComedians;
    private ComedianAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comediansRecyclerView = findViewById(R.id.comedians_recycler_view);
        editSearch = findViewById(R.id.edit_search);
        txtNoResults = findViewById(R.id.txt_no_results);
        navHome = findViewById(R.id.nav_home);
        navMap = findViewById(R.id.nav_map);
        navEvents = findViewById(R.id.nav_events);
        iconHome = findViewById(R.id.icon_home);
        iconMap = findViewById(R.id.icon_map);
        iconEvents = findViewById(R.id.icon_events);
        textHome = findViewById(R.id.text_home);
        textMap = findViewById(R.id.text_map);
        textEvents = findViewById(R.id.text_events);

        // Load comedians from JSON
        allComedians = loadComediansFromJson();
        filteredComedians = new ArrayList<>(allComedians);

        // Set home navigation as active (orange)
        iconHome.setColorFilter(getResources().getColor(android.R.color.holo_orange_dark), android.graphics.PorterDuff.Mode.SRC_IN);
        textHome.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));

        // Set up RecyclerView
        comediansRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ComedianAdapter(filteredComedians, this);
        comediansRecyclerView.setAdapter(adapter);

        // Set up search
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterComedians(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
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

    private void filterComedians(String query) {
        filteredComedians.clear();
        if (query.isEmpty()) {
            filteredComedians.addAll(allComedians);
            txtNoResults.setVisibility(View.GONE);
            comediansRecyclerView.setVisibility(View.VISIBLE);
        } else {
            String lowerQuery = query.toLowerCase();
            String[] words = lowerQuery.split("\\s+");
            for (Comedian c : allComedians) {
                String fullName = c.getFullName().toLowerCase();
                boolean match = true;
                for (String word : words) {
                    if (!fullName.contains(word)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    filteredComedians.add(c);
                }
            }
            if (filteredComedians.isEmpty()) {
                txtNoResults.setVisibility(View.VISIBLE);
                comediansRecyclerView.setVisibility(View.GONE);
            } else {
                txtNoResults.setVisibility(View.GONE);
                comediansRecyclerView.setVisibility(View.VISIBLE);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private List<Comedian> loadComediansFromJson() {
        String json = null;
        try {
            InputStream is = getAssets().open("komiki.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Comedian>>() {}.getType();
        return gson.fromJson(json, listType);
    }
}