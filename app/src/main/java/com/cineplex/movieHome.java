package com.cineplex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class movieHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
    }


    public void goToMyRatings(View view) {
        startActivity(new Intent(movieHome.this, AllMovies.class));
    }

    public void goToAddMovie(View view) {
        startActivity(new Intent(movieHome.this, movieRating.class));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), Blog.class);
        startActivity(i);
    }
}