package si.uni_lj.fe.tnuv.slovenskismeh;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ComedianProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comedian_profile);

        TextView txtComedianName = findViewById(R.id.txt_comedian_name);
        TextView txtBio = findViewById(R.id.txt_bio);

        String comedianName = getIntent().getStringExtra("comedian_name");

        if (comedianName != null) {
            txtComedianName.setText(comedianName);

            if (comedianName.equals("Vid Valič")) {
                txtBio.setText("Vid Valič je eden najbolj znanih slovenskih stand-up komikov...");
            } else if (comedianName.equals("Martina Ipša")) {
                txtBio.setText("Martina Ipša je priznana komičarka z ostrim humorjem...");
            } else if (comedianName.equals("Tilen Artač")) {
                txtBio.setText("Tilen Artač je znan po imitacijah in glasbenem humorju...");
            } else if (comedianName.equals("Lucija Ćirović")) {
                txtBio.setText("Lucija Ćirović je znana po lutkah in humorju...");
            }
        }
    }
}