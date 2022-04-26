package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TimingsScreen extends AppCompatActivity {
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timings_screen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = (Button) findViewById(R.id.timings11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimingsScreen.this,Bookseats.class);
                startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.timings03);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimingsScreen.this,Bookseats.class);
                startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.timings06);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimingsScreen.this,Bookseats.class);
                startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.timings09);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimingsScreen.this,Bookseats.class);
                startActivity(intent);
            }
        });
    }
}