package com.example.myapplication.model;

public class Movies {


    private String MovieName;
    private String MoviePrice;
    private String MovieGenre;
    String ImageUrl;

    public Movies(String movieName, String moviePrice, String movieGenre, String imageUrl) {
        MovieName = movieName;
        MoviePrice = moviePrice;
        MovieGenre = movieGenre;
        ImageUrl = imageUrl;
    }

    public Movies() {

    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        this.MovieName = movieName;
    }

    public String getMovieGenre() {
        return MovieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.MovieGenre = movieGenre;
    }

    public String getMoviePrice() {
        return MoviePrice;
    }

    public void setMoviePrice(String moviePrice) {
        this.MoviePrice = moviePrice;
    }


}
