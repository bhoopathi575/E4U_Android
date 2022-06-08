package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SelectionPage extends AppCompatActivity implements View.OnClickListener {
    public ImageButton imageButton;
    public TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageButton = (ImageButton) findViewById(R.id.movie1);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie2);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie3);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie4);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie5);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie6);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie7);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie8);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie9);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie10);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie11);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie12);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie13);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie14);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie15);
        imageButton.setOnClickListener(this);
        imageButton = (ImageButton) findViewById(R.id.movie16);
        imageButton.setOnClickListener(this);



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.userprofile){
            startActivity(new Intent(this, UserProfile.class));
            return true;
        }
        if (id == R.id.theateractivity){
            startActivity(new Intent(this, UserTheaterActivity.class));
            return true;
        }
        if (id == R.id.logout){

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null){
                FirebaseAuth.getInstance().signOut();
            }
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.movie1:
                startActivity(new Intent(SelectionPage.this, ReviewsScreen.class));
                break;
            case R.id.movie2:
                startActivity(new Intent(SelectionPage.this, Review2screen.class));
                break;
            case R.id.movie3:
                startActivity(new Intent(SelectionPage.this, Review3Screen.class));
                break;
            case R.id.movie4:
                startActivity(new Intent(SelectionPage.this, Review4screen.class));
                break;
            case R.id.movie5:
                startActivity(new Intent(SelectionPage.this, Review5screen.class));
                break;
            case R.id.movie6:
                startActivity(new Intent(SelectionPage.this, Review6screen.class));
                break;
            case R.id.movie7:
                startActivity(new Intent(SelectionPage.this, Review7screen.class));
                break;
            case R.id.movie8:
                startActivity(new Intent(SelectionPage.this, Review8screen.class));
                break;
            case R.id.movie9:
                startActivity(new Intent(SelectionPage.this, Review9screen.class));
                break;
            case R.id.movie10:
                startActivity(new Intent(SelectionPage.this, Review10screen.class));
                break;
            case R.id.movie11:
                startActivity(new Intent(SelectionPage.this, Review11screen.class));
                break;
            case R.id.movie12:
                startActivity(new Intent(SelectionPage.this, Review12screen.class));
                break;
            case R.id.movie13:
                startActivity(new Intent(SelectionPage.this, Review13screen.class));
                break;
            case R.id.movie14:
                startActivity(new Intent(SelectionPage.this, Review14screen.class));
                break;
            case R.id.movie15:
                startActivity(new Intent(SelectionPage.this, Review15screen.class));
                break;
            case R.id.movie16:
                startActivity(new Intent(SelectionPage.this, Review16screen.class));
                break;

        }
    }
}