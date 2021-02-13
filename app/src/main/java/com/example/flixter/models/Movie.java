package com.example.flixter.models;

import com.facebook.stetho.common.ArrayListAccumulator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    String posterPath;
    String title;
    String overview;
    String backdropPath;

    // Create a constructor for the movie object that takes a JSONObject
    // We then want to read the json object and pull out the portions associated with the given keys
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    // This static method will ingest the JSON array and create a list of movies from it
    // This method is called from within MainActivity to create the list of movies
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        // responsible for iterating through the json array and creating a movie object for each item
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    // In order to generate these, right click here > Generate > Getters
    public String getPosterPath() {
        // For poster path, we want the entire URL for the API
        // Technically, we would go to the API and get the appropriate size too (w342), but
        // since we already know that we want w342, then we can just append the poster path
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    // Again, ideally you would tap the API in order to get the right resolution, but we hard coded it for this assignment
    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }
}


