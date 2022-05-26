package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.SelectionPage;
import com.example.myapplication.model.Genre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.UI> {
    // instance variables
    private Context context;
    private List<Genre> genreList;

    public GenreAdapter(Context context, List<Genre> genreList) {
        this.context = context;
        this.genreList = genreList;
    }

    @NonNull
    @Override
    public UI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.genre_ui, null);
        return new UI(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UI holder, int position) {
        Genre genre = genreList.get(position);
        holder.name.setText(genre.getName());
        holder.avatar.setImageResource(genre.getDrawable());
        holder.card.setOnClickListener(v -> {
            context.startActivity(new Intent(context, SelectionPage.class)
                    .putExtra("type", genre.getName()));
            Toast.makeText(context, genre.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class UI extends RecyclerView.ViewHolder {
        CardView card;
        ImageView avatar;
        TextView name;

        public UI(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card);
            avatar = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.name);
        }
    }
}
