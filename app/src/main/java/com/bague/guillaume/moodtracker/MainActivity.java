package com.bague.guillaume.moodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private RecyclerViewAdapter mAdapter;
    private Button mHistoryBtn;
    private Button mCommentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHistoryBtn = findViewById(R.id.mainActivity_History_Btn);
        mCommentBtn = findViewById(R.id.mainActivity_Comment_Btn);


        mAdapter = new RecyclerViewAdapter(this);

        mRecycler = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        mRecycler.setLayoutManager(layoutManager);

        mRecycler.setAdapter(mAdapter);

    }
}
