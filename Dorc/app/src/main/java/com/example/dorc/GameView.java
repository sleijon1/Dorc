package com.example.dorc;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    MainThread thread;
    private Orc testOrc;
    public SurfaceHolder surfaceHolder = getHolder();
    //This player will not be created in gameView but is here for testing purposes
    private Player testPlayer;

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        surfaceHolder.addCallback(this);

        thread = new MainThread(surfaceHolder, this);
        setFocusable(true);

    }

    public void update(boolean hitOrResume){
        updateGold(hitOrResume);
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

        //testPlayer.getGold().;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
            Log.i(TAG, "Trying to draw Orc");
            testOrc.draw(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        testOrc = new Orc(BitmapFactory.decodeResource(getResources(), R.drawable.orcboytwo));
        testPlayer = new Player();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            }  catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }
}
