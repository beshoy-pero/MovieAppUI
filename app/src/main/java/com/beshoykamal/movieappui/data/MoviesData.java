package com.beshoykamal.movieappui.data;

import com.beshoykamal.movieappui.model.Modelretro;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MoviesData {

    private final static String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private MoviesInterface moviesInterface;
    private static MoviesData INSTANCE;


    public MoviesData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        moviesInterface = retrofit.create(MoviesInterface.class);

    }

    public static MoviesData getINSTANCE() {

        if (null == INSTANCE){
            INSTANCE = new MoviesData();
        }
        return INSTANCE;
    }

    public Observable<Response> getPosts()
    {
        return moviesInterface.getPostts();
    }

}
