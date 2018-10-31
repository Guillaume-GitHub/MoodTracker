package com.bague.guillaume.moodtracker;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity {

private TextView day7;
private TextView day6;
private TextView day5;
private TextView day4;
private TextView day3;
private TextView day2;
private TextView day1;
private int i =0;
private int mDisplayWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
private String[] text = {"Il y a une semaine", "Il y a 6 jours", "Il y a 5 jours", "Il y a 4 jours", "Il y a 3 jours", "Avant Hier", "Hier"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        day7 = findViewById(R.id.textView7);
        day6 = findViewById(R.id.textView6);
        day5 = findViewById(R.id.textView5);
        day4 = findViewById(R.id.textView4);
        day3 = findViewById(R.id.textView3);
        day2 = findViewById(R.id.textView2);
        day1 = findViewById(R.id.textView1);

        String[] prefsArray = {
                MainActivity.PREF_MOOD_DAY7,
                MainActivity.PREF_MOOD_DAY6,
                MainActivity.PREF_MOOD_DAY5,
                MainActivity.PREF_MOOD_DAY4,
                MainActivity.PREF_MOOD_DAY3,
                MainActivity.PREF_MOOD_DAY2,
                MainActivity.PREF_MOOD_DAY1};

        TextView[] textViews = {day7, day6, day5, day4, day3, day2, day1};

        while (i < prefsArray.length) {

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textViews[i].getLayoutParams();
            String mPref = MainActivity.getPrefs(prefsArray[i], this);
            displayIcon(textViews[i],i);

            switch (mPref) {

                case "0":
                    params.width = (mDisplayWidth);
                    textViews[i].setLayoutParams(params);
                    textViews[i].setText(text[i]);
                    textViews[i].setBackgroundColor(textViews[i].getContext().getResources().getColor(R.color.banana_yellow));
                    break;

                case "1":
                    params.width = (int) Math.round(mDisplayWidth * 0.8);
                    textViews[i].setLayoutParams(params);
                    textViews[i].setText(text[i]);
                    textViews[i].setBackgroundColor(textViews[i].getContext().getResources().getColor(R.color.light_sage));
                    break;

                case "2":
                    params.width = (int) Math.round(mDisplayWidth * 0.6);
                    textViews[i].setLayoutParams(params);
                    textViews[i].setText(text[i]);
                    textViews[i].setBackgroundColor(textViews[i].getContext().getResources().getColor(R.color.cornflower_blue_65));
                    break;

                case "3":
                    params.width = (int) Math.round(mDisplayWidth * 0.4);
                    textViews[i].setLayoutParams(params);
                    textViews[i].setText(text[i]);
                    textViews[i].setBackgroundColor(textViews[i].getContext().getResources().getColor(R.color.warm_grey));
                    break;

                case "4":
                    params.width = (int) Math.round(mDisplayWidth * 0.2);
                    textViews[i].setLayoutParams(params);
                    textViews[i].setText(text[i]);
                    textViews[i].setBackgroundColor(textViews[i].getContext().getResources().getColor(R.color.faded_red));
                    break;

                default:
                    params.width = 0;
                    textViews[i].setLayoutParams(params);
                    System.out.println("exit");
                    break;
            }
            i++;
        }
    }

    public void displayIcon(TextView txtView, int i){

        String[] commentPref = {
                MainActivity.PREF_COMMENT_7,
                MainActivity.PREF_COMMENT_6,
                MainActivity.PREF_COMMENT_5,
                MainActivity.PREF_COMMENT_4,
                MainActivity.PREF_COMMENT_3,
                MainActivity.PREF_COMMENT_2,
                MainActivity.PREF_COMMENT_1};

        String str = MainActivity.getPrefs(commentPref[i],this);
        if(str != ""){
            txtView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_comment_black_48px,0);
        }else{

        }
    }
}
