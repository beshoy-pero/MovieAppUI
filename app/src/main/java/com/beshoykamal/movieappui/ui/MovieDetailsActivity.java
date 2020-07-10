package com.beshoykamal.movieappui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.beshoykamal.movieappui.R;
import com.beshoykamal.movieappui.adapter.MovieAdapterRC;
import com.beshoykamal.movieappui.model.MovieAPI;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView movieThaumilimg,covermovieimg;
    TextView tv_title,tv_describe,tv_vote,tv_date;
    FloatingActionButton play_fab;

    RequestQueue requestQueue;
    JsonObjectRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        inViews();
    }

    private void inViews() {
        movieThaumilimg = findViewById(R.id.detail_img);
        covermovieimg=findViewById(R.id.cover_imag);
        tv_title=findViewById(R.id.detail_title);
        tv_describe=findViewById(R.id.detail_discrip);
        tv_vote=findViewById(R.id.vote);
        tv_date=findViewById(R.id.date);
        play_fab=findViewById(R.id.play_fab);


        String movietitle = getIntent().getExtras().getString("title");
        tv_title.setText(movietitle);
        getSupportActionBar().setTitle(movietitle);

        String descrip = getIntent().getExtras().getString("decrip");
        tv_describe.setText(descrip);
        int id = getIntent().getExtras().getInt("id");


        String ImageResoursId= getIntent().getExtras().getString("imgURL");
        String http="https://image.tmdb.org/t/p/w500/";
        Glide.with(this).load(http+ImageResoursId).into(movieThaumilimg);


        int ratvote= getIntent().getExtras().getInt("vote");
        tv_vote.setText(ratvote+"");
        String date= getIntent().getExtras().getString("date");
        tv_date.setText(date);
        tv_date.append("\nID / "+id);

        String ImageCover= getIntent().getExtras().getString("background");
        Glide.with(this).load(http+ImageCover).into(covermovieimg);





        // setup animation
        covermovieimg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim));

        Toast.makeText(this, http+ImageResoursId, Toast.LENGTH_SHORT).show();

        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MovieDetailsActivity.this, "DON"+id, Toast.LENGTH_SHORT).show();
                requestQueue = Volley.newRequestQueue(MovieDetailsActivity.this);
                String url = "https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=ce24e72c879e9107082dc00f343db8bf&language=en-US";
                 request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray=response.getJSONArray("results");
                                    for (int i = 0; i <jsonArray.length() ; i++) {
                                        JSONObject result = jsonArray.getJSONObject(0);
                                        String key= result.getString("key");

                                        Intent in = new Intent(MovieDetailsActivity.this,Video_Player_Activity.class);
                                        in.putExtra("key",key);
                                        startActivity(in);

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MovieDetailsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                 requestQueue.add(request);
            }
        }

        );



    }
}
