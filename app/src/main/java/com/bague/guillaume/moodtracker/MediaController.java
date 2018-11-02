package com.bague.guillaume.moodtracker;

import android.content.Context;
import android.media.MediaPlayer;


/**
 * Create by Guillaume on 02/11/2018
 */
public class MediaController {

    private MediaPlayer song1;
    private MediaPlayer song2;
    private MediaPlayer song3;
    private MediaPlayer song4;
    private MediaPlayer song5;

    public void playSong (Context context , int position){

        MediaPlayer[] mMediaPlayerTab = {
                song1 = MediaPlayer.create(context.getApplicationContext(), R.raw.song1),
                song2 = MediaPlayer.create(context.getApplicationContext(), R.raw.song2),
                song3 = MediaPlayer.create(context.getApplicationContext(), R.raw.song3),
                song4 = MediaPlayer.create(context.getApplicationContext(), R.raw.song4),
                song5 = MediaPlayer.create(context.getApplicationContext(), R.raw.song5)
        };

        mMediaPlayerTab[position].start();

    }

}
