package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.Movies;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    // instance variables
    private Context context;
    ArrayList<Movies> movies;

    DatabaseReference databaseReference;

    public MovieAdapter(Context context, ArrayList<Movies> movies) {
        this.context = context;
        this.movies = movies;
    }


    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_movie_ui_adapter, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Movies");

        Movies movie =movies.get(position);
        holder.tv_movieName.setText(movie.getMovieName());
        holder.tv_movieGenre.setText(movie.getMovieGenre());
        holder.tv_movieRating.setText(movie.getMoviePrice());
        Glide.with(context).load(movie.getImageUrl()).into(holder.movieImage);

        holder.btn_deleteMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(movie.getMovieName()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                        holder.notify();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
    // this displays movie info in the movie card
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_movieName,tv_movieRating,tv_movieGenre;
        ImageView movieImage;
        Button btn_deleteMovie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_movieName = itemView.findViewById(R.id.tv_movieName);
            tv_movieGenre = itemView.findViewById(R.id.tv_genreMovie);
            tv_movieRating = itemView.findViewById(R.id.tv_movieprice);
            movieImage = itemView.findViewById(R.id.movieImage);
            btn_deleteMovie = itemView.findViewById(R.id.btn_deleteMovie);
        }
    }
}
