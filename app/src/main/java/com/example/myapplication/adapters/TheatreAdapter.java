package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Theatre;

import java.util.List;

public class TheatreAdapter extends RecyclerView.Adapter<TheatreAdapter.UI> {
    // instance variables
    private Context context;
    private List<Theatre> theatreList;

    public TheatreAdapter(Context context, List<Theatre> theatreList) {
        this.context = context;
        this.theatreList = theatreList;
    }

    @NonNull
    @Override
    public UI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.theater_ui, null);
        return new UI(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UI holder, int position) {
        Theatre theatre = theatreList.get(position);
        holder.name.setText(theatre.getName());
        holder.rating.setText(holder.rating.getText() + theatre.getRating());
        holder.isOpen.setText(theatre.isOpen() ? "Open" : "Closed");
    }

    @Override
    public int getItemCount() {
        return theatreList.size();
    }

    public class UI extends RecyclerView.ViewHolder {
        TextView name;
        TextView location;
        TextView rating;
        TextView isOpen;

        public UI(View view){
            super(view);
            name = view.findViewById(R.id.name);
            location = view.findViewById(R.id.location);
            rating = view.findViewById(R.id.rating);
            isOpen = view.findViewById(R.id.isOpen);
        }
    }
}
