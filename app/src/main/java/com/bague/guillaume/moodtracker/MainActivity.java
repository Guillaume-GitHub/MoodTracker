package com.bague.guillaume.moodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mHistoryBtn = findViewById(R.id.mainActivity_History_Btn);
        Button mCommentBtn = findViewById(R.id.mainActivity_Comment_Btn);
        ImageView mImage = findViewById(R.id.mainActivity_Smiley_Image);
        
    }
}
