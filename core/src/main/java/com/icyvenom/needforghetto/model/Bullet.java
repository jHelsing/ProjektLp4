package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

/**
 * This abstract class is a superclass to all existing bullets and contains basic
 * functionality.
 * @author Anton. Revisited by Amar.
 * @version 2.0
 */
public abstract class Bullet {
    /**
     * The width and the height of the bullet.
     */
    public static final float SIZE = 0.5f;
    /**
     * The position of the bullet.
     */
    private Vector2 position;
    /**
     * The velocity of the bullet.
     */
    private Vector2 velocityVector;
    /**
     * The direction of the bullet.
     */
    private BulletDirection bulletDirection;
    /**
     * The hitbox of the bullet.
     */
    private Rectangle bounds = new Rectangle();
    /**
     *  When a bullet is created, it's position, hitbox and velocity are set according to the parameter
     *  and variables.
     * @param position This is the given position for the bullet.
     */
    public Bullet(Vector2 position, Vector2 velocityVector, BulletDirection bulletDirection) {
        this.position = position.cpy();
        this.velocityVector=velocityVector.cpy();
        this.bulletDirection=bulletDirection;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
        this.bounds.setX(this.position.x);
        this.bounds.setY(this.position.y);
    }

    /**
     * Gets the bounds of the bullet.
     * @return Returns the bounds.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     *  Gets the position of the bullet.
     * @return Returns the position.
     */
    public Vector2 getPosition() {
        return position.cpy();
    }

    /**
     *  Gets the velocity of the bullet.
     * @return Returns the velocity.
     */
    public Vector2 getVelocityVector() {
        return velocityVector.cpy();
    }

    /**
     *  Sets the position of the bullet.
     * @param position This is the given position for the bullet.
     */
    public void setPosition(Vector2 position) {
        this.position = position.cpy();
        this.bounds.setX(this.position.x);
        this.bounds.setY(this.position.y);
    }

    /**
     * This method moves the bullet in a direction with a defined velocity.
     */
    public void update() {
        setPosition(getPosition().add(velocityVector.scl(bulletDirection.getDirectionValue())));
    }
}
