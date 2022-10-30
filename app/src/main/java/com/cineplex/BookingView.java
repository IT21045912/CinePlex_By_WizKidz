package com.cineplex;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BookingView extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText fullName;
    EditText email;
    EditText phone;
    RatingBar ratings;
    EditText description;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_view);
        fullName = (EditText) findViewById(R.id.full_name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        ratings = findViewById(R.id.stars_bar);
        btnBack = findViewById(R.id.back);
        description = (EditText) findViewById(R.id.description);
        fullName.setText(getIntent().getStringExtra("fullName"));
        email.setText(getIntent().getStringExtra("email"));
        phone.setText(getIntent().getStringExtra("phone"));
        ratings.setRating(Float.parseFloat(getIntent().getStringExtra("ratings")));
        description.setText(getIntent().getStringExtra("description"));

        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), AllBooking.class);
                startActivity(i);
            }
        });

    }


    public void feedbackDelete(View view) {
        DocumentReference documentReference = db.collection("bookings").document(getIntent().getStringExtra("id"));
        documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(BookingView.this, "Delete Bookings Successfully.",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), com.example.project.FeedbackHome.class));
            }
        });
    }

    public void feedbackUpdate(View view) {

        //validation
        if (TextUtils.isEmpty(fullName.getText().toString())) {
            Toast.makeText(BookingView.this, "Enter the Full name..", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            Toast.makeText(BookingView.this, "Enter the Email..", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone.getText().toString())) {
            Toast.makeText(BookingView.this, "Enter the phone number..", Toast.LENGTH_SHORT).show();
        } else {

            com.example.project.Feedback feedback = new com.example.project.Feedback();
            Date date = new Date();
            feedback.setId(getIntent().getStringExtra("id"));
            feedback.setUserId(mAuth.getCurrentUser().getUid());
            feedback.setFullName(fullName.getText().toString());
            feedback.setEmail(email.getText().toString());
            feedback.setPhone(phone.getText().toString());
            feedback.setRatings( ratings.getRating());
            feedback.setSendTime(date);
            feedback.setDescription(description.getText().toString());

            Map<String, Object> data = new HashMap<>();
            data.put("fullName", feedback.getFullName());
            data.put("email", feedback.getEmail());
            data.put("phone", feedback.getPhone());
            data.put("ratings", feedback.getRatings());
            data.put("sendTime", feedback.getSendTime());
            data.put("description", feedback.getDescription());
            DocumentReference documentReference = db.collection("bookings").document(feedback.getId());
            documentReference.update(data)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(BookingView.this, "Bookings data Update Successfully!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(BookingView.this, com.example.project.FeedbackHome.class));
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(BookingView.this, "Oops there are some issue occured !",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), Blog.class);
        startActivity(i);
    }
}