package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.model.Theatre;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddTheatreActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private FirebaseDatabase database;
    private TextInputEditText nameTF;
    private TextInputEditText ratingTF;
    private TextInputEditText locationTF;
    private Spinner spinner;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_theatre);

        dialog = new ProgressDialog(this);
        database = FirebaseDatabase.getInstance();
        nameTF = findViewById(R.id.nameTF);
        ratingTF = findViewById(R.id.ratingTF);
        locationTF = findViewById(R.id.locationTF);
        spinner = findViewById(R.id.status);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {

            String name = nameTF.getText().toString().trim();
            String rating = ratingTF.getText().toString().trim();
            String location = locationTF.getText().toString().trim();
            String status = spinner.getSelectedItem().toString();

            if (name.isEmpty() || rating.isEmpty() || location.isEmpty() || status.contains("-")){
                Toast.makeText(this, "Provide valid details", Toast.LENGTH_SHORT).show();
                return;
            }

            dialog.setMessage("Please wait");
            dialog.show();

            Theatre theatre = new Theatre(status.equals("Open"), location, name, rating);

            DatabaseReference theatre_ref = database.getReference("theatres");
            theatre_ref.push().setValue(theatre).addOnCompleteListener(this, task -> {
                dialog.dismiss();;
                if (task.isSuccessful()){
                    Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddTheatreActivity.this, AdminActivity.class));
                    finish();
                }else{
                    Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show();
                }
            });

        });
    }
}