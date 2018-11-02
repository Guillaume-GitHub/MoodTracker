package com.bague.guillaume.moodtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Create by Guillaume on 31/10/2018
 */
public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Alarm Ring");
        PreferenceController.dailyTasks(context);
    }

}
