package com.beshoykamal.movieappui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.beshoykamal.movieappui.R;
import com.beshoykamal.movieappui.model.Slide;

import java.util.List;

public class SlidePagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<Slide> mList;

    public SlidePagerAdapter(Context mContext, List<Slide> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slidlayout = inflater.inflate(R.layout.slidepage, null);

        ImageView slidImg = slidlayout.findViewById(R.id.slid_Imag);
        TextView slidText = slidlayout.findViewById(R.id.slide_text);

        slidImg.setImageResource(mList.get(position).getImage());
        slidText.setText(mList.get(position).getTitle());

        container.addView(slidlayout);
        return slidlayout;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}