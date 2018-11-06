package com.bague.guillaume.moodtracker;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Create by Guillaume on 26/10/2018
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private MediaController mMedia = new MediaController();
    private int[] mImages = {
            R.drawable.smiley_super_happy,
            R.drawable.smiley_happy,
            R.drawable.smiley_normal,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_sad
    };

    private int[] mColors ={
            R.color.banana_yellow,
            R.color.light_sage,
            R.color.cornflower_blue_65,
            R.color.warm_grey,
            R.color.faded_red,
    };

    /**
     * Constructor
     * @param context context
     */
    public RecyclerViewAdapter(Context context) {
        mContext = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private LinearLayout mLinearLayout;

        private MyViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.activity_recyclerview_ImageView);
            mLinearLayout = itemView.findViewById(R.id.activity_recyclerview_LinearLayout);
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
        myViewHolder.mLinearLayout.setBackgroundColor(myViewHolder.mLinearLayout.getContext().getResources().getColor(mColors[i]));
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        PreferenceController.setPrefs(PreferenceController.PREF_POSITION_RECYCLERVIEW,Integer.toString(holder.getLayoutPosition()),mContext);
        System.out.println("View N° "+holder.getLayoutPosition());
        System.out.println(PreferenceController.getPrefs(PreferenceController.PREF_POSITION_RECYCLERVIEW,mContext));
        mMedia.playSong(mContext,holder.getLayoutPosition());

    }

    @Override
    public int getItemCount() {
        return mImages.length;
    }

}

