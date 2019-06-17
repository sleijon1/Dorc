package com.example.dorc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class MainActivity extends FragmentActivity {
    private Player testPlayer;
    private Gold testGold;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        Log.i(TAG, "well we got here fam");
    }

    public void updateGold(boolean hitOrResume){
        long lastMeasured = testPlayer.getLastMeasured();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long timeDifference = currentTime-lastMeasured;

        if(timeDifference != currentTime){
            testPlayer.getGold().increaseGold(timeDifference, false);
        }
        if(hitOrResume){
            testPlayer.getGold().increaseGold(timeDifference, true);
            //Update visual value by chunk
        }

        testGold = testPlayer.getGold();
        int goldAmount = testGold.getAmount();
        TextView goldDisplay = findViewById(R.id.playerGold);
        String testInt = String.valueOf(goldAmount);
        Log.i(TAG, "got to setText" + testInt + goldDisplay);
        try{goldDisplay.setText(String.valueOf(goldAmount));}
        catch(Exception e){
            e.printStackTrace();
        }
        Log.i(TAG, "got past setText");
    }
}
