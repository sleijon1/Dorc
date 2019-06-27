package com.example.dorc;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import static android.content.ContentValues.TAG;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    MainThread thread;
    private Orc testOrc;
    public SurfaceHolder surfaceHolder = getHolder();
    public boolean starting;
    private boolean destroying = false;

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        surfaceHolder.addCallback(this);

        thread = new MainThread(surfaceHolder, this);
        setFocusable(true);
    }

    // Because we call this from onTouchEvent, this code will be executed for both
    // normal touch events and for when the system calls this using Accessibility
    @Override
    public boolean performClick() {
        super.performClick();

        return true;
    }

    public void update(){

    }

    //Take argument orc and update testOrc to newly selected orc
    public void updateOrc(String selectedOrc){
        switch(selectedOrc){
            case "orcWarrior":
                testOrc = new orcWarrior(BitmapFactory.decodeResource(getResources(), R.drawable.orcwarriorchar));
                break;
            case "orcMage":
                testOrc = new orcMage(BitmapFactory.decodeResource(getResources(), R.drawable.orcmagechar));
                Log.i(TAG, "MAGE");
                break;
            case "orcRogue":
                testOrc = new orcRogue(BitmapFactory.decodeResource(getResources(), R.drawable.orcroguechar));
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
            testOrc.draw(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCREATED AGAIN");
        if(starting){
            thread.setRunning(true);
            testOrc = new Orc(BitmapFactory.decodeResource(getResources(), R.drawable.orcwarriorchar));
            thread.start();
        }else{
            thread.setPaused(false);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
            while (retry) {
                try {
                    if(destroying){
                        thread.setRunning(false);
                        thread.join();
                    }else{
                        thread.setPaused(true);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                retry = false;
            }
        Log.i(TAG, "waited successfully");
    }

    public Orc getCurrentOrc(){
        return testOrc;
    }

    public void setCurrentOrc(Orc newOrc){
        testOrc = newOrc;
    }
}
