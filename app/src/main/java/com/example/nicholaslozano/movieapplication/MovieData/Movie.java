//this object holds information about a movie
package com.example.nicholaslozano.movieapplication.MovieData;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String overview;
    private String poster_path;

    public Movie(String title, String overview, String poster_path) {
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return "    " + overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return "http://image.tmdb.org/t/p/original/" + poster_path;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    public String toString() {
        return title;
    }
}
