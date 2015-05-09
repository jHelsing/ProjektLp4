package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;


/**
 * Created by Marcus on 2015-04-29.
 */
public class Player {
    static final float SPEED = 0.5f;
    public static final float SIZE = 1f;
    public static final Vector2 DEFAULTPOSITION = new Vector2(4.5f, 1f);

    private int lifes = 3;

    Vector2 position = new Vector2();
    Vector2 goalPosition = new Vector2();
    Vector2 acceleration = new Vector2();
    Vector2 velocity = new Vector2(0,0);
    Rectangle bounds = new Rectangle();
    Weapon weapon = new Weapon();

    public Player(Vector2 position){
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
    public void setPosition(Vector2 position){
        // this messes with my physics, comment out for now
        //this.position = new Vector2(position.x - SIZE/2, position.y - SIZE/5);
        this.position = position;
        this.bounds.setX(this.position.x);
        this.bounds.setY(this.position.y);
    }

    public boolean isColliding (Rectangle object){
        if (bounds.overlaps(object)){
            kill();
        }
        return (bounds.overlaps(object));
    }

    public void kill(){
        lifes--;
        setPosition(DEFAULTPOSITION);
        goalPosition.set(DEFAULTPOSITION);
        System.err.println(lifes);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void fire() {
        weapon.addBullet(this.position.cpy());
    }

    public int getLifes() {
        return lifes;
    }

    //Updates position, if we reached our goalPos we stop moving
    // Otherwise we update our position accordingly
    public void updatePosition() {
        if(position.epsilonEquals(goalPosition, 0.6f)) {
            velocity.x = 0;
            velocity.y = 0;
        }
        setPosition(getPosition().cpy().add(velocity.cpy().scl(SPEED)));
    }

    // physics says goalPos - currentPos = velocity
    public void setVelocity(Vector2 goalPos) {
        goalPosition = goalPos;
        this.velocity = goalPos.cpy().sub(getPosition());
        this.velocity.nor();
    }
}
