package com.bague.guillaume.moodtracker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static android.graphics.Color.toArgb;

/**
 * Create by Guillaume BAGUE  on 25/10/2018
 */
public class ViewPagerAdapter extends PagerAdapter {

    public ViewPagerAdapter(Context context) {
        mContext = context;
    }

    Context mContext;
    LayoutInflater mInflater;

    public int[] mImage = {
            R.drawable.smiley_super_happy,
            R.drawable.smiley_happy,
            R.drawable.smiley_normal,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_sad
    };

    public int[] mColors = {
            R.color.banana_yellow,
            R.color.light_sage,
            R.color.cornflower_blue_65,
            R.color.warm_grey,
            R.color.faded_red
    };

    @Override
    public int getCount() {
        return mImage.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.activity_slider,container,false);
        LinearLayout layoutSlide = view.findViewById(R.id.linearLayoutSlide);
        ImageView imageViewSlide = view.findViewById(R.id.imageViewSlide);
        imageViewSlide.setImageResource(mImage[position]);
        layoutSlide.setBackgroundColor(mColors[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
