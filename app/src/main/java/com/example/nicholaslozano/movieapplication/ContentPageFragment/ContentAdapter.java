//this adapter implements the list of recyclerviews in the app
package com.example.nicholaslozano.movieapplication.ContentPageFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicholaslozano.movieapplication.MovieData.Movie;
import com.example.nicholaslozano.movieapplication.MovieListFragment.MovieAdapter;
import com.example.nicholaslozano.movieapplication.MovieListFragment.MovieItemClickListener;
import com.example.nicholaslozano.movieapplication.R;

import java.util.ArrayList;

import androidx.navigation.Navigation;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private ArrayList<ArrayList<Movie>> content;
    private MovieItemClickListener movieItemClickListener;
    private ArrayList<String> headers;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView movieRecyclerView;

    public ContentAdapter(ArrayList<ArrayList<Movie>> content, ArrayList<String> headers){
        this.content = content;
        this.headers = headers;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View viewItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_movie_list_section,
                viewGroup, false);

        return new ContentViewHolder(viewItem);
    }


    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder contentViewHolder, final int i) {
        movieRecyclerView = contentViewHolder.movieRecyclerView;

        final ArrayList<Movie> movies = content.get(i);

        //implements the custom clicklistener so that I can pass the position of the recyclerview to the detailview.
        //this allows me to show particular movie details based on the item the user clicks
        movieItemClickListener = new MovieItemClickListener() {
            @Override
            public void onItemClick(View v, int i) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movies.get(i));
                Navigation.findNavController(v).navigate(R.id.action_contentPage_to_detailView, bundle);
            }
        };

        MovieAdapter movieAdapter = new MovieAdapter(movies, movieItemClickListener);

        linearLayoutManager = new LinearLayoutManager(contentViewHolder.itemView.getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        setUpRecyclerView(movieAdapter);

        contentViewHolder.sectionHeader.setText(headers.get(i));


    }


    @Override
    public int getItemCount() {
        return content.size();
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder{
        RecyclerView movieRecyclerView;
        TextView sectionHeader;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            movieRecyclerView = itemView.findViewById(R.id.movie_recyclerview);
            sectionHeader = itemView.findViewById(R.id.section_header);

        }
    }

    private void setUpRecyclerView(MovieAdapter movieAdapter){
        movieRecyclerView.setLayoutManager(linearLayoutManager);
        movieRecyclerView.setItemViewCacheSize(20);
        movieRecyclerView.setDrawingCacheEnabled(true);
        movieRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        movieRecyclerView.setAdapter(movieAdapter);
    }
}




