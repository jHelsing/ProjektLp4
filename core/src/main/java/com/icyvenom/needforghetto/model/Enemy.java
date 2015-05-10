package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * This abstract class is a superclass to all existing enemies and contains basic
 * functionality.
 * @author Marcus. Revisited by Amar.
 * @version 2.0
 */
public abstract class Enemy {

    /**
     * The speed of the enemy.
     */
    private float speed;
    /**
     * The width and the height of the enemy.
     */
    public static final float SIZE = 1f;

    /**
     * The position of the enemy.
     */
    private Vector2 position;
    /**
     * The hitbox of the enemy.
     */
    private Rectangle bounds = new Rectangle();

    /**
     *  When a enemy is created, it's position, the hitbox and the speed are set according to
     *  the parameter and variables.
     * @param position This is the given position for the enemy.
     */
    public Enemy(Vector2 position, float speed){
        this.position = position.cpy();
        this.speed=speed;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
        this.bounds.setX(this.position.x);
        this.bounds.setY(this.position.y);
    }

    /**
     * Gets the bounds of the enemy.
     * @return Returns the bounds.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Gets the position of the enemy.
     * @return Returns the position.
     */
    public Vector2 getPosition() {
        return position.cpy();
    }
}