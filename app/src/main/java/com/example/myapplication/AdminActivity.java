package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.adapters.SelectedGenreAdapter;
import com.example.myapplication.model.Genre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private Button btnAddGenre;
    private Button btnAddTheatre;
    private Button viewTheatre;
    private RecyclerView genreList;
    private List<Genre> list;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        database = FirebaseDatabase.getInstance();
        dialog = new ProgressDialog(this);
        list = new ArrayList<>();

        btnAddGenre = findViewById(R.id.addGenre);
        btnAddTheatre = findViewById(R.id.addTheatre);
        genreList = findViewById(R.id.genreList);
        viewTheatre = findViewById(R.id.viewTheatre);

        btnAddGenre.setOnClickListener(v -> {
            startActivity(new Intent(AdminActivity.this, AddGenreActivity.class));
        });

        btnAddTheatre.setOnClickListener(v -> {
            startActivity(new Intent(this, AddTheatreActivity.class));
        });

        viewTheatre.setOnClickListener(v -> {
            startActivity(new Intent(this, TheatreActivity.class));
        });

        populateGenres();
    }

    private void populateGenres(){
        dialog.setMessage("Please wait");
        dialog.setCancelable(false);
        dialog.show();
        LinearLayoutManager layout = new LinearLayoutManager(this);
        genreList.setLayoutManager(layout);

        // retrieve from database
        DatabaseReference genre_ref = database.getReference("genre_all");
        genre_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dialog.dismiss();
                for (DataSnapshot s : snapshot.getChildren()){
                    Genre genre = s.getValue(Genre.class);
                    list.add(genre);
                }

                SelectedGenreAdapter adapter = new SelectedGenreAdapter(AdminActivity.this, list);
                genreList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
                Toast.makeText(AdminActivity.this, "Error fetching genres: \n" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}