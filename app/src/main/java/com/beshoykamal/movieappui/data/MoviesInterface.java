package com.beshoykamal.movieappui.data;

import com.beshoykamal.movieappui.model.Modelretro;
import com.beshoykamal.movieappui.model.MovieAPI;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MoviesInterface {

    @GET("now_playing?api_key=ce24e72c879e9107082dc00f343db8bf&language=en-US&page=1")
    public Observable<Response> getPostts();
}
