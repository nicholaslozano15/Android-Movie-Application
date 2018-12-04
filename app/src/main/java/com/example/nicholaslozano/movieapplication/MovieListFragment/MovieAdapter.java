//This adapter implements the views that will show movie posters
package com.example.nicholaslozano.movieapplication.MovieListFragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nicholaslozano.movieapplication.MovieData.Movie;
import com.example.nicholaslozano.movieapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private MovieItemClickListener movieItemClickListener;
    private ArrayList<Movie> movies;


    public MovieAdapter(ArrayList<Movie> movies, MovieItemClickListener movieItemClickListener) {
        this.movieItemClickListener = movieItemClickListener;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_list_item,
                viewGroup, false);
        return new MovieViewHolder(itemView, movieItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int i) {
        Movie movie = movies.get(i);

        ImageView imageView = viewHolder.moviePoster;
        String url = movie.getPosterPath();
        Picasso.get().load(url).into(imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView moviePoster;
        private MovieItemClickListener movieItemClickListener;

        public MovieViewHolder(@NonNull View itemView, MovieItemClickListener clickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            movieItemClickListener = clickListener;

            moviePoster = itemView.findViewById(R.id.movie_poster_imageview);
        }

        @Override
        public void onClick(View v) {
            movieItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }


}


