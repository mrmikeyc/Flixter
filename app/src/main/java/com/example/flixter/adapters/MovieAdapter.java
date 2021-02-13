package com.example.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import java.util.List;

// For the overall adapter, we need to tell it what viewHolder we will use in the recycler view - we use the one defined in the inner class
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    // We need a context to inflate the view - where is the view coming from?
    Context context;
    List<Movie> movies;

    // Tag
    public static final String MOVIE_ADAPTER = "MovieAdapter";

    // Constructor for entire adapter
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // These are abstract methods that RecyclerView.Adapter<> needs us to implement

    // This inflates the layout - returns a viewholder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(MOVIE_ADAPTER, "onCreateViewHolder");
        // Using context (where the view is coming from), inflate that to use the item_movie.xml
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Involves populating data into the viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(MOVIE_ADAPTER, "onBindViewHolder " + position);

        // Get the movie at the passed in position
        Movie movie = movies.get(position);

        // Bind the movie data to the viewholder
        // Holder is the ViewHolder, which is the MovieAdapter.ViewHolder that we defined below and indicated in our Adapter<>
        holder.bind(movie);
    }

    // Tells you how many items are in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // First define an inner viewHolder class - this will be the representation of the item_movie.xml 'row' that we made
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        // The View passed into this constructor - itemView - will be the item_movie.xml view
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            // Use getter methods of movies to set attributes of viewHolder
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            String imageURL;

            // If the phone is in portrait, set imageURL to the poster image
            // Otherwise if the phone is landscape, set imageURL to backdrop image

            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageURL = movie.getBackdropPath();
            } else {
                imageURL = movie.getPosterPath();
            }

            // Need to render an image, so we'll use a library for that - Glide
            Glide.with(context).load(imageURL).into(ivPoster);
            // Recall that movie.getPosterPath() returns a URL to an image that it gets from an API
        }
    }
}
