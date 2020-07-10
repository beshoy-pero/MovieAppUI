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
import com.beshoykamal.movieappui.model.Modelretro;
import com.beshoykamal.movieappui.model.Modelretro;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapterRetrofit extends RecyclerView.Adapter<MovieAdapterRetrofit.MyViewHolder> {
    String http = "https://image.tmdb.org/t/p/w500/";
    Context mContext;
    List<Modelretro> mData;
    RequestOptions option;
    MovieItemClickLRetro movieItemClickListner2;

    private ArrayList<Modelretro> postsList = new ArrayList<>();

    public MovieAdapterRetrofit( MovieItemClickLRetro listner) {
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

        myViewHolder.titleMovie.setText(postsList.get(position).getTitle());
//        myViewHolder.discMovie.setText(mData.get(position).getDesctiption());


        Glide.with(mContext).load(http + postsList.get(position).getPosterPath()).apply(option).into(myViewHolder.imagMovie);
//        Picasso.get().load(http+mData.get(position).getThumpmail()).into(myViewHolder.imagMovie);

    }

    @Override
    public int getItemCount() {
        return getItemViewType(0);
    }

    public void setPostsList(ArrayList<Modelretro> postsList){
        this.postsList=postsList;
        notifyDataSetChanged();
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

                    movieItemClickListner2.onMovieClick(postsList.get(getAdapterPosition()), imagMovie);
                }
            });
        }
    }
}
