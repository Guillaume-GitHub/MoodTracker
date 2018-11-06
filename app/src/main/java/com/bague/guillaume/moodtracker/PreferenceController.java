package com.bague.guillaume.moodtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Create by Guillaume on 02/11/2018
 */
public class PreferenceController {

    public static final String PREF_MOOD_DAY1 = "PREF_MOOD_DAY1" ;
    public static final String PREF_MOOD_DAY2 = "PREF_MOOD_DAY2" ;
    public static final String PREF_MOOD_DAY3 = "PREF_MOOD_DAY3" ;
    public static final String PREF_MOOD_DAY4 = "PREF_MOOD_DAY4" ;
    public static final String PREF_MOOD_DAY5 = "PREF_MOOD_DAY5" ;
    public static final String PREF_MOOD_DAY6 = "PREF_MOOD_DAY6" ;
    public static final String PREF_MOOD_DAY7 = "PREF_MOOD_DAY7" ;

    public static final String PREF_COMMENT_1 = "PREF_COMMENT_1";
    public static final String PREF_COMMENT_2 = "PREF_COMMENT_2";
    public static final String PREF_COMMENT_3 = "PREF_COMMENT_3";
    public static final String PREF_COMMENT_4 = "PREF_COMMENT_4";
    public static final String PREF_COMMENT_5 = "PREF_COMMENT_5";
    public static final String PREF_COMMENT_6 = "PREF_COMMENT_6";
    public static final String PREF_COMMENT_7 = "PREF_COMMENT_7";

    public static final String PREF_POSITION_RECYCLERVIEW = "-1";
    public static final String PREF_CURRENT_COMMENT ="PREF_CURRENT_COMMENT";

    /**
     * Write in preferences
     * @param key are name of preference key
     * @param value are value of preference key
     * @param context context
     */
    public static void setPrefs (String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Return value of Key
     * @param key are name of preference key
     * @param context context
     * @return
     */
    public static String getPrefs (String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "");
    }

    /**
     * Save all preferences
     * @param context context
     */
    public static void dailyTasks(Context context){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_COMMENT_7,preferences.getString(PREF_COMMENT_6,""));
        editor.putString(PREF_COMMENT_6,preferences.getString(PREF_COMMENT_5,""));
        editor.putString(PREF_COMMENT_5,preferences.getString(PREF_COMMENT_4,""));
        editor.putString(PREF_COMMENT_4,preferences.getString(PREF_COMMENT_3,""));
        editor.putString(PREF_COMMENT_3,preferences.getString(PREF_COMMENT_2,""));
        editor.putString(PREF_COMMENT_2,preferences.getString(PREF_COMMENT_1,""));
        editor.putString(PREF_COMMENT_1,preferences.getString(PREF_CURRENT_COMMENT,""));
        editor.putString(PREF_CURRENT_COMMENT,preferences.getString("",""));

        editor.putString(PREF_MOOD_DAY7,preferences.getString(PREF_MOOD_DAY6,"-1"));
        editor.putString(PREF_MOOD_DAY6,preferences.getString(PREF_MOOD_DAY5,"-1"));
        editor.putString(PREF_MOOD_DAY5,preferences.getString(PREF_MOOD_DAY4,"-1"));
        editor.putString(PREF_MOOD_DAY4,preferences.getString(PREF_MOOD_DAY3,"-1"));
        editor.putString(PREF_MOOD_DAY3,preferences.getString(PREF_MOOD_DAY2,"-1"));
        editor.putString(PREF_MOOD_DAY2,preferences.getString(PREF_MOOD_DAY1,"-1"));
        editor.putString(PREF_MOOD_DAY1,preferences.getString(PREF_POSITION_RECYCLERVIEW,"-1"));
        editor.putString(PREF_POSITION_RECYCLERVIEW,preferences.getString("-1","0"));

        editor.apply();

    }
}
