package com.cineplex;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

public class CreditCard extends AppCompatActivity {

    EditText Name;
    EditText CardNo;
    EditText CVV;
    EditText Date;
    Button confirmPay;

    DatabaseReference DBRef;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_credit_card);
        confirmPay = findViewById(R.id.confrimPayment);
        Name =  findViewById(R.id.name);
        CardNo =  findViewById(R.id.CardNo);
        CVV =  findViewById(R.id.cvv);
        Date =  findViewById(R.id.date);

        DBRef = FirebaseDatabase.getInstance().getReference();
        confirmPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

    }

    private void insertData(){
        String name = Name.getText().toString();
        int cardNo = Integer.parseInt(CardNo.getText().toString());
        String cvv = CVV.getText().toString();
        String Date;
    }
}