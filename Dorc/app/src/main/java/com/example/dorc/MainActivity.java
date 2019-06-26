package com.example.dorc;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends FragmentActivity {
    Player testPlayer = new Player();
    private static final String TAG = "MainActivity";

    //TODO Need for checking which orc opponent to be highlighted. (Might wanna change this)
    ImageView previouslySelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        renderGold();

        setContentView(R.layout.activity_main);

        // FIND VIEWS
        ProgressBar currHealth = findViewById(R.id.healthBar);
        TextView goldDisplay = findViewById(R.id.playerGold);
        ImageView warriorImage = findViewById(R.id.orcWarrior);
        ImageView mageImage = findViewById(R.id.orcMage);
        ImageView rogueImage = findViewById(R.id.orcRogue);

        // ANIMATION TEXT
        ObjectAnimator colorAnim = ObjectAnimator.ofInt(goldDisplay, "textColor",
                Color.YELLOW, Color.BLACK);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setDuration(500);


        // Communication to game fragment
        SharedViewModel sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        sharedViewModel.getSelected().observe(this, (PlayerHit playerHit)-> {
                Log.i(TAG, "onChanged: received freshObject");
                if (playerHit != null) {
                    if(playerHit.hit){

                        if(playerHit.getFinishingBlow()){
                            currHealth.setProgress(100);
                            colorAnim.start();
                        }else{
                            testPlayer.getGold().increaseGold(20);
                            int calculatedDmg = playerHit.getDamage();
                            int barHealth = currHealth.getProgress();
                            currHealth.setProgress(barHealth - calculatedDmg);
                        }
                        //TODO might have to put this earlier if delay is noticeable
                        int goldAmount = testPlayer.getGold().getAmount();
                        goldDisplay.setText(String.valueOf(goldAmount));
                    }
                    Log.i(TAG, "object was updated");
                //
            }
        });

        // ANIMATION & LISTENERS SELECT ORC
        setImageListeners(warriorImage, sharedViewModel);
        setImageListeners(mageImage, sharedViewModel);
        setImageListeners(rogueImage, sharedViewModel);

    }

    public void setImageListeners(ImageView v, SharedViewModel viewModel){
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(v,
                "alpha", 0.4f, 1f);
        fadeAnim.setDuration(500);
        AnimatorSet wobble = new AnimatorSet();
        wobble.playTogether(fadeAnim);

        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(previouslySelected == null){
                    wobble.start();
                    v.setBackgroundResource(R.drawable.borderselected);
                }else if(!(v.getId() == previouslySelected.getId())){
                    wobble.start();
                    previouslySelected.setBackgroundResource(R.drawable.imageborder);
                    v.setBackgroundResource(R.drawable.borderselected);

                    String selectedOrc = getResources().getResourceEntryName(v.getId());
                    viewModel.select(selectedOrc);
                }
                previouslySelected = (ImageView) v;
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
