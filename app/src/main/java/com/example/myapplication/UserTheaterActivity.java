package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapters.UserTheatreAdapter;
import com.example.myapplication.model.Theatre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserTheaterActivity extends AppCompatActivity {
    ArrayList<Theatre> theatres;

    private RecyclerView list;
    DatabaseReference databaseReference;
    UserTheatreAdapter userTheatreAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_theater);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        theatres = new ArrayList<>();
        userTheatreAdapter = new UserTheatreAdapter(this, theatres);
        list = findViewById(R.id.usertheatreList);
        list.setAdapter(userTheatreAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setHasFixedSize(true);

        populateTheatres();


    }


    private void populateTheatres() {
        // retrieve from database
        databaseReference.child("Theatres").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Theatre theatre = dataSnapshot.getValue(Theatre.class);
                    theatres.add(theatre);
                    userTheatreAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
