package com.example.myapplication.model;

public class Genre {
    // instance variables
    private String avatarUrl;
    private String name;
    private String rating;
    private String type;
    private int drawable;

    /**
     *
     * @param avatarUrl
     * @param name
     * @param rating
     * @param type
     */
    public Genre(String avatarUrl, String name, String rating, String type) {
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.rating = rating;
        this.type = type;
    }

    public Genre( String name, int drawable) {
        this.name = name;
        this.drawable = drawable;
        setRating("0.0");
    }

    public Genre() {
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
