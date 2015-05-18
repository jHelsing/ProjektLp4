package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Marcus on 2015-05-18.
 */
public class EnemyStalker extends Enemy{

    private int lifes = 3;

    /**
     * When a enemy is created, it's position, the hitbox and the speed are set according to
     * the parameter and variables.
     *
     * @param position  This is the given position for the enemy.
     * @param speed
     * @param weapon
     * @param direction
     */
    public EnemyStalker(Vector2 position, float speed, Weapon weapon, Vector2 direction) {
        super(position, speed, weapon, direction);
    }
}
