package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapters.UserMovieAdapter;
import com.example.myapplication.model.Movies;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserMovieActivity extends AppCompatActivity {

    ArrayList<Movies> movies;

    private RecyclerView userMovieList;
    DatabaseReference databaseReference;
    UserMovieAdapter userMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_movie);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        movies = new ArrayList<>();
        userMovieAdapter = new UserMovieAdapter(this, movies);
        userMovieList = findViewById(R.id.userMovieList);
        userMovieList.setAdapter(userMovieAdapter);
        userMovieList.setLayoutManager(new LinearLayoutManager(this));
        userMovieList.setHasFixedSize(true);

        PopulateMovies();

    }

    private void PopulateMovies() {
        databaseReference.child("Movies").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Movies movie = dataSnapshot.getValue(Movies.class);
                    movies.add(movie);
                    userMovieAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}