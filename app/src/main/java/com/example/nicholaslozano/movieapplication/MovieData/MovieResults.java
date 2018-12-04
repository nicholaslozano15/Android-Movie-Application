//This object holds the movie results that are retrieved from TMDB
package com.example.nicholaslozano.movieapplication.MovieData;

import java.util.ArrayList;

public class MovieResults {
    private ArrayList<Movie> results;

    public MovieResults(ArrayList<Movie> results) {
        this.results = results;
    }

    public ArrayList<Movie> getMovies() {
        return results;
    }

}
