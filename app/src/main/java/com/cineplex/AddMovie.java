package com.cineplex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMovie extends AppCompatActivity {

    EditText Name;
    EditText Year;
    EditText Desc;
    EditText Cast;
    EditText Director;
    EditText Halls;
    EditText imageLink;
    EditText Link;
    Button save;

    DatabaseReference MovieDBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        Name = findViewById(R.id.Name);
        Year = findViewById(R.id.Year);
        Desc = findViewById(R.id.Desc);
        Cast = findViewById(R.id.Cast);
        Director = findViewById(R.id.Director);
        Halls = findViewById(R.id.Halls);
        imageLink = findViewById(R.id.Image);
        Link = findViewById(R.id.Link);
        save = findViewById(R.id.ADDMOVIE);

        MovieDBRef = FirebaseDatabase.getInstance().getReference().child("Movie");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertMovieData();
            }
        });
    }
    private void insertMovieData(){
        String name = Name.getText().toString();
        String year = Year.getText().toString();
        String description = Desc.getText().toString();
        String cast = Cast.getText().toString();
        String direct = Director.getText().toString();
        String Hall = Halls.getText().toString();
        String image = imageLink.getText().toString();
        String link = Link.getText().toString();

        Movie movie = new Movie(name,year,description,cast,direct,Hall,image,link);

        MovieDBRef.push().setValue(movie);
        Toast.makeText(AddMovie.this,"Data Saved Successfully !" , Toast.LENGTH_LONG).show();
    }
}