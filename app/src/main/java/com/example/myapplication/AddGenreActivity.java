package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.myapplication.model.Genre;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;



public class AddGenreActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private FirebaseDatabase database;
    private TextInputEditText nameTF;
    private TextInputEditText ratingTF;
    private Spinner spinner;
    private ImageView imageView;
    private Button btnUploadImg;
    private Button btnChooseImg;
    private Button btnSave;

    private Uri fileUri;
    private String uploadedImageUrl;
    private static final int PICK_IMAGE = 334;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_genre);

        dialog = new ProgressDialog(this);
        database = FirebaseDatabase.getInstance();
        nameTF = findViewById(R.id.nameTF);
        ratingTF = findViewById(R.id.ratingTF);
        spinner = findViewById(R.id.spinner);
        imageView = findViewById(R.id.imageView);

        btnSave = findViewById(R.id.btnSave);
        btnChooseImg = findViewById(R.id.chooseImage);
        btnUploadImg = findViewById(R.id.btnUploadImg);

        btnChooseImg.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
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

        btnSave.setOnClickListener(v -> {

            String name = nameTF.getText().toString().trim();
            String rating = ratingTF.getText().toString().trim();
            String type = spinner.getSelectedItem().toString();
            if (name.isEmpty() || rating.isEmpty() || type.contains("-")) {
                Toast.makeText(this, "Please fill all the fields with valid information", Toast.LENGTH_SHORT).show();
                return;
            }

            if (uploadedImageUrl == null || uploadedImageUrl == "") {
                Toast.makeText(this, "Please choose an image", Toast.LENGTH_SHORT).show();
                return;
            }

            Genre genre = new Genre(uploadedImageUrl, name, rating, type);
            dialog.setMessage("Please wait");
            dialog.show();

            DatabaseReference genre_ref = database.getReference("genre_all");
            genre_ref.push().setValue(genre).addOnCompleteListener(this, task -> {
                dialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Data was saved successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddGenreActivity.this, AdminActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Saving data failed", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                fileUri = data.getData();
                imageView.setImageURI(fileUri);
            }
        }
    }
}