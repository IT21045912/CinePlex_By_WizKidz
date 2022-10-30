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

public class movieView extends AppCompatActivity {
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
        setContentView(R.layout.activity_movie_view);
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
                Intent i = new Intent(getApplicationContext(), AllMovies.class);
                startActivity(i);
            }
        });

    }


    public void movieDelete(View view) {
        DocumentReference documentReference = db.collection("movies").document(getIntent().getStringExtra("id"));
        documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(movieView.this, "Movie Delete Successfully.",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), com.example.project.MovieHome.class));
            }
        });
    }

    public void movieUpdate(View view) {

        //validation
        if (TextUtils.isEmpty(fullName.getText().toString())) {
            Toast.makeText(movieView.this, "Enter Details", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            Toast.makeText(movieView.this, "Enter Details", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone.getText().toString())) {
            Toast.makeText(movieView.this, "Enter Details", Toast.LENGTH_SHORT).show();
        } else {

            Movies movie = new Movies();
            Date date = new Date();
            movie.setId(getIntent().getStringExtra("id"));
            movie.setUserId(mAuth.getCurrentUser().getUid());
            movie.setFullName(fullName.getText().toString());
            movie.setEmail(email.getText().toString());
            movie.setPhone(phone.getText().toString());
            movie.setRatings( ratings.getRating());
            movie.setSendTime(date);
            movie.setDescription(description.getText().toString());

            Map<String, Object> data = new HashMap<>();
            data.put("fullName", movie.getFullName());
            data.put("email", movie.getEmail());
            data.put("phone", movie.getPhone());
            data.put("ratings", movie.getRatings());
            data.put("sendTime", movie.getSendTime());
            data.put("description", movie.getDescription());
            DocumentReference documentReference = db.collection("movies").document(movie.getId());
            documentReference.update(data)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(movieView.this, "Movie data Update Successfully!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(movieView.this, com.example.project.MovieHome.class));
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(movieView.this, "Oops there are some issue occurred !",
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