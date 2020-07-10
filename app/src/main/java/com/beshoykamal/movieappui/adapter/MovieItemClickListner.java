package com.beshoykamal.movieappui.adapter;

import android.widget.ImageView;

import com.beshoykamal.movieappui.model.Movie;
import com.beshoykamal.movieappui.model.MovieAPI;

public interface MovieItemClickListner {
    void onMovieClick(Movie movie , ImageView movieimagview);


    // we need image view to make shared animation between activity
}
