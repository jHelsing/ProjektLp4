package com.icyvenom.needforghetto.model.enemies;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Marcus on 2015-05-18.
 */
public class EnemyStalker extends Enemy {

    /**
     * The amount of lives the enemy will start with.
     */
    private static final int LIVES = 3;

    /**
     * The speed of the enemy.
     */
    private static final float SPEED = 0.04f;
    /**
     * This is the direction of the enemy.
     */
    private static final Vector2 STRAIGHT = new Vector2(0,-1f);

    private static final Vector2 LEFT = new Vector2(-0.5f,-1f);

    private static final Vector2 RIGHT = new Vector2(0.5f,-1f);


    private Vector2 goalPosition;

    /**
     * When a enemy is created, it's position, the hitbox and the speed are set according to
     * the parameter and variables.
     *
     * @param position  This is the given position for the enemy.
     */
    public EnemyStalker(Vector2 position) {
        super(position, SPEED, new com.icyvenom.needforghetto.model.weapons.WeaponNothing(), STRAIGHT);

        this.setLives(LIVES);
    }

    public void updatePosition(Vector2 playerPos){
        if (playerPos.x < this.getPosition().x) {
            setDirection(LEFT);
        } else if (playerPos.x > this.getPosition().x){
            setDirection(RIGHT);
        } else{
            setDirection(STRAIGHT);
        }
        goalPosition  = getPosition().cpy().add(getDirection().cpy().scl(SPEED));
        setPosition(goalPosition);
    }
}
