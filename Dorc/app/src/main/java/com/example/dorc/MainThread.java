package com.example.dorc;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

public class MainThread extends Thread {
    private static final String TAG = "MainThread";
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;

    @SuppressLint("ClickableViewAccessibility")
    public MainThread(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG,"registered touch");
                return false;
            }
        });

    }

    @Override
    public void run() {
        while (running) {
            canvas = null;


            try {
                Log.i(TAG, "Trying to lock canvas");
                canvas = this.surfaceHolder.lockCanvas();
                Log.i(TAG, "Successfully locked");
                synchronized (surfaceHolder) {
                    Log.i(TAG, "Updating");
                    this.gameView.update();
                    Log.i(TAG, "Drawing");
                    this.gameView.draw(canvas);
                    Log.i(TAG, "Going into unlock");
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                    Log.i(TAG, "Should've successfully unlocked");
                }
            } catch (Exception e) {
                //Add error handling
            }

        }
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

}
