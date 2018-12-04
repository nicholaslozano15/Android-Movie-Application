//This fragment holds all the details to the selected movie
package com.example.nicholaslozano.movieapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicholaslozano.movieapplication.MovieData.Movie;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailView extends Fragment {


    public DetailView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Movie movie = (Movie) getArguments().getSerializable("movie");
        View detailView = inflater.inflate(R.layout.fragment_detail_view, container, false);

        setDetails(detailView, movie);

        return detailView;
    }

    private void setDetails(View detailView, Movie movie){
        TextView titleTextView = detailView.findViewById(R.id.title_textview);
        TextView overviewTextView = detailView.findViewById(R.id.overview_textview);
        ImageView moviePosterImageView = detailView.findViewById(R.id.movie_poster_detailview);
        titleTextView.setText(movie.getTitle());
        overviewTextView.setText(movie.getOverview());
        Picasso.get().load(movie.getPosterPath()).into(moviePosterImageView);
    }
}
