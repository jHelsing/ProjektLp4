package com.icyvenom.needforghetto.model.enemies;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.icyvenom.needforghetto.model.weapons.Weapon;
import com.icyvenom.needforghetto.model.weapons.WeaponBoss;
import com.icyvenom.needforghetto.model.weapons.WeaponNineMM;
import com.icyvenom.needforghetto.model.weapons.WeaponNothing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcus on 2015-05-28.
 */
public class EnemyBoss extends Enemy {

    private static float SPEED = 0.05f;

    private List<Weapon> weapons = new ArrayList<Weapon>();

    private static final Vector2 STRAIGHT = new Vector2(0,-1f);

    private static final Vector2 LEFT = new Vector2(-0.5f, 0);

    private static final Vector2 RIGHT = new Vector2(0.5f,0);

    private Vector2 goalPosition;

    private Rectangle bounds = new Rectangle();

    private static final int LIVES = 1337;

    /**
     * When a enemy is created, it's position, the hitbox and the speed are set according to
     * the parameter and variables.
     *
     * @param position  This is the given position for the enemy.
     */
    public EnemyBoss(Vector2 position) {
        super(position, SPEED, new WeaponBoss(Enemy.getBulletDirection()), STRAIGHT);
        this.WIDTH = 8f;
        this.setBounds(WIDTH, 1f);
        this.setLives(LIVES);
    }

    @Override
    public void updatePosition(Vector2 playerPos) {
        if (getPosition().y <= 9f) {
            if (playerPos.x < this.getPosition().x + WIDTH/2 - 0.5f) {
                setDirection(LEFT);
            } else {
                setDirection(RIGHT);
            }
        }
            goalPosition = getPosition().cpy().add(getDirection().cpy().scl(SPEED));
            setPosition(goalPosition);
    }
}
