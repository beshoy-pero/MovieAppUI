package com.beshoykamal.movieappui.adapter;

import android.widget.ImageView;

import com.beshoykamal.movieappui.model.Modelretro;
import com.beshoykamal.movieappui.model.Movie;

public interface MovieItemClickLRetro {
    void onMovieClick(Modelretro movie, ImageView movieimagview);


    // we need image view to make shared animation between activity
}
