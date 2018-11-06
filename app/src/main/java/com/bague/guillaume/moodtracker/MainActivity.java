package com.bague.guillaume.moodtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mHistoryBtn;
    private Button mCommentBtn;
    private String mComment;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private AlarmManager mAlarmManager;
    private Intent mIntent;
    private PendingIntent mPendindIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Create layout elements
        mHistoryBtn = findViewById(R.id.mainActivity_History_Btn);
        mCommentBtn = findViewById(R.id.mainActivity_Comment_Btn);

        // Set onClickListener to buttons
        mCommentBtn.setOnClickListener(this);
        mHistoryBtn.setOnClickListener(this);

        //Set tags on differents button
        mHistoryBtn.setTag(1);
        mCommentBtn.setTag(2);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new RecyclerViewAdapter(this);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


        if(PreferenceController.getPrefs(PreferenceController.PREF_POSITION_RECYCLERVIEW,this) == "-1" || PreferenceController.getPrefs(PreferenceController.PREF_POSITION_RECYCLERVIEW,this) ==""){

        }else {
            mRecyclerView.scrollToPosition(Integer.decode(PreferenceController.getPrefs(PreferenceController.PREF_POSITION_RECYCLERVIEW,this)));
        }

        startAlarm();
    }

    @Override
    public void onClick(View v) {
        if((int) v.getTag() == 1) {
            Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
            startActivity(intent);

        } else if((int) v.getTag() == 2) {
            commentAlertDialog();
        }
    }

    /**
     * Create an AlertDialog
     */
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

                        }
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                 .setView(mView)
                 .create()
                 .show();
    }

    /**
     * Set an alarm
     */
    public void startAlarm() {

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR,mCalendar.get(Calendar.YEAR));
        mCalendar.set(Calendar.MONTH,mCalendar.get(Calendar.MONTH));
        mCalendar.set(Calendar.DAY_OF_MONTH,mCalendar.get(Calendar.DAY_OF_MONTH));
        mCalendar.set(Calendar.HOUR_OF_DAY,23);
        mCalendar.set(Calendar.MINUTE,59);
        mCalendar.set(Calendar.SECOND,0);

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        mIntent = new Intent(this, AlarmReceiver.class);
        mPendindIntent = PendingIntent.getBroadcast(this, 1234, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mPendindIntent);
    }
}
