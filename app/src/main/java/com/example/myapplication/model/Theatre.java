package com.example.myapplication.model;

public class Theatre {
    // instance variables
    private boolean isOpen;
    private String location;
    private String name;
    private String rating;

    public Theatre() {
    }

    public Theatre(boolean isOpen, String location, String name, String rating) {
        this.isOpen = isOpen;
        this.location = location;
        this.name = name;
        this.rating = rating;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
