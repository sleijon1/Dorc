package com.example.dorc;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;

import java.util.Calendar;

public class MainActivity extends FragmentActivity {
    public static Player testPlayer = new Player();
    private static final String TAG = "MainActivity";

    ImageView previouslySelected = null;
    SharedViewModel sharedViewModel;
    FragmentManager fragMan;

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
        ImageButton backpackBtn = findViewById(R.id.backpackBtn);
        ImageButton equipmentBtn = findViewById(R.id.equipmentBtn);
        TextView dmgText = findViewById(R.id.dmgTextView);
      //  dmgText.setVisibility(View.INVISIBLE);
        int dmgInitialPos = dmgText.getTop();


        // ONCLICKS FOR INTERFACE
        // ONCLICK FOR INVENTORY
        backpackBtn.setOnClickListener( (View view) -> {
            fadeAnimation(view);

            LinearLayout fragContainer = findViewById(R.id.inv_frag_container);

            fragMan = getSupportFragmentManager();
            FragmentTransaction fragTransaction = fragMan.beginTransaction();

            Fragment myFrag = new InventoryFragment();
            fragTransaction.add(fragContainer.getId(), myFrag, "inventory_fragment");
            fragTransaction.commit();
        });

        // ONCLICK FOR EQUIPMENT
         equipmentBtn.setOnClickListener( (View view) -> {
            fadeAnimation(view);
            LinearLayout fragContainer = findViewById(R.id.inv_frag_container);

            fragMan = getSupportFragmentManager();
            FragmentTransaction fragTransaction = fragMan.beginTransaction();

            Fragment myFrag = new EquipmentFragment();
            fragTransaction.add(fragContainer.getId(), myFrag, "equipment fragment");
            fragTransaction.commit();

        });

        //WARRIOR PRESELECTED
        previouslySelected = warriorImage;
        warriorImage.setBackgroundResource(R.drawable.borderselected);

        // ANIMATION TEXT
        ObjectAnimator colorAnim = ObjectAnimator.ofInt(goldDisplay, "textColor",
                Color.YELLOW, Color.BLACK);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setDuration(500);

        //HIT ANIMATIONS
        AnimatorSet regularAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.dmg_text_anim);
        AnimatorSet elimAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.elim_text_anim);


        // Communication to game fragment
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        sharedViewModel.getSelected().observe(this, (PlayerHit playerHit)-> {
                Log.i(TAG, "onChanged: received freshObject");
                if (playerHit != null) {

                    regularAnimation.setTarget(dmgText);
                    elimAnimation.setTarget(dmgText);

                    String dmgString = String.valueOf(playerHit.getDamage());
                    dmgText.setText(dmgString);
                    dmgText.setTextColor(Color.parseColor("#ffffff"));
                    dmgText.setVisibility(View.VISIBLE);

                    if(playerHit.hit){
                        if(playerHit.getFinishingBlow()){
                            dmgText.setText(R.string.final_blow);
                            elimAnimation.start();

                            testPlayer.getGold().increaseGold(50);

                            Gear loot = playerHit.getLoot();
                            Inventory playerInventory = testPlayer.getPlayerInventory();
                            if(loot != null) {
                                playerInventory.putInInventory(loot);
                                playerHit.setLoot(null);
                            }
                            currHealth.setProgress(100);
                        }else{
                            regularAnimation.start();
                            testPlayer.getGold().increaseGold(20);
                            int calculatedDmg = playerHit.getDamage();
                            int barHealth = currHealth.getProgress();
                            currHealth.setProgress(barHealth - calculatedDmg);
                        }
                        colorAnim.start();
                        //might have to put this earlier if delay is noticeable
                        int goldAmount = testPlayer.getGold().getAmount();
                        goldDisplay.setText(String.valueOf(goldAmount));
                    }
                    Log.i(TAG, "object was updated");
                //
            }
        });

        // LISTENS TO FRAGMENT CANCELLATIONS
        sharedViewModel.getFragmentToCancel().observe(this, (Fragment fragment)-> {
            if(fragment != null){
                fragMan.beginTransaction().remove(fragment).commit();
            }
        });

        // ANIMATION & LISTENERS SELECT ORC
        setImageListeners(warriorImage, sharedViewModel);
        setImageListeners(mageImage, sharedViewModel);
        setImageListeners(rogueImage, sharedViewModel);

        sharedViewModel.select(true);
    }

    @Override
    public void onPause(){
        super.onPause();
        sharedViewModel.select(false);
    }


    public void setImageListeners(ImageView v, SharedViewModel viewModel){
        v.setOnClickListener( (View orcView) -> {
                if(!(orcView.getId() == previouslySelected.getId())){
                    fadeAnimation(orcView);
                    previouslySelected.setBackgroundResource(R.drawable.imageborder);
                    orcView.setBackgroundResource(R.drawable.borderselected);

                    //Tell GameFragment
                    String selectedOrc = getResources().getResourceEntryName(v.getId());
                    if(viewModel != null){
                        viewModel.select(selectedOrc);
                    }

                    //Reset health bar if swap target
                    ProgressBar currHealth = findViewById(R.id.healthBar);
                    currHealth.setProgress(100);
                }
                previouslySelected = (ImageView) orcView;
        });
    }

    public static void fadeAnimation(View viewToFade){
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(viewToFade,
                "alpha", 0.4f, 1f);
        fadeAnim.setDuration(500);
        fadeAnim.start();
    }

    public void renderGold() {
        new Thread( () -> {
                while(true){
                    updateGold(false, testPlayer);
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
            }else {
                testPlayer.getGold().increaseGold(1);
            }

            Gold testGold = testPlayer.getGold();
            int goldAmount = testGold.getAmount();
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
