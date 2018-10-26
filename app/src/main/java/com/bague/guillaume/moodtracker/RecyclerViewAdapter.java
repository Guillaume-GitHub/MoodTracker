package com.bague.guillaume.moodtracker;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Create by Guillaume BAGUE  on 26/10/2018
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;

    private int[] mImages = {
            R.drawable.smiley_super_happy,
            R.drawable.smiley_happy,
            R.drawable.smiley_normal,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_sad
    };

    public RecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private View mContainer;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.activity_recyclerview_ImageView);
            mContainer = itemView;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View v = mInflater.inflate(R.layout.activity_recyclerview_slider,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.mImageView.setImageResource(mImages[i]);

    }

    @Override
    public int getItemCount() {
        return mImages.length;
    }
}

