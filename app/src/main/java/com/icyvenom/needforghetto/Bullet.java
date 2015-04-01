package com.icyvenom.needforghetto;

import android.graphics.Path;
import android.graphics.Point;
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
    private ImageView sprite;
    /**
     * The bulletPath is the path of the bullet.
     */
    private Path bulletPath;

    /**
     * Moves the bullet to the next Point in the path that was given when instanced.
     */
    public void move(){
        //code here
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
    public ImageView getSprite(){
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
     *
     * @param damageModifier is the extra damage that the bullet causes.
     */
    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }
}
