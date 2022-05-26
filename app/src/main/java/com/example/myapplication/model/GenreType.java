package com.example.myapplication.model;

public enum GenreType {
    COMEDY("Comedy"), ACTION("Action"), LOVE("Drama"),
    DOCUMENTARY("Documentary"), HORROR("Horror"), FANTASY("Fantasy");
    private String name;

    GenreType(String name) {
        this.name = name;
    }
}
