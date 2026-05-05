package si.uni_lj.fe.tnuv.slovenskismeh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import si.uni_lj.fe.tnuv.slovenskismeh.util.YouTubeUtil;

public class ComedianProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comedian_profile);

        // Back button
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        ImageView imgHeader = findViewById(R.id.img_header);
        TextView txtComedianName = findViewById(R.id.txt_comedian_name);
        TextView txtBio = findViewById(R.id.txt_bio);
        TextView txtStyle = findViewById(R.id.txt_style);
        LinearLayout layoutMediaLinks = findViewById(R.id.layout_media_links);
        RecyclerView videosRecyclerView = findViewById(R.id.recycler_videos);

        String comedianName = getIntent().getStringExtra("comedian_name");

        if (comedianName != null) {
            List<Comedian> comedians = loadComediansFromJson();
            if (comedians != null) {
                Comedian selectedComedian = findComedianByName(comedians, comedianName);
                if (selectedComedian != null) {
                    txtComedianName.setText(selectedComedian.getFullName());

                    // Load image with placeholder
                    String imageUrl = selectedComedian.getImageUrl();
                    if (imageUrl == null || imageUrl.trim().isEmpty() || imageUrl.trim().equals(" ")) {
                        Glide.with(this)
                                .load(R.drawable.ic_icon)
                                .into(imgHeader);
                    } else {
                        Glide.with(this)
                                .load(imageUrl)
                                .placeholder(R.drawable.ic_icon)
                                .error(R.drawable.ic_icon)
                                .into(imgHeader);
                    }

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

                    // Display videos in RecyclerView
                    if (selectedComedian.getYoutubeLinks() != null && !selectedComedian.getYoutubeLinks().isEmpty()) {
                        List<Video> videos = YouTubeUtil.parseYouTubeLinks(selectedComedian.getYoutubeLinks());
                        if (!videos.isEmpty()) {
                            videosRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                            VideoAdapter videoAdapter = new VideoAdapter(videos, this);
                            videosRecyclerView.setAdapter(videoAdapter);
                            videosRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            videosRecyclerView.setVisibility(View.GONE);
                        }
                    } else {
                        videosRecyclerView.setVisibility(View.GONE);
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
