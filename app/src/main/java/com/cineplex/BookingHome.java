package com.cineplex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class BookingHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
    }


    public void goToMyRatings(View view) {
        startActivity(new Intent(BookingHome.this, AllBooking.class));
    }

    public void goToAddFeedback(View view) {
        startActivity(new Intent(BookingHome.this, BookingRating.class));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), Blog.class);
        startActivity(i);
    }
}