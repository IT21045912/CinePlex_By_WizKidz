package com.cineplex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class upadate_movie extends AppCompatActivity {

    EditText Name;
    EditText Cast;
    EditText Year;
    EditText Halls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upadate_movie);

        Name = (EditText)findViewById(R.id.Name);
        Cast = (EditText)findViewById(R.id.Cast);
        Year = (EditText)findViewById(R.id.Year);
        Halls = (EditText)findViewById(R.id.Halls);

        Name.setText(getIntent().getStringExtra("Name"));
        Cast.setText(getIntent().getStringExtra("Cast"));
        Year.setText(getIntent().getStringExtra("Year"));
        Halls.setText(getIntent().getStringExtra("halls"));



    }
}