package com.bague.guillaume.moodtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
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

import java.sql.SQLOutput;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mHistoryBtn;
    private Button mCommentBtn;
    private String mComment;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context contxt = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mHistoryBtn = findViewById(R.id.mainActivity_History_Btn);
        mCommentBtn = findViewById(R.id.mainActivity_Comment_Btn);
        Button test = findViewById(R.id.testBtn);

        mCommentBtn.setOnClickListener(this);
        mHistoryBtn.setOnClickListener(this);
        test.setOnClickListener(this);

        mHistoryBtn.setTag(1);
        mCommentBtn.setTag(2);
        test.setTag(3);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mAdapter = new RecyclerViewAdapter(this);

        SnapHelper snapHelper = new PagerSnapHelper();

        snapHelper.attachToRecyclerView(mRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mAdapter);

        startAlarmToSave();

        System.out.println("posistion apres destroy" + PreferenceController.getPrefs(PreferenceController.PREF_POSITION_RECYCLERVIEW,this));

        if(PreferenceController.getPrefs(PreferenceController.PREF_POSITION_RECYCLERVIEW,this) == "-1" || PreferenceController.getPrefs(PreferenceController.PREF_POSITION_RECYCLERVIEW,this) ==""){
            System.out.println("create first time");

        }else {
            mRecyclerView.scrollToPosition(Integer.decode(PreferenceController.getPrefs(PreferenceController.PREF_POSITION_RECYCLERVIEW,this)));
        }
    }

    @Override
    public void onClick(View v) {
        if((int) v.getTag() == 1) {
            Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
            startActivity(intent);

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
                            PreferenceController.setPrefs(PreferenceController.PREF_CURRENT_COMMENT,mComment,MainActivity.this);
                            System.out.println(mComment);

                        }else{
                            System.out.println("Vide");
                            PreferenceController.dailyTasks(MainActivity.this);
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

    public void startAlarmToSave() {

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.set(Calendar.HOUR_OF_DAY, 0);
        mCalendar.set(Calendar.MINUTE, 0);

        AlarmManager mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent mIntent = new Intent(MainActivity.this,AlarmReceiver.class);
        PendingIntent mPendindIntent = PendingIntent.getBroadcast(MainActivity.this,0,mIntent,0);

        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(),mAlarmManager.INTERVAL_DAY, mPendindIntent);
        System.out.println("Alarm are set");

    }
}
