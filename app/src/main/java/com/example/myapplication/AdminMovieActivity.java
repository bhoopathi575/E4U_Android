// This displays all the movies added, such that admin can see
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapters.MovieAdapter;
import com.example.myapplication.model.Movies;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminMovieActivity extends AppCompatActivity {

    ArrayList<Movies> movies;

    private RecyclerView movieList;
    DatabaseReference databaseReference;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_movie_list);

        movieList = findViewById(R.id.movieList);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        movies = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, movies);
        movieList.setAdapter(movieAdapter);
        movieList.setLayoutManager(new LinearLayoutManager(this));
        movieList.setHasFixedSize(true);

        PopulateMovies();

    }

    private void PopulateMovies() {
        databaseReference.child("Movies").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Movies movie = dataSnapshot.getValue(Movies.class);
                    movies.add(movie);
                    movieAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}