package com.cineplex;

public class MovieListRead {
    String Name;
    String Year;
    String Cast;
    String Halls;

    public MovieListRead(){}

    public MovieListRead(String name, String year, String cast, String halls) {
        Name = name;
        Year = year;
        Cast = cast;
        Halls = halls;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getCast() {
        return Cast;
    }

    public void setCast(String cast) {
        Cast = cast;
    }

    public String getHalls() {
        return Halls;
    }

    public void setHalls(String halls) {
        Halls = halls;
    }
}
