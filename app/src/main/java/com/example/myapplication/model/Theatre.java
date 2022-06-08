package com.example.myapplication.model;

public class Theatre {
    // instance variables
    private boolean isOpen;
    private String TheatreLocation;
    private String TheatreName;
    private String TheatreRating;

    public Theatre() {
    }

    public Theatre(boolean isOpen, String location, String name, String rating) {
        this.isOpen = isOpen;
        this.TheatreLocation = location;
        this.TheatreName = name;
        this.TheatreRating = rating;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getTheatreLocation() {
        return TheatreLocation;
    }

    public void setTheatreLocation(String theatreLocation) {
        this.TheatreLocation = theatreLocation;
    }

    public String getTheatreName() {
        return TheatreName;
    }

    public void setTheatreName(String theatreName) {
        this.TheatreName = theatreName;
    }

    public String getTheatreRating() {
        return TheatreRating;
    }

    public void setTheatreRating(String theatreRating) {
        this.TheatreRating = theatreRating;
    }
}
