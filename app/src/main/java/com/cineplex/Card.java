package com.cineplex;

import android.widget.EditText;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Card {
    private String Name;
    private int CardNo;
    private int CVV;
    private String Date;

    public Card() {
    }

    public Card(String name, int cardNo, int CVV, String date) {
        Name = name;
        CardNo = cardNo;
        this.CVV = CVV;
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public int getCardNo() {
        return CardNo;
    }

    public int getCVV() {
        return CVV;
    }

    public String getDate() {
        return Date;
    }


}
