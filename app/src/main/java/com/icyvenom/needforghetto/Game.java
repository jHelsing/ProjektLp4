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

import java.util.List;

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
    private List<Enemy> enemies;
    private BulletList bullets;
    //private static List<Bullet> bullets;

    Game(Resources resources) {
        this.resources = resources;
        this.player = Player.getInstance();
        this.bullets = BulletList.getInstance();
        player.setSprite(BitmapFactory.decodeResource(this.resources, R.drawable.car));
    }

    public void update(long gameTime) {
        gameTimeSec = (int) (gameTime/1000);
        playerCollision();
        enemyCollision();
        if(bullets.size() != 0) {
            for(Bullet b : bullets) {
                b.move();
            }
        }
    }

    public void draw(Canvas c) {
        c.drawColor(Color.WHITE);

        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(40);
        c.drawText(String.valueOf(gameTimeSec), 400, 400, p);
        c.drawBitmap(player.getSprite(), player.getPosition().x, player.getPosition().y, null);
        if(bullets.size() != 0) {
            for(Bullet b : bullets) {
                b.move();
                c.drawBitmap(b.getSprite(),b.getPosition().x, b.getPosition().y, null);
            }
        }
    }

    public void move(float x, float y) {
        player.move((int) x, (int) y);
    }

    public void playerCollision(){
        if(enemies != null) {
            for (Enemy e : enemies) {
                if (player.getHitbox().intersect(e.getHitbox())) {
                    player.kill();
                    e.kill();
                    enemies.remove(e);
                }
            }
        }
    }
    public void enemyCollision(){
        if(enemies != null && bullets != null) {
            for (Enemy e : enemies) {
                for (Bullet b : bullets) {
                    if (e.getHitbox().intersect(b.getHitbox())) {
                        e.kill();
                        enemies.remove(e);
                        bullets.remove(b);
                    }
                }
            }
        }
    }

}
