package com.icyvenom.needforghetto.model.enemies;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Marcus on 2015-05-18.
 */
public class EnemyAwp extends Enemy {

    /**
     * The amount of lives the enemy will start with.
     */
    private static final int LIVES = 1;

    /**
     * The speed of the enemy.
     */
    private static final float SPEED = 0.03f;

    /**
     * This is the direction of the enemy.
     */
    private static final Vector2 direction = new Vector2(0,-1f);

    /**
     * When a enemy is created, it's position, the hitbox and the speed are set according to
     * the parameter and variables.
     *
     * @param position  This is the given position for the enemy.
     */
    public EnemyAwp(Vector2 position) {
        super(position, SPEED, new com.icyvenom.needforghetto.model.weapons.WeaponAWP(Enemy.getBulletDirection()), direction);
        this.setLives(LIVES);
    }
}
