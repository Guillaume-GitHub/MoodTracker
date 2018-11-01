package com.bague.guillaume.moodtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
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
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecycler;
    private RecyclerViewAdapter mAdapter;
    private Button mHistoryBtn;
    private Button mCommentBtn;
    public String mComment;

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

        startAlarmToSave(14,6);
        System.out.println("create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("Restart");
    }

    @Override
    public void onClick(View v) {
        if((int) v.getTag() == 1) {
            //Toast.makeText(this,mAdapter.getCurrentImage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
            startActivity(intent);
           // setMoodPrefs(PREF_MOOD_DAY1,mAdapter.getCurrentImage(),this);
           // setCommentPrefs(PREF_COMMENT_1,mComment,this);

           // System.out.println(getPrefs(PREF_MOOD_DAY1,this));
           // System.out.println(getPrefs(PREF_COMMENT_1,this));

        } else if((int) v.getTag() == 2) {
            Toast.makeText(this, "Bouton comment", Toast.LENGTH_SHORT).show();
            commentAlertDialog();
        }

    }

    private void commentAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.comment_popup,null);
        final EditText mEditText = (EditText) mView.findViewById(R.id.commentEditText);
         builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!mEditText.getText().toString().isEmpty()) {
                            mComment = mEditText.getText().toString();
                            System.out.println(mComment);

                        }else{
                            System.out.println("Vide");
                        }
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("annul");
                    }
                })
                 .setView(mView)
                 .create()
                 .show();
    }

    public static void setMoodPrefs (String key, String value, Context context) {
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

    public static void setCommentPrefs (String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_COMMENT_7,preferences.getString(PREF_COMMENT_6,""));
        editor.putString(PREF_COMMENT_6,preferences.getString(PREF_COMMENT_5,""));
        editor.putString(PREF_COMMENT_5,preferences.getString(PREF_COMMENT_4,""));
        editor.putString(PREF_COMMENT_4,preferences.getString(PREF_COMMENT_3,""));
        editor.putString(PREF_COMMENT_3,preferences.getString(PREF_COMMENT_2,""));
        editor.putString(PREF_COMMENT_2,preferences.getString(PREF_COMMENT_1,""));
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefs (String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "");
    }

    private void startAlarmToSave(int hours, int minuts){
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.get(Calendar.ZONE_OFFSET);
        mCalendar.get(Calendar.DST_OFFSET);
        mCalendar.set(Calendar.HOUR_OF_DAY, hours);
        mCalendar.set(Calendar.MINUTE, minuts);

        AlarmManager mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent mIntent;
        PendingIntent mPendindIntent;
        mIntent = new Intent(MainActivity.this,AlarmReceiver.class);
        mPendindIntent = PendingIntent.getBroadcast(getApplicationContext(),0,mIntent,0);

        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,mCalendar.getTimeInMillis(),mAlarmManager.INTERVAL_DAY, mPendindIntent);

    }
}
