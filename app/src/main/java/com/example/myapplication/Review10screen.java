package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Review10screen  extends AppCompatActivity implements View.OnClickListener{
    public Button button;
    public TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review10screen);
        button = (Button) findViewById(R.id.moviebookbtn);
        button.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.moviereview);
        textView.setText("Movie Name: Acharya\n" +
                "Directed by: Koratala Siva\n" +
                "Starring: Chiranjeevi, Ram Charan, Pooja Hegde, Kajal Aggarwal, Sonu Sood, Jisshu Sengupta, Vennela Kishore, Saurav Lokesh, Kishore, Posani Krishna Murali, Tanikella Bharani, Ajay, Sangeetha Krish, Nassar, Banerjee, Regina Cassandra\n" +
                "Genre: Drama, Action\n" +
                "Release Date: 29 April, 2022\n" +
                "Language: Telugu\n" +
                "Running Time: – Minutes\n" +
                "Rating: –  \n" +
                "Budget: ₹140 Crore");

        ImageView imgView=(ImageView) findViewById(R.id.movieimg);
        imgView.setImageResource(R.drawable.movie10);

        textView = (TextView) findViewById(R.id.movieprice);
        textView.setText("15$");
        textView = (TextView) findViewById(R.id.moviename);
        textView.setText("Kung Fu");
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.moviebookbtn:
                startActivity(new Intent(Review10screen.this, TimingsScreen.class));
                break;
        }

    }
}