package com.bague.guillaume.moodtracker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecycler;
    private RecyclerViewAdapter mAdapter;
    private Button mHistoryBtn;
    private Button mCommentBtn;

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        mRecycler.setLayoutManager(layoutManager);

        mRecycler.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        if((int) v.getTag() == 1)
            Toast.makeText(this,"Bouton History", Toast.LENGTH_SHORT).show()  ;
        else if((int) v.getTag() == 2)
            Toast.makeText(this,"Bouton comment", Toast.LENGTH_SHORT).show()  ;
            commentAlertDialog();

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
}
