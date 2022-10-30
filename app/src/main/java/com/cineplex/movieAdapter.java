package com.cineplex;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.myViewHolder> {
    Context contest;
    ArrayList<MovieListRead> movieData;
    private Class<?> activity;

    public movieAdapter(Context contest, ArrayList<MovieListRead> movieData) {
        this.contest = contest;
        this.movieData = movieData;
    }

    @NonNull
    @Override
    public movieAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(contest).inflate(R.layout.movie_item,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull movieAdapter.myViewHolder holder, int position) {

        MovieListRead movie = movieData.get(position);

        holder.movieName.setText(movie.Name);
        holder.movieYear.setText(movie.Year);
        holder.movieCast.setText(movie.Cast);
        holder.movieHalls.setText(movie.Halls);
        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contest, activity);
                intent.putExtra("Name", movie.getName());
                intent.putExtra("Year",movie.getYear());
                intent.putExtra("Cast",movie.getCast());
                intent.putExtra("halls",movie.getHalls());
                contest.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }
    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView movieName , movieYear , movieCast , movieHalls;
        Button updateBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            movieName = itemView.findViewById(R.id.movieName);
            movieYear = itemView.findViewById(R.id.movieCast);
            movieCast = itemView.findViewById(R.id.movieCast);
            movieHalls = itemView.findViewById(R.id.movieHalls);
            updateBtn = itemView.findViewById(R.id.btnupdate);

        }
    }
}
