//This class holds all of the data for the application. It is where I get all the movie objects that are used in the app.
package com.example.nicholaslozano.movieapplication.ContentData;

import com.example.nicholaslozano.movieapplication.MovieData.Movie;
import com.example.nicholaslozano.movieapplication.MovieData.MovieResults;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Content {
    private ArrayList<Section> sections = new ArrayList<>();
    private ArrayList<ArrayList<Movie>> contentList = new ArrayList<>();
    private ArrayList<String> headerList = new ArrayList<>();

    //API key has been hidden for privacy reasons.
    private String apikey = "hidden";



    public Content(){
        setSections();
    }

    //this can be called from other classes to retrieve data from the internet before retrieving contentList
    public void getObjectsFromJson(){
        for (int i = 0; i < sections.size(); i++) {
            fetchJson(sections.get(i).getUrl());
            headerList.add(sections.get(i).getHeader());
        }
    }

    public ArrayList<ArrayList<Movie>> getContentList() {
        return contentList;
    }


    //this method gets the json body from the TMDB Url and parses it into a MovieResults object. It then adds
    //the results to a list
    private void fetchJson(String url) {

        OkHttpClient client = new OkHttpClient();
        String body = "";
        Response response;
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            response = null;
        }


        try {
            body = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Gson gson = new GsonBuilder().create();
        MovieResults movieResults = gson.fromJson(body, MovieResults.class);
        ArrayList<Movie> movies = movieResults.getMovies();
        contentList.add(movies);
    }

    public ArrayList<String> getHeaders() {
        return headerList;
    }


    private void setSections(){
        sections.add(new Section("In Theaters","https://api.themoviedb.org/3/movie/now_playing?api_key="+ apikey +"&region=us"));
        sections.add(new Section("Top Rated" ,"https://api.themoviedb.org/3/movie/top_rated?api_key="+apikey+"&language=en-US&page=1"));
        sections.add(new Section("Popular", "https://api.themoviedb.org/3/movie/popular?api_key="+apikey+"&language=en-US&page=1"));
        sections.add(new Section("Most Popular Family Movies", "https://api.themoviedb.org/3/discover/movie?api_key="+apikey+"&certification_country=US&with_genres=10751&sort_by=popularity.desc&language=en-US&vote_count.gte=10"));
        sections.add(new Section("Most Popular Romances", "https://api.themoviedb.org/3/discover/movie?api_key="+apikey+"&certification_country=US&with_genres=10749&sort_by=popularity.desc&language=en-US&vote_count.gte=10"));
        sections.add(new Section("Most Popular Comedies", "https://api.themoviedb.org/3/discover/movie?api_key="+apikey+"&certification_country=US&with_genres=35&sort_by=popularity.desc&language=en-US&vote_count.gte=10"));
        sections.add(new Section("Most Popular Documentaries", "https://api.themoviedb.org/3/discover/movie?api_key="+apikey+"&certification_country=US&with_genres=99&sort_by=popularity.desc&language=en-US&vote_count.gte=10"));
        sections.add(new Section("Most Popular Horror", "https://api.themoviedb.org/3/discover/movie?api_key="+apikey+"&certification_country=US&with_genres=27&sort_by=popularity.desc&language=en-US&vote_count.gte=10"));
    }

}