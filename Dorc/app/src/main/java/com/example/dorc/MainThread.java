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
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
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
