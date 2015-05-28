package com.icyvenom.needforghetto.model.enemies;

import com.badlogic.gdx.math.Vector2;
import com.icyvenom.needforghetto.model.weapons.WeaponNineMM;

/**
 * This class is creating a specific enemy and contains basic
 * functionality.
 * @author Amar.
 * @version 2.0
 */
public class EnemyPistol extends Enemy {
    /**
     * The speed of the enemy.
     */
    private static final float SPEED = 0.05f;

    /**
     * This is the direction of the enemy.
     */
    private static final Vector2 direction = new Vector2(0,-1f);

    /**
     * The amount of lives the enemy will start with.
     */
    private static final int LIVES = 1;

    /**
     *  When a enemy is created, it's position, the hitbox and the speed are set according to
     *  the parameter and variables.
     * @param position This is the given position for the enemy.
     */
    public EnemyPistol(Vector2 position){
        super(position, SPEED, new WeaponNineMM(Enemy.getBulletDirection()), direction);

        this.setLives(LIVES);
    }
}
