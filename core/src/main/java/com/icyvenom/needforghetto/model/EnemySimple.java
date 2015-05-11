package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

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
    private static final float SPEED=2f;
    /**
     * The speed of the enemy.
     */
    private static final Weapon weapon = new WeaponNineMM(Enemy.getBulletDirection());

    /**
     *  When a enemy is created, it's position, the hitbox and the speed are set according to
     *  the parameter and variables.
     * @param position This is the given position for the enemy.
     */
    public EnemySimple(Vector2 position){
        super(position, SPEED, weapon);
    }
}
