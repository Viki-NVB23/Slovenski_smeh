package si.uni_lj.fe.tnuv.slovenskismeh;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ComedianAdapter extends RecyclerView.Adapter<ComedianAdapter.ComedianViewHolder> {

    private List<Comedian> comedians;
    private Context context;

    public ComedianAdapter(List<Comedian> comedians, Context context) {
        this.comedians = comedians;
        this.context = context;
    }

    @NonNull
    @Override
    public ComedianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comedian_item, parent, false);
        return new ComedianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComedianViewHolder holder, int position) {
        Comedian comedian = comedians.get(position);
        holder.nameTextView.setText(comedian.getFullName());
        holder.quoteTextView.setText(comedian.getQuote());

        Glide.with(context)
                .load(comedian.getImageUrl())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ComedianProfileActivity.class);
            intent.putExtra("comedian_name", comedian.getFullName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return comedians.size();
    }

    public static class ComedianViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView quoteTextView;

        public ComedianViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_comedian);
            nameTextView = itemView.findViewById(R.id.txt_name);
            quoteTextView = itemView.findViewById(R.id.txt_quote);
        }
    }
}
