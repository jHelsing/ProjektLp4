package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 2015-04-30.
 */
public class Weapon {

    private int damage;
    private int attackRate;
    private Vector2 position = new Vector2();

    // bullet?
    private List<Bullet> bullets = new ArrayList<Bullet>();

    Weapon() {

    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void addBullet(Vector2 position) {
        bullets.add(new Bullet(position));
    }


}
