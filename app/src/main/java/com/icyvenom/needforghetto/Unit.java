package com.icyvenom.needforghetto;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.widget.ImageView;

import java.util.Observable;
import java.util.Observer;

/**
 * @version 1.2
 * @author Amar Kulaglic. Revisited 2015-03-28 by Jonathan Helsing. Revisited 2015-03-30 by Amar Kulaglic.
 */
public abstract class Unit extends Observable {

    /**
     * The health is the creature's life.
     */
    private int health;
    /**
     * The speed is the Movement speed of the creature.
     */
    private int speed;
    /**
     * The weapon of the creature.
     */
    private Weapon weapon;
    /**
     * Position of the creature. The position is in the center of the sprite.
     */
    private Point position;
    /**
     * The sprite is the appearance of the creature.
     */
    private Bitmap sprite;

    private Rect hitbox;

    private Observer observer;

    public void addObserver(Observer observer){
        this.observer=observer;
    }


    /**
     * The damage this creature gets.
     * @param damage
     */
    public void destroy(int damage) throws GameOverException {
        health = health-damage;
        if(health<= 0)
            kill();
    }
    /**
     * Kills the creature.
     * @throws GameOverException is thrown when the game is over.
     */
    protected abstract void kill() throws GameOverException;

    /**
     * Attacks a target from the creature's weapon.
     */
    public abstract void attack();
    /**
     *
     * @return  Returns the position of the creature.
     */
    public Point getPosition(){
        return position;
    }

    /**
     *
     * @return  Returns the sprite of the creature.
     */
    public Bitmap getSprite(){
        return sprite;
    }

    /**
     *
     * @return  Returns the health of the creature.
     */
    public int getHealth(){
        return health;
    }

    /**
     *
     * @return  Returns the speed of the creature.
     */
    public int getSpeed(){
        return speed;
    }

    /**
     *
     * @return Returns the weapon of the creature.
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /*
     * @return Returns the hitbox of the creature.
     */

    public Rect getHitbox() {return hitbox; }

    /**
     *
     * @param health Sets the health of the unit.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @param sprite Sets the sprite for the unit.
     */
    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }

    /**
     *
     * @param position Sets the position of the unit.
     */
    public void setPosition(Point position){
        this.position = (new Point(position.x - getSprite().getWidth()/2, position.y - getSprite().getHeight()/2));
    }

    public void setHitbox(Bitmap Sprite){
        hitbox = new Rect(this.getPosition().x - sprite.getWidth()/2, this.getPosition().y - sprite.getHeight()/2,
                this.getPosition().x + sprite.getWidth()/2, this.getPosition().y + sprite.getHeight()/2);
    }
}
