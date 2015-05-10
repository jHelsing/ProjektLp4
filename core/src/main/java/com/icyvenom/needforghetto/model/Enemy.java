package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * Created by Marcus on 2015-04-29.
 */
public class Enemy {

    static final float SPEED = 2f;
    public static final float SIZE = 1f;

    Vector2 position = new Vector2();
    Rectangle bounds = new Rectangle();

    public Enemy(Vector2 position){
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
        this.bounds.setX(position.x);
        this.bounds.setY(position.y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }
}