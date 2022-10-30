package com.cineplex;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import java.util.UUID;

public class SendBooking extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText fullName;
    EditText email;
    EditText phone;
    RatingBar ratings;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_booking);
        fullName = (EditText) findViewById(R.id.full_name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        ratings = findViewById(R.id.stars_bar);
        description = (EditText) findViewById(R.id.description);

    }

    public void createFeedback(View view) {
        if (TextUtils.isEmpty(fullName.getText().toString())) {
            Toast.makeText(SendBooking.this, "Enter the Full name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            Toast.makeText(SendBooking.this, "Enter the email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone.getText().toString())) {
            Toast.makeText(SendBooking.this, "Enter the phone number", Toast.LENGTH_SHORT).show();
        } else {

            Booking feedback = new Booking();
            Date date = new Date();

            feedback.setId(UUID.randomUUID().toString());
            feedback.setUserId(mAuth.getCurrentUser().getUid());
            feedback.setFullName(fullName.getText().toString());
            feedback.setEmail(email.getText().toString());
            feedback.setPhone(phone.getText().toString());
            feedback.setRatings(ratings.getRating());
            feedback.setSendTime(date);
            feedback.setDescription(description.getText().toString());
            DocumentReference documentReference = db.collection("bookings").document(feedback.getId());
            documentReference.set(feedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SendBooking.this, "Added New Booking Successfully!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SendBooking.this, AllBooking.class));
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SendBooking.this, "Booking Failed!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }
}