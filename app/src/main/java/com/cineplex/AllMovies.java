package com.cineplex.;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

    public class AllMovies extends AppCompatActivity {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        RecyclerView recyclerView;
        TextView allRatingsInput;
        TextView ratingsMeanInput;
        float ratingsCal;
        float ratingsMean;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_all_movies);
            allRatingsInput = findViewById(R.id.all_ratings);
            ratingsMeanInput = findViewById(R.id.rating_mean);
            recyclerView = findViewById(R.id.recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
        }

        @Override
        protected void onStart() {
            super.onStart();
            getAllMovies(mAuth.getCurrentUser().getUid());
            ratingsCal = 0;
            ratingsMean = 0;
        }

        private void getAllMovies(String currentUser) {
            db.collection("movies")
                    .whereEqualTo("userId", currentUser)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            List<Movie> resCardList = new ArrayList<>();
                            for (QueryDocumentSnapshot movie : value) {
                                Movie eventObj = new Movie(
                                        movie.getString("id"),
                                        movie.getString("userId"),
                                        movie.getString("fullName"),
                                        movie.getString("email"),
                                        movie.getString("phone"),
                                        movie.getDouble("ratings"),
                                        movie.getDate("sendTime"),
                                        movie.getString("description")
                                );
                                //calculation
                                ratingsCal += movie.getDouble("ratings");
                                resCardList.add(eventObj);
                            }

                            allRatingsInput.setText(resCardList.size() + " Movies");
                            //calculation
                            ratingsMean = ratingsCal/resCardList.size();

                            ratingsMeanInput.setText(String.valueOf(ratingsMean));
                            MovieAdapter adapter = new MovieAdapter(AllMovies.this, resCardList, MovieView.class);
                            recyclerView.setAdapter(adapter);
                        }
                    });
        }
    

}
