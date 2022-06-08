package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.model.Movies;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private Button btnAddGenre;
    private Button btnAddTheatre;
    private Button viewTheatre;
    Button btn_viewMovies;
    private List<Movies> list;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        database = FirebaseDatabase.getInstance();
        dialog = new ProgressDialog(this);
        list = new ArrayList<>();

        btnAddGenre = findViewById(R.id.addMovie);
        btnAddTheatre = findViewById(R.id.addTheatre);
        viewTheatre = findViewById(R.id.viewTheatre);
        btn_viewMovies = findViewById(R.id.viewMovies);

        btnAddGenre.setOnClickListener(v -> {
            startActivity(new Intent(AdminActivity.this, AddMovieActivity.class));
        });

        btnAddTheatre.setOnClickListener(v -> {
            startActivity(new Intent(this, AddTheatreActivity.class));
        });

        viewTheatre.setOnClickListener(v -> {
            startActivity(new Intent(this, adminTheatreActivity.class));
        });

        btn_viewMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AdminMovieActivity.class));
            }
        });


    }

    private void populateGenres() {

    }

//    private void populateGenres(){
//        dialog.setMessage("Please wait");
//        dialog.setCancelable(false);
//        dialog.show();
//        LinearLayoutManager layout = new LinearLayoutManager(this);
//        genreList.setLayoutManager(layout);
//
//        // retrieve from database
//        DatabaseReference genre_ref = database.getReference("genre_all");
//        genre_ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                dialog.dismiss();
//                for (DataSnapshot s : snapshot.getChildren()){
//                    Genre genre = s.getValue(Genre.class);
//                    list.add(genre);
//                }
//
//                SelectedGenreAdapter adapter = new SelectedGenreAdapter(AdminActivity.this, list);
//                genreList.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                dialog.dismiss();
//                Toast.makeText(AdminActivity.this, "Error fetching genres: \n" + error.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }