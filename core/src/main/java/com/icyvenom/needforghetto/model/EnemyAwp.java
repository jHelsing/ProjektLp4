package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Marcus on 2015-05-18.
 */
public class EnemyAwp extends Enemy {

    /**
     * The amount of lives the enemy will start with.
     */
    private static final int LIVES = 3;

    /**
     * The speed of the enemy.
     */
    private static final float SPEED = 0.01f;

    /**
     * When a enemy is created, it's position, the hitbox and the speed are set according to
     * the parameter and variables.
     *
     * @param position  This is the given position for the enemy.
     * @param direction
     */
    public EnemyAwp(Vector2 position, Vector2 direction) {
        super(position, SPEED, new WeaponAWP(Enemy.getBulletDirection()), direction);
        this.setLives(LIVES);
    }
}
