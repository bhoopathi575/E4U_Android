package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectionPage extends AppCompatActivity {
    public ImageButton imageButton;
    public ImageButton imageButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageButton = (ImageButton) findViewById(R.id.movie1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionPage.this, ReviewsScreen.class);
                startActivity(intent);

            }

        });
    }
}