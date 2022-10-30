package com.cineplex.;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.project.Movie;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.UUID;

public class SendMovie extends AppCompatActivity {
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
        setContentView(R.layout.activity_send_movie);
        fullName = (EditText) findViewById(R.id.full_name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        ratings = findViewById(R.id.stars_bar);
        description = (EditText) findViewById(R.id.description);

    }

    public void createMovie(View view) {
        if (TextUtils.isEmpty(fullName.getText().toString())) {
            Toast.makeText(SendMovie.this, "Enter Details", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            Toast.makeText(SendMovie.this, "Enter Details", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone.getText().toString())) {
            Toast.makeText(SendMovie.this, "Enter Details", Toast.LENGTH_SHORT).show();
        } else {

            Movie movie = new Movie();
            Date date = new Date();

            movie.setId(UUID.randomUUID().toString());
            movie.setUserId(mAuth.getCurrentUser().getUid());
            movie.setFullName(fullName.getText().toString());
            movie.setEmail(email.getText().toString());
            movie.setPhone(phone.getText().toString());
            movie.setRatings(ratings.getRating());
            movie.setSendTime(date);
            movie.setDescription(description.getText().toString());
            DocumentReference documentReference = db.collection("movies").document(movie.getId());
            documentReference.set(movie)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SendMovie.this, "Movie Saved Successfully!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SendMovie.this, AllMovies.class));
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SendMovie.this, "Movie Failed!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }
}