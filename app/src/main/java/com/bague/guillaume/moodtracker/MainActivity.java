package com.bague.guillaume.moodtracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecycler;
    private RecyclerViewAdapter mAdapter;
    private Button mHistoryBtn;
    private Button mCommentBtn;

    public static final String PREF_MOOD_DAY1 = "PREF_MOOD_DAY1" ;
    public static final String PREF_MOOD_DAY2 = "PREF_MOOD_DAY2" ;
    public static final String PREF_MOOD_DAY3 = "PREF_MOOD_DAY3" ;
    public static final String PREF_MOOD_DAY4 = "PREF_MOOD_DAY4" ;
    public static final String PREF_MOOD_DAY5 = "PREF_MOOD_DAY5" ;
    public static final String PREF_MOOD_DAY6 = "PREF_MOOD_DAY6" ;
    public static final String PREF_MOOD_DAY7 = "PREF_MOOD_DAY7" ;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHistoryBtn = findViewById(R.id.mainActivity_History_Btn);
        mCommentBtn = findViewById(R.id.mainActivity_Comment_Btn);
        mCommentBtn.setOnClickListener(this);
        mHistoryBtn.setOnClickListener(this);

        mHistoryBtn.setTag(1);
        mCommentBtn.setTag(2);


        mAdapter = new RecyclerViewAdapter(this);

        mRecycler = (RecyclerView) findViewById(R.id.recyclerView);

        SnapHelper snapHelper = new PagerSnapHelper();

        snapHelper.attachToRecyclerView(mRecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        mRecycler.setLayoutManager(layoutManager);

        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if((int) v.getTag() == 1) {
            //Toast.makeText(this,mAdapter.getCurrentImage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
            startActivity(intent);
            setPrefs(PREF_MOOD_DAY1,mAdapter.getCurrentImage(),this);
            System.out.println(getPrefs(PREF_MOOD_DAY1,this));

        } else if((int) v.getTag() == 2) {
            Toast.makeText(this, "Bouton comment", Toast.LENGTH_SHORT).show();
            //commentAlertDialog();
        }

    }

    private void commentAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.comment_popup)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //  System.out.println("click sur ok");
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //  System.out.println("click sur back");
                    }
                })
                .create()
                .show();
    }

    public static void setPrefs (String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_MOOD_DAY7,preferences.getString(PREF_MOOD_DAY6,"-1"));
        editor.putString(PREF_MOOD_DAY6,preferences.getString(PREF_MOOD_DAY5,"-1"));
        editor.putString(PREF_MOOD_DAY5,preferences.getString(PREF_MOOD_DAY4,"-1"));
        editor.putString(PREF_MOOD_DAY4,preferences.getString(PREF_MOOD_DAY3,"-1"));
        editor.putString(PREF_MOOD_DAY3,preferences.getString(PREF_MOOD_DAY2,"-1"));
        editor.putString(PREF_MOOD_DAY2,preferences.getString(PREF_MOOD_DAY1,"-1"));
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefs (String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "");
    }
}
