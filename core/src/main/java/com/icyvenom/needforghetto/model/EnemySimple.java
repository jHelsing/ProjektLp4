package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

/**
 * This class is creating a specific enemy and contains basic
 * functionality.
 * @author Amar.
 * @version 2.0
 */
public class EnemySimple extends Enemy {
    /**
     * The speed of the enemy.
     */
    private static final float SPEED = 0.005f;

    private static final Vector2 direction = new Vector2(0,-1f);

    /**
     *  When a enemy is created, it's position, the hitbox and the speed are set according to
     *  the parameter and variables.
     * @param position This is the given position for the enemy.
     */
    public EnemySimple(Vector2 position){
        super(position, SPEED, new WeaponNineMM(Enemy.getBulletDirection()), direction);
    }
}
