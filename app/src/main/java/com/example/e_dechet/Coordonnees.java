package com.example.e_dechet;

import java.util.HashMap;
import java.util.Map;

public class Coordonnees {
    public Double latitude;
    public Double longitude;
    public  String description;

    public Map<String, Boolean> stars = new HashMap<>();

    public Coordonnees() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Coordonnees(String description,Double latitude, Double logitude) {
        this.latitude = latitude;
        this.longitude = logitude;
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Boolean> getStars() {
        return stars;
    }

    public void setStars(Map<String, Boolean> stars) {
        this.stars = stars;
    }
}
