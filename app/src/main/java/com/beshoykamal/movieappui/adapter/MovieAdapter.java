package com.beshoykamal.movieappui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beshoykamal.movieappui.R;
import com.beshoykamal.movieappui.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    Context mContext;
    List<Movie> mData;
    MovieItemClickListner movieItemClickListner;

    public MovieAdapter(Context mContext, List<Movie> mData,MovieItemClickListner listner) {
        this.mContext = mContext;
        this.mData = mData;
        movieItemClickListner= listner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_layout,parent,false);
       return new  MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.titleMovie.setText(mData.get(position).getTiltle());
        myViewHolder.imagMovie.setImageResource(mData.get(position).getThumpmail());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleMovie;
        private ImageView imagMovie;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagMovie = itemView.findViewById(R.id.item_movie_img);
            titleMovie = itemView.findViewById(R.id.item_movie_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /// bn3ml interface
                    movieItemClickListner.onMovieClick(mData.get(getAdapterPosition()),imagMovie);
                }
            });
        }
    }
}
