package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by anton on 2015-04-30.
 */
public class Bullet {

    public static final float SIZE = 0.5f;

    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 direction = new Vector2();

    private Rectangle bounds = new Rectangle();

    public Bullet(Vector2 position) {
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

    public void setPosition(Vector2 position) {
        this.position = position;
        this.bounds.setX(this.position.x);
        this.bounds.setY(this.position.y);
    }

    public boolean isColliding (Rectangle object){
        return (bounds.overlaps(object));
    }
}
