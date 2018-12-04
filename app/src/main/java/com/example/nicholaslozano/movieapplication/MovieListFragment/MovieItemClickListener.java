//This click listener allows us to get the position from the recycler view so that we can pass the position to the details fragment
package com.example.nicholaslozano.movieapplication.MovieListFragment;

import android.view.View;

public interface MovieItemClickListener {

    void onItemClick(View v, int i);
}
