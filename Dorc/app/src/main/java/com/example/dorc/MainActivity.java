package com.example.dorc;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends FragmentActivity {
    Player testPlayer = new Player();

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        renderGold();

        setContentView(R.layout.activity_main);

        SharedViewModel sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        sharedViewModel.getSelected().observe(this, (PlayerHit playerHit)-> {
                Log.i(TAG, "onChanged: received freshObject");
                if (playerHit != null) {
                    if(playerHit.hit){
                        testPlayer.getGold().increaseGold(20);

                        int goldAmount = testPlayer.getGold().getAmount();
                        TextView goldDisplay = findViewById(R.id.playerGold);
                        goldDisplay.setText(String.valueOf(goldAmount));
                    }
                    Log.i(TAG, "object was updated");
                //
            }
        });
    }
    //TODO convert to lambda expression
    public void renderGold() {
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    updateGold(false, testPlayer);
                }
            }

        }).start();
    }


    public void updateGold(boolean hitOrResume, Player testPlayer){
        long lastMeasured = testPlayer.getLastMeasured();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long timeDifference = currentTime-lastMeasured;

        if(timeDifference > 1000 && timeDifference != currentTime) {
            if (hitOrResume) {
                //TODO needs to be accurately updated with hit value
                testPlayer.getGold().increaseGold(1);
                //Update visual value by chunk
            }else {
                testPlayer.getGold().increaseGold(1);
            }

            Gold testGold = testPlayer.getGold();
            int goldAmount = testGold.getAmount();
            Log.i(TAG, "curr gold: " + goldAmount);
            TextView goldDisplay = findViewById(R.id.playerGold);
            goldDisplay.setText(String.valueOf(goldAmount));

            testPlayer.setLastMeasured(Calendar.getInstance().getTimeInMillis());

        }else {
            if(timeDifference == currentTime) {
                testPlayer.setLastMeasured(currentTime);
            }
        }

    }
}
