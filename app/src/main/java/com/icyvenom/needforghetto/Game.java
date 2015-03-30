package com.icyvenom.needforghetto;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;

/**
 * Created by anton on 2015-03-30.
 *
 * Controller candidate
 *
 */


//TODO: make the game,
//TODO: currently we only display a dummy of time passed in seconds since start
public class Game {

    private int gameTimeSec;

    public void update(long gameTime) {

        gameTimeSec = (int) (gameTime/1000);
        
    }

    public void draw(Canvas c) {
        c.drawColor(Color.BLACK);

        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);

        c.drawText(String.valueOf(gameTimeSec), 100, 100, p);
    }

}
