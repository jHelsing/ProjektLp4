package com.icyvenom.needforghetto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * @author Amar Kulaglic
 * @version 1.0
 */
public abstract class Bullet {
    /**
     * The damage of the bullet on power-up mode.
     */
    private int damageModifier;
    /**
     * The actual position of the bullet with a x- and y-coordinate.
     */
    private Point position;
    /**
     * The sprite is the appearance of the bullet.
     */
    public static Bitmap sprite;
    /**
     * The bulletPath is the path of the bullet.
     */
    private Path bulletPath;

    private BulletDirection direction;

    private Rect hitbox;

    public Bullet(Context context, Point position, int damageModifier, BulletDirection direction) {
        this.damageModifier = damageModifier;
        this.direction = direction;
        this.position = position;
        setHitbox(getSprite());
    }

    public Bullet() {
        this.position= new Point(Player.getInstance().getPosition().x, Player.getInstance().getPosition().y);
        this.direction=BulletDirection.UP;
        this.damageModifier=1;
        setHitbox(getSprite());
    }

    /**
     * Moves the bullet to the next Point in the path that was given when instanced.
     */
    public void move(){
        if(direction==BulletDirection.DOWN) {
            this.position.offset(0,5);

        } else {
            this.position.offset(0,-5);
        }
        setHitbox(getSprite());
    }

    /**
     *
     * @return  Returns the bullet damage which is available on power-up mode.
     */
    public int getDamageModifier() {
        return damageModifier;
    }

    /**
     *
     * @return  Returns the actual position of the bullet.
     */
    public Point getPosition() {
        return position;
    }

    /**
     *
     * @return  Returns the sprite of the bullet.
     */
    public Bitmap getSprite(){
        return sprite;
    }

    /**
     *
     * @return  Returns the path of the bullet.
     */
    public Path getBulletPath(){
        return bulletPath;
    }

    /**
     * @return Returns the hitbox of the bullet.
     */
    public Rect getHitbox(){ return hitbox; }
    /**
     *
     * @param damageModifier is the extra damage that the bullet causes.
     */
    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public void setHitbox(Bitmap sprite){
        hitbox = new Rect(this.getPosition().x, this.getPosition().y,
                this.getPosition().x + sprite.getWidth(), this.getPosition().y + sprite.getHeight());
    }

    public BulletDirection getDirection() {
        return direction;
    }
}
