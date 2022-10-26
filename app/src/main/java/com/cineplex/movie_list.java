package com.cineplex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.app.ProgressDialog;
import android.os.Bundle;

import java.util.ArrayList;


public class movie_list extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MovieListRead> movieArrayList;
    movieAdapter movieAdapter;
    DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);


        recyclerView = findViewById(R.id.movie_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbref = FirebaseDatabase.getInstance().getReference("Movie");
        movieArrayList = new ArrayList<>();
        movieAdapter = new movieAdapter(movie_list.this,movieArrayList);

        recyclerView.setAdapter(movieAdapter);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MovieListRead movie = dataSnapshot.getValue(MovieListRead.class);
                    movieArrayList.add(movie);
                }
                movieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}