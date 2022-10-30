package com.cineplex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class movieRating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_rating);
    }

    public void goToSendMovie(View view) {
        startActivity(new Intent(movieRating.this, SendMovie.class));
    }
}