package com.cineplex;

import android.widget.EditText;

public class Movie {

    String Name;
    String Year;
    String Desc;
    String Cast;
    String Director;
    String Halls;
    String ImageLink;
    String Link;

    public Movie(String name, String year, String desc, String cast, String director, String halls, String imageLink, String link) {
        Name = name;
        Year = year;
        Desc = desc;
        Cast = cast;
        Director = director;
        Halls = halls;
        ImageLink = imageLink;
        Link = link;
    }

    public String getName() {
        return Name;
    }

    public String getYear() {
        return Year;
    }

    public String getDesc() {
        return Desc;
    }

    public String getCast() {
        return Cast;
    }

    public String getDirector() {
        return Director;
    }

    public String getHalls() {
        return Halls;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public String getLink() {
        return Link;
    }
}
