package com.cineplex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class BookingRating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_rating);
    }

    public void goToSendFeedback(View view) {
        startActivity(new Intent(BookingRating.this, SendBooking.class));
    }
}