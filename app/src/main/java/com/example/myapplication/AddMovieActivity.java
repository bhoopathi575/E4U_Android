package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
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
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;



public class AddMovieActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    DatabaseReference databaseReference;
    private TextInputLayout nameTF;
    private TextInputLayout priceTF;
    TextInputLayout imageTF;
    AutoCompleteTextView autoCompleteTextView;
    TextInputLayout genreTF;

    private Spinner spinner;
    private ImageView imageView;
    private Button btnUploadImg;
    private Button btnChooseImg;
    private Button btn_addMovie;

    private Uri fileUri;
    private String uploadedImageUrl;
    private static final int PICK_IMAGE = 334;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        dialog = new ProgressDialog(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        nameTF = (TextInputLayout) findViewById(R.id.nameTF);
        priceTF = (TextInputLayout) findViewById(R.id.priceTF);
        imageTF = (TextInputLayout) findViewById(R.id.ImageTF);
        genreTF = (TextInputLayout) findViewById(R.id.genreTF);
        autoCompleteTextView = findViewById(R.id.autoCompleteText);


        btn_addMovie = findViewById(R.id.btn_addMovie);

        String[] movies = getResources().getStringArray(R.array.genres);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddMovieActivity.this,
                R.layout.dropdown_layout,
                movies
                );
        autoCompleteTextView.setAdapter(arrayAdapter);

        btn_addMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMovie();
            }
        });


//        btnUploadImg.setOnClickListener(v -> {
//            dialog.setMessage("Please wait...");
//            dialog.show();
//            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
//            StorageReference uploadImageRef = storageRef.child("images/" + fileUri.getLastPathSegment());
//
//            UploadTask uploadTask = uploadImageRef.putFile(fileUri);
//
//            uploadTask.addOnSuccessListener(taskSnapshot -> {
//                dialog.dismiss();
//                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(task -> {
//                    uploadedImageUrl = task.getResult().toString();
//                    btnSave.setEnabled(true);
//                });
//            }).addOnFailureListener(e -> {
//                dialog.dismiss();
//                Toast.makeText(AddGenreActivity.this, "Failed to Upload Image", Toast.LENGTH_SHORT).show();
//            });
//        });

//        btnSave.setOnClickListener(v -> {
//
//            String name = nameTF.getText().toString().trim();
//            String rating = ratingTF.getText().toString().trim();
//            String type = spinner.getSelectedItem().toString();
//            if (name.isEmpty() || rating.isEmpty() || type.contains("-")) {
//                Toast.makeText(this, "Please fill all the fields with valid information", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (uploadedImageUrl == null || uploadedImageUrl == "") {
//                Toast.makeText(this, "Please choose an image", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            Genre genre = new Genre(uploadedImageUrl, name, rating, type);
//            dialog.setMessage("Please wait");
//            dialog.show();
//
//            DatabaseReference genre_ref = database.getReference("genre_all");
//            genre_ref.push().setValue(genre).addOnCompleteListener(this, task -> {
//                dialog.dismiss();
//                if (task.isSuccessful()) {
//                    Toast.makeText(this, "Data was saved successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(AddMovieActivity.this, AdminActivity.class));
//                    finish();
//                } else {
//                    Toast.makeText(this, "Saving data failed", Toast.LENGTH_SHORT).show();
//                }
//            });
//        });

    }

    private void AddMovie() {
        String name = nameTF.getEditText().getText().toString();
        String price = priceTF.getEditText().getText().toString();
        String genre = genreTF.getEditText().getText().toString();
        String imageUrl = imageTF.getEditText().getText().toString();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Object> userdata = new HashMap<>();

                userdata.put("MovieName", name);
                userdata.put("MoviePrice", price);
                userdata.put("MovieGenre", genre);
                userdata.put("ImageUrl", imageUrl);

                databaseReference.child("Movies").child(name).updateChildren(userdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AddMovieActivity.this, "Movie Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddMovieActivity.this, AdminActivity.class));
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
//            if (data != null) {
//                fileUri = data.getData();
//                imageView.setImageURI(fileUri);
//            }
//        }
//    }
}