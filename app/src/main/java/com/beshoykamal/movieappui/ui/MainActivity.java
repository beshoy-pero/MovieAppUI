package com.beshoykamal.movieappui.ui;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.beshoykamal.movieappui.adapter.MovieAdapterRC;
import com.beshoykamal.movieappui.adapter.MovieAdapterRetrofit;
import com.beshoykamal.movieappui.adapter.MovieItemClickLRetro;
import com.beshoykamal.movieappui.adapter.MovieItemClickListenr2;
import com.beshoykamal.movieappui.adapter.MovieItemClickListner;
import com.beshoykamal.movieappui.R;
import com.beshoykamal.movieappui.adapter.MovieAdapter;
import com.beshoykamal.movieappui.adapter.SlidePagerAdapter;
import com.beshoykamal.movieappui.model.Modelretro;
import com.beshoykamal.movieappui.model.Movie;
import com.beshoykamal.movieappui.model.MovieAPI;
import com.beshoykamal.movieappui.model.Slide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.android.material.tabs.TabLayout;
import com.rbddevs.splashy.Splashy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListner, MovieItemClickLRetro, MovieItemClickListenr2 {

    private List<Slide> lastslide;
    private List<MovieAPI> movieAPIList, movieAPIList2;
    private ViewPager slidPager;
    private TabLayout indicator;
    private RecyclerView moviesRV, mRecyclerView, mRecyclerViewNP;
    RequestQueue mRequestQueue;
    MovieAdapterRC movieAdapterRC;
    MovieAdapterRetrofit movieAdapterRetrofit;
    MovieViewModel movieViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slidPager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        moviesRV = findViewById(R.id.rv_movies);
        mRecyclerView = findViewById(R.id.rv_recycle);
        mRecyclerViewNP = findViewById(R.id.rv_recycleNP);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getPosts();

        movieAdapterRetrofit = new MovieAdapterRetrofit(MainActivity.this);
        mRecyclerViewNP.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewNP.setAdapter(movieAdapterRetrofit);

        movieViewModel.postmutableLiveData.observe(this, new Observer<List<Modelretro>>() {
            @Override
            public void onChanged(List<Modelretro> movieAPS) {
//                        movieAdapterRetrofit.setPostsList((ArrayList<Modelretro>) movieAPS);

                Toast.makeText(MainActivity.this, "Data Change ", Toast.LENGTH_LONG).show();
            }
        });


        lastslide = new ArrayList<>();
        lastslide.add(new Slide(R.drawable.slideh, "slide Title / nmore text here"));
        lastslide.add(new Slide(R.drawable.slideb, "slide Title / nmore text here"));
        lastslide.add(new Slide(R.drawable.slidee, "slide Title / nmore text here"));
        lastslide.add(new Slide(R.drawable.slideb, "slide Title / nmore text here"));

        SlidePagerAdapter adapter = new SlidePagerAdapter(this, lastslide);
        slidPager.setAdapter(adapter);

        Timer time = new Timer();
        time.scheduleAtFixedRate(new MainActivity.SlideTimer(), 4000, 6000);

        indicator.setupWithViewPager(slidPager, true);


//        //ini data
        List<Movie> lstmovie = new ArrayList<>();
        lstmovie.add(new Movie("Joker", R.drawable.joker, R.drawable.slidee));
        lstmovie.add(new Movie("Sky Light", R.drawable.sky, R.drawable.slidee));
        lstmovie.add(new Movie("Eye", R.drawable.eye, R.drawable.slidee));
        lstmovie.add(new Movie("Killer", R.drawable.killer));
        lstmovie.add(new Movie("U R G E", R.drawable.slidee));
        MovieAdapter movieAdapter = new MovieAdapter(this, lstmovie, this);
        moviesRV.setAdapter(movieAdapter);
        moviesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //// ini data api

        aPiData();
        aPiDataNowPlay();
        SplashyMethod();

    }

    private void SplashyMethod() {
        new Splashy(this)                 // For JAVA : new Splashy(this)
                .setLogo(R.drawable.slidecircle)
                .setTitle("SHOW\n")
                .setTitleColor("#FFFFEB3B")
                .setSubTitle("Movies\nOpen Your Eyes\nDon't sleep I See you")
                .setSubTitleColor("#ffffff")
                .setSubTitleSize(18)
                .setLogoWHinDp(250, 250)
                .setBackgroundColor("#000000")
                .setFullScreen(true)
                .setTime(5000)
                .show();
    }

    private void aPiDataNowPlay() {

        mRecyclerViewNP.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieAPIList2 = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=ce24e72c879e9107082dc00f343db8bf&language=en-US&page=1";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject result = jsonArray.getJSONObject(i);

                                String tiltle = result.getString("title");
                                String discrip = result.getString("overview");
                                String posterPath = result.getString("poster_path");
                                String background = result.getString("backdrop_path");
                                String relesDate = result.getString("release_date");
                                Integer vote = result.getInt("vote_average");
                                Integer id = result.getInt("id");

                                movieAPIList2.add(new MovieAPI(tiltle, discrip, posterPath, id, vote, background, relesDate));
                                //// listtenr null

                                movieAdapterRC = new MovieAdapterRC(MainActivity.this, movieAPIList2, MainActivity.this);
                                mRecyclerViewNP.setAdapter(movieAdapterRC);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mRequestQueue.add(request);


    }

    private void aPiData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieAPIList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=ce24e72c879e9107082dc00f343db8bf&language=en-US&page=1";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject result = jsonArray.getJSONObject(i);


                                String tiltle = result.getString("title");
                                String discrip = result.getString("overview");
                                String posterPath = result.getString("poster_path");
                                String background = result.getString("backdrop_path");
                                String relesDate = result.getString("release_date");
                                Integer vote = result.getInt("vote_average");
                                Integer id = result.getInt("id");


                                movieAPIList.add(new MovieAPI(tiltle, discrip, posterPath, id, vote, background, relesDate));
                                //// listtenr null

                                movieAdapterRC = new MovieAdapterRC(MainActivity.this, movieAPIList, MainActivity.this);
                                mRecyclerView.setAdapter(movieAdapterRC);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mRequestQueue.add(request);


    }

    ///// send to activity
    public void onMovieClicks(MovieAPI movie,
                              ImageView movieimagview) {
        Intent in = new Intent(MainActivity.this, MovieDetailsActivity.class);
        in.putExtra("title", movie.getTiltle());
        in.putExtra("imgURL", movie.getThumpmail());
        in.putExtra("decrip", movie.getDesctiption());
        in.putExtra("date", movie.getDateimg());
        in.putExtra("vote", movie.getRatvote());
        in.putExtra("background", movie.getBackground());
        in.putExtra("id", movie.getId());

        ActivityOptions options = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                    movieimagview, "sharedImag");
        }
        startActivity(in, options.toBundle());

        Toast.makeText(this, "TITLE / " + movie.getTiltle(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieimagview) {
        // send information movie
        // make transition animation between activity

        Intent in = new Intent(MainActivity.this, MovieDetailsActivity.class);
        in.putExtra("title", movie.getTiltle());
        in.putExtra("imgURL", movie.getThumpmail());
        in.putExtra("imgCover", movie.getCoverphoto());

        ActivityOptions options = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                    movieimagview, "sharedImag");
        }
        startActivity(in, options.toBundle());

        Toast.makeText(this, "TITLE / " + movie.getTiltle(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMovieClick(Modelretro movie, ImageView movieimagview) {

    }


    class SlideTimer extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (slidPager.getCurrentItem() < lastslide.size() - 1)
                        slidPager.setCurrentItem(slidPager.getCurrentItem() + 1);
                    else
                        slidPager.setCurrentItem(0);

                }
            });
        }
    }


}
