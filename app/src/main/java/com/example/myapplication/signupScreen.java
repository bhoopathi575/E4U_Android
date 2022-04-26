package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class signupScreen extends AppCompatActivity {
    public Button button;
    public TextView button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        button = (Button) findViewById(R.id.signupbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signupScreen.this, MainActivity.class);
                startActivity(intent);

            }

        });

        button1 = (TextView) this.findViewById(R.id.backtosignin);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signupScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void handleSection(View view) {
        Toast.makeText(this, "Account created please SignIn", Toast.LENGTH_SHORT).show();
    }
}