package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

/**
 * This class is creating a specific bullet and contains basic
 * functionality.
 * @author Amar.
 * @version 2.0
 */
public class BulletSimple extends Bullet {
    /**
     * The velocity of the bullet.
     */
    private static final Vector2 velocityVector= new Vector2(0,0.5f);

    /**
     *  When a bullet is created, it's position, hitbox and velocity are set according to the parameter
     *  and variables.
     * @param position This is the given position for the bullet.
     */
    public BulletSimple(Vector2 position) {
        super(position, velocityVector);
    }
}
