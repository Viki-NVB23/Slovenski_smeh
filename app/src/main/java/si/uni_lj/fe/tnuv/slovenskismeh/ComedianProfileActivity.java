package si.uni_lj.fe.tnuv.slovenskismeh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ComedianProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comedian_profile);

        ImageView imgHeader = findViewById(R.id.img_header);
        TextView txtComedianName = findViewById(R.id.txt_comedian_name);
        TextView txtBio = findViewById(R.id.txt_bio);
        TextView txtStyle = findViewById(R.id.txt_style);
        LinearLayout layoutMediaLinks = findViewById(R.id.layout_media_links);

        String comedianName = getIntent().getStringExtra("comedian_name");

        if (comedianName != null) {
            List<Comedian> comedians = loadComediansFromJson();
            if (comedians != null) {
                Comedian selectedComedian = findComedianByName(comedians, comedianName);
                if (selectedComedian != null) {
                    txtComedianName.setText(selectedComedian.getFullName());

                    Glide.with(this)
                            .load(selectedComedian.getImageUrl())
                            .placeholder(R.drawable.ic_icon)
                            .error(R.drawable.ic_icon)
                            .into(imgHeader);

                    // Set biography (previously used quote)
                    if (selectedComedian.getBiography() != null && !selectedComedian.getBiography().isEmpty()) {
                        txtBio.setText(selectedComedian.getBiography());
                    } else {
                        txtBio.setText(selectedComedian.getQuote());
                    }

                    // Set style
                    if (selectedComedian.getStyle() != null) {
                        txtStyle.setText(selectedComedian.getStyle());
                    }

                    // Set media links
                    if (selectedComedian.getYoutubeLinks() != null) {
                        layoutMediaLinks.removeAllViews();
                        for (String link : selectedComedian.getYoutubeLinks()) {
                            TextView linkView = new TextView(this);
                            linkView.setText(link);
                            linkView.setTextColor(getResources().getColor(android.R.color.holo_blue_light));
                            linkView.setPadding(0, 8, 0, 8);
                            linkView.setOnClickListener(v -> {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                                startActivity(intent);
                            });
                            layoutMediaLinks.addView(linkView);
                        }
                    }
                }
            }
        }
    }

    private Comedian findComedianByName(List<Comedian> comedians, String fullName) {
        for (Comedian c : comedians) {
            if (c.getFullName().equals(fullName)) {
                return c;
            }
        }
        return null;
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
