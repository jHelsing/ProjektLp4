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

import java.util.ArrayList;
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
    private Player player; //CHANGE BACK
    private List<Enemy> enemies = new ArrayList<Enemy>();

    private BulletList bullets;
    //private static List<Bullet> bullets;

    Game(Resources resources) {
        this.resources = resources;
        this.player = Player.getInstance();
        this.bullets = BulletList.getInstance();
        player.setSprite(BitmapFactory.decodeResource(this.resources, R.drawable.car));
        player.setHitbox(player.getSprite());
        enemies.add(new DummyEnemy(BitmapFactory.decodeResource(this.resources, R.drawable.enemycar), new Point(200, 200)));
        enemies.add(new DummyEnemy(BitmapFactory.decodeResource(this.resources, R.drawable.enemycar), new Point(500, 500)));
        enemies.add(new DummyEnemy(BitmapFactory.decodeResource(this.resources, R.drawable.enemycar), new Point(200, 700)));
        enemies.add(new DummyEnemy(BitmapFactory.decodeResource(this.resources, R.drawable.enemycar), new Point(900, 200)));
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

        /*
        Paint p = new Paint();
        p.setColor(Color.CYAN);
        p.setStrokeWidth(10);
        c.drawRect(enemies.get(0).getHitbox(), p);

        p.setColor(Color.RED);
        c.drawRect(player.getHitbox(), p);
        */

        c.drawBitmap(player.getSprite(), player.getPosition().x, player.getPosition().y, null);
        if(bullets.size() != 0) {
            for(Bullet b : bullets) {
                b.move();
                c.drawBitmap(b.getSprite(),b.getPosition().x, b.getPosition().y, null);
            }
        }
        if(enemies.size() != 0) {
            for(Enemy e : enemies) {
                c.drawBitmap(e.getSprite(),e.getPosition().x, e.getPosition().y, null);
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
