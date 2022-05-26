package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ReviewsScreen extends AppCompatActivity implements View.OnClickListener{
    public Button button;
    public TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        button = (Button) findViewById(R.id.movie1bookbtn);
        button.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.movie1review);
        textView.setText("Movie Name: Acharya\n" +
                "Directed by: Koratala Siva\n" +
                "Starring: Chiranjeevi, Ram Charan, Pooja Hegde, Kajal Aggarwal, Sonu Sood, Jisshu Sengupta, Vennela Kishore, Saurav Lokesh, Kishore, Posani Krishna Murali, Tanikella Bharani, Ajay, Sangeetha Krish, Nassar, Banerjee, Regina Cassandra\n" +
                "Genre: Drama, Action\n" +
                "Release Date: 29 April, 2022\n" +
                "Language: Telugu\n" +
                "Running Time: – Minutes\n" +
                "Rating: –  \n" +
                "Budget: ₹140 Crore");

        ImageView imgView=(ImageView) findViewById(R.id.movie1img);
        imgView.setImageResource(R.drawable.movie1);

        textView = (TextView) findViewById(R.id.movie1price);
        textView.setText("30$");
        textView = (TextView) findViewById(R.id.movie1name);
        textView.setText("Acharaya");





    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.movie1bookbtn:
                startActivity(new Intent(ReviewsScreen.this, TimingsScreen.class));
                break;
        }
    }
}