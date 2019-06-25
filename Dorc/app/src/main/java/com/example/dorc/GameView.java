package com.example.dorc;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.widget.EditText;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;
import static java.lang.String.valueOf;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    MainThread thread;
    private Orc testOrc;
    public SurfaceHolder surfaceHolder = getHolder();
    //This player will not be created in gameView but is here for testing purposes



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
        testOrc = new Orc(BitmapFactory.decodeResource(getResources(), R.drawable.orcboytwo));
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

    // Returns the current battling orc for information like health
    public Orc getCurrentOrc(){
        return testOrc;
    }

    public void setCurrentOrc(Orc newOrc){
        testOrc = newOrc;
    }
}
