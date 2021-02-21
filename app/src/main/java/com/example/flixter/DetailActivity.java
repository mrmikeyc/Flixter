package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {

    // API Key for accessing YouTube content
    private static final String YOUTUBE_API_KEY = "AIzaSyBSXUd5ZDhHu1vIJSy0T7F1RSznvxkHMqQ";
    public static final String VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String DETAIL_ACTIVITY_TAG = "DetailActivity";

    // Variables for details view
    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;
    YouTubePlayerView youtubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle_Details);
        tvOverview = findViewById(R.id.tvOverview_Details);
        ratingBar = findViewById(R.id.ratingBar_Details);
        youtubePlayerView = findViewById(R.id.player);

        // Instead, using Parcelable, we passed in the entire movie
        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("MOVIE"));
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        ratingBar.setRating((float) movie.getRating());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEOS_URL, movie.getMovieId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(DETAIL_ACTIVITY_TAG, "Async request for Youtube video success");
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");

                    // Extract the key for the movie from the results JSONArray
                    if (results.length() == 0) {
                        return;
                    } else {
                        String youtubeVideoKey = results.getJSONObject(0).getString("key");
                        Log.d(DETAIL_ACTIVITY_TAG, "Key: " + youtubeVideoKey);
                        initializeYouTube(youtubeVideoKey);
                    }
                } catch (JSONException e) {
                    Log.e(DETAIL_ACTIVITY_TAG, "Failed to parse JSON", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });
    }

    private void initializeYouTube(final String youtubeVideoKey) {
        youtubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(DETAIL_ACTIVITY_TAG, "YouTube player initialization success");
                // Need to get the movie ID that each movie is associated with
                youTubePlayer.cueVideo(youtubeVideoKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(DETAIL_ACTIVITY_TAG, "YouTube player initialization failure");


            }
        });
    }
}