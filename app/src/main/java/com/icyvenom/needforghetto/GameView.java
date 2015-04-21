package com.icyvenom.needforghetto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by anton on 2015-03-30.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Game game;
    private GameLoop gameLoop;

    public GameView(Context context, AttributeSet attrSet) {
        super(context, attrSet);

        // Focus must be on GameView so that events can be handled.
        this.setFocusable(true);
        // For intercepting events on the surface.
        this.getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // We can now safely start the game loop.
        startGame();
    }

    private void startGame(){
        game = new Game(getResources());

        gameLoop = new GameLoop(this.getHolder(), game);

        gameLoop.running = true;
        gameLoop.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameLoop.running = false;

        // Shut down the game loop thread cleanly.
        boolean retry = true;
        while(retry) {
            try {
                gameLoop.join();
                retry = false;
            } catch (InterruptedException e) {}
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){
            Log.v("com.icyvenom.needforghetto", "user pressed down (" + event.getRawX() + ", " + event.getRawY() + ")");
            game.move(event.getRawX(), event.getRawY());
        }

        if(action == MotionEvent.ACTION_MOVE) {
            Log.v("com.icyvenom.needforghetto", "user moved finger (" + event.getRawX() +", "+ event.getRawY() + ")");
            game.move(event.getRawX(), event.getRawY());
        }

        if(action == MotionEvent.ACTION_UP){
            Log.v("com.icyvenom.needforghetto", "user stopped pressing (" + event.getRawX() +", "+ event.getRawY()+")");
        }

        return true;
    }

}
