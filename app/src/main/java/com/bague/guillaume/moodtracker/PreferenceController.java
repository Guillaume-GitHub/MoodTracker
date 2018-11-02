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


    public static void setPrefs (String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefs (String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "");
    }

    public static void dailyTasks(Context context){
        System.out.println("DailyTasks");

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

        System.out.println("PREF_COMMENT_7 = " + preferences.getString(PREF_COMMENT_7,"") );
        System.out.println("PREF_COMMENT_6 = " + preferences.getString(PREF_COMMENT_6 ,"") );
        System.out.println("PREF_COMMENT_5 = " + preferences.getString(PREF_COMMENT_5,"") );
        System.out.println("PREF_COMMENT_4 = " + preferences.getString(PREF_COMMENT_4,"") );
        System.out.println("PREF_COMMENT_3 = " + preferences.getString(PREF_COMMENT_3,"") );
        System.out.println("PREF_COMMENT_2 = " + preferences.getString(PREF_COMMENT_2,"") );
        System.out.println("PREF_COMMENT_1 = " + preferences.getString(PREF_COMMENT_1,"") );
        System.out.println("PREF_CURRENT = " + preferences.getString(PREF_CURRENT_COMMENT,"") );

        System.out.println("PREF_MOOD_7 = " + preferences.getString(PREF_MOOD_DAY7,"") );
        System.out.println("PREF_MOOD_6 = " + preferences.getString(PREF_MOOD_DAY6 ,"") );
        System.out.println("PREF_MOOD_5 = " + preferences.getString(PREF_MOOD_DAY5,"") );
        System.out.println("PREF_MOOD_4 = " + preferences.getString(PREF_MOOD_DAY4,"") );
        System.out.println("PREF_MOOD_3 = " + preferences.getString(PREF_MOOD_DAY3,"") );
        System.out.println("PREF_MOOD_2 = " + preferences.getString(PREF_MOOD_DAY2,"") );
        System.out.println("PREF_MOOD_1 = " + preferences.getString(PREF_MOOD_DAY1,"") );
        System.out.println("PREF_MOOD_CuRRENT = " + preferences.getString(PREF_POSITION_RECYCLERVIEW,"") );


    }
}
