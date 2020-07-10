package com.beshoykamal.movieappui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.beshoykamal.movieappui.R;
import com.beshoykamal.movieappui.model.Movie;
import com.beshoykamal.movieappui.model.MovieAPI;
import com.beshoykamal.movieappui.ui.MainActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapterRC extends RecyclerView.Adapter<MovieAdapterRC.MyViewHolder> {
    String http = "https://image.tmdb.org/t/p/w500/";
    Context mContext;
    List<MovieAPI> mData;
    RequestOptions option;
    MovieItemClickListenr2 movieItemClickListner2;



    public MovieAdapterRC(Context mContext, List<MovieAPI> mData, MovieItemClickListenr2 listner) {
        this.mContext = mContext;
        this.mData = mData;
        this.movieItemClickListner2 = listner;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.eye).error(R.drawable.eye);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.titleMovie.setText(mData.get(position).getTiltle());
//        myViewHolder.discMovie.setText(mData.get(position).getDesctiption());


        Glide.with(mContext).load(http + mData.get(position).getThumpmail()).apply(option).into(myViewHolder.imagMovie);
//        Picasso.get().load(http+mData.get(position).getThumpmail()).into(myViewHolder.imagMovie);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleMovie, discMovie;
        private ImageView imagMovie;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagMovie = itemView.findViewById(R.id.item_movie_img);
            titleMovie = itemView.findViewById(R.id.item_movie_title);
//            discMovie = itemView.findViewById(R.id.disc_tv);
//                    movieItemClickListner.onMovieClick(mData.get(getAdapterPosition()),imagMovie);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    movieItemClickListner2.onMovieClicks(mData.get(getAdapterPosition()), imagMovie);
                }
            });
        }
    }
}
