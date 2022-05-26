package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.adapters.TheatreAdapter;
import com.example.myapplication.model.Theatre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class TheatreActivity extends AppCompatActivity {
    private List<Theatre> theatreList;
    private ProgressDialog dialog;
    private RecyclerView list;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);

        dialog = new ProgressDialog(this);
        database = FirebaseDatabase.getInstance();
        theatreList = new ArrayList<>();

        list = findViewById(R.id.theatreList);

        populateTheatres();
    }

    private void populateTheatres() {
        dialog.setMessage("Please wait");
        dialog.setCancelable(false);
        dialog.show();
        LinearLayoutManager layout = new LinearLayoutManager(this);
        list.setLayoutManager(layout);

        // retrieve from database
        DatabaseReference theatres_ref = database.getReference("theatres");
        theatres_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dialog.dismiss();
                for (DataSnapshot s : snapshot.getChildren()) {
                    Theatre genre = s.getValue(Theatre.class);
                    theatreList.add(genre);
                }

                TheatreAdapter adapter = new TheatreAdapter(TheatreActivity.this, theatreList);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
                Toast.makeText(TheatreActivity.this, "Error fetching theatres: \n" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}