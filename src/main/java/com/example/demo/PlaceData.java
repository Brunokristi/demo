package com.example.demo;

import java.io.Serializable;

public class PlaceData implements Serializable {
    private String location;
    private int likes;
    private int totalRatings;

    public PlaceData(String location, int likes, int totalRatings) {
        this.location = location;
        this.likes = likes;
        this.totalRatings = totalRatings;
    }

    public String getLocation() {
        return location;
    }

    public int getLikes() {
        return likes;
    }

    public int getTotalRatings() {
        return totalRatings;
    }
}