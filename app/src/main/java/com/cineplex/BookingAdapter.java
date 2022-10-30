package com.cineplex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private Context context;
    private List<?> cardList;
    private Class<?> activity;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public BookingAdapter(Context context, List<?> CardList, Class<?> activity) {
        this.context = context;
        this.cardList = CardList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking feedback = (Booking) cardList.get(position);
        DocumentReference documentReference = db.collection("bookings").document(feedback.getId());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Booking data = new Booking(
                        documentSnapshot.getString("id"),
                        documentSnapshot.getString("userId"),
                        documentSnapshot.getString("fullName"),
                        documentSnapshot.getString("email"),
                        documentSnapshot.getString("phone"),
                        documentSnapshot.getDouble("ratings"),
                        documentSnapshot.getDate("sendTime"),
                        documentSnapshot.getString("description")
                );
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                holder.fullName.setText(data.getFullName());
                holder.ratings.setText(String.valueOf(data.getRatings() + " Ratings"));
                holder.date.setText(dateFormat.format(data.getSendTime()));
                holder.message.setText(data.getDescription());

                holder.preview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, activity);
                        intent.putExtra("id", data.getId());
                        intent.putExtra("fullName", data.getFullName());
                        intent.putExtra("email", data.getEmail());
                        intent.putExtra("phone", data.getPhone());
                        intent.putExtra("ratings", String.valueOf(data.getRatings()));
                        intent.putExtra("sendTime", dateFormat.format(data.getSendTime()));
                        intent.putExtra("description", data.getDescription());
                        context.startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return cardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fullName;
        TextView ratings;
        TextView date;
        TextView message;
        Button preview;

        public ViewHolder(View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.full_name);
            ratings = itemView.findViewById(R.id.ratings);
            date = itemView.findViewById(R.id.date);
            message = itemView.findViewById(R.id.message);
            preview = itemView.findViewById(R.id.preview);

        }
    }
}