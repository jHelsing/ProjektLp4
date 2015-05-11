package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

/**
 * This class is the bullet that belongs to WeaponNineMM.
 * @author Jonathan
 * @version 1.0
 */
public class BulletNineMM extends Bullet {

    /**
     * The velocity of the bullet.
     */
    private static final Vector2 VELOCITY_VECTOR = new Vector2(0,0.1f);

    /**
     *  When a bullet is created, it's position, hitbox and velocity are set according to the parameter
     *  and variables.
     * @param position This is the given position for the bullet.
     */
    public BulletNineMM(Vector2 position, BulletDirection bulletDirection) {
        super(position, VELOCITY_VECTOR.cpy(), bulletDirection);
    }
}
