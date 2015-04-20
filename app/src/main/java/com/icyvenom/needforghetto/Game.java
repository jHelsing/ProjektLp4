package com.icyvenom.needforghetto;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
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
    private Resources resources;
    private Player player;
    Game(Resources resources) {
        this.resources = resources;
        this.player = this.player.getInstance();
    }

    public void update(long gameTime) {

        gameTimeSec = (int) (gameTime/1000);

    }

    public void draw(Canvas c) {
        c.drawColor(Color.BLACK);

        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(40);
        c.drawText(String.valueOf(gameTimeSec), 400, 400, p);
        c.drawBitmap(player.getSprite(), player.getPosition().x, player.getPosition().y);
    }

    public void move(float x, float y) {
        player.setPosition(new Point((int)x, (int)y));
    }
}
