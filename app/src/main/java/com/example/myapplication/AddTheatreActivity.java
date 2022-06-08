package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class AddTheatreActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private FirebaseDatabase database;
    DatabaseReference databaseReference;
    private TextInputLayout nameTF;
    private TextInputLayout ratingTF;
    private TextInputLayout locationTF;
    private Spinner spinner;
    private Button btnSave;
//    String name,rating,location,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_add_theatre);

        dialog = new ProgressDialog(this);
        database = FirebaseDatabase.getInstance();
        nameTF = (TextInputLayout) findViewById(R.id.nameTF);
        ratingTF = (TextInputLayout) findViewById(R.id.priceTF);
        locationTF = (TextInputLayout) findViewById(R.id.locationTF);
//        spinner = findViewById(R.id.status);
        btnSave = findViewById(R.id.btnSave);
        databaseReference = FirebaseDatabase.getInstance().getReference();

//        status = spinner.getSelectedItem().toString();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTheatre();
            }
        });
    }

    private void AddTheatre() {



        dialog.setMessage("Please wait");
        dialog.show();

//            Theatre theatre = new Theatre(status.equals("Open"), location, name, rating);

//            DatabaseReference theatre_ref = database.getReference("theatres");
//            theatre_ref.push().setValue(theatre).addOnCompleteListener(this, task -> {
//                dialog.dismiss();;
//                if (task.isSuccessful()){
//                    Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(AddTheatreActivity.this, AdminActivity.class));
//                    finish();
//                }else{
//                    Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show();
//                }
//            });
        String name = nameTF.getEditText().getText().toString();
       String rating = ratingTF.getEditText().getText().toString();
        String location = locationTF.getEditText().getText().toString();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Object> userdata = new HashMap<>();
                userdata.put("TheatreName", name);
                userdata.put("TheatreRating", rating);
                userdata.put("TheatreLocation", location);

                databaseReference.child("Theatres").child(name).updateChildren(userdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AddTheatreActivity.this, "Added", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}