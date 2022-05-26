package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.example.myapplication.Bookseats;
import com.example.myapplication.R;
import com.example.myapplication.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class SelectedGenreAdapter extends RecyclerView.Adapter<SelectedGenreAdapter.UI>
        implements Filterable {
    private Context context;
    private List<Genre> genreList;
    private List<Genre> genreListFiltered;

    public SelectedGenreAdapter(Context context, List<Genre> genreList) {
        this.context = context;
        this.genreList = genreList;
        this.genreListFiltered = genreList;
    }

    @NonNull
    @Override
    public UI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.selected_genre_ui, null);
        return new UI(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UI holder, int position) {
        Genre genre = genreList.get(position);
        holder.name.setText(genre.getName());
        holder.rating.setText(genre.getRating());
//        Glide.with(context)
//                .load(genre.getAvatarUrl())
//                .centerCrop()
//                .placeholder(R.drawable.action)
//                .into(holder.avatar);

        holder.btnBook.setOnClickListener(v -> {
            context.startActivity(new Intent(context, Bookseats.class)
                    .putExtra("name", genre.getName()));
        });
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String text = charSequence.toString();
                if (text.isEmpty()) {
                    genreListFiltered = genreList;
                } else {
                    List<Genre> filteredList = new ArrayList<>();
                    for (Genre genre : genreList) {
                        if (genre.getName().toLowerCase().contains(text.toLowerCase())) {
                            filteredList.add(genre);
                        }
                    }
                    genreListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = genreListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                genreListFiltered = (ArrayList<Genre>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class UI extends RecyclerView.ViewHolder {
        TextView name;
        TextView rating;
        Button btnBook;
        ImageView avatar;

        public UI(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            rating = v.findViewById(R.id.rating);
            avatar = v.findViewById(R.id.imageAvatar);
            btnBook = v.findViewById(R.id.btnBook);
        }
    }
}
