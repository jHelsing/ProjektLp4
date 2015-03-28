package com.icyvenom.needforghetto;

import android.graphics.Point;
import android.widget.ImageView;

import java.util.Observable;
import java.util.Observer;

/**
 * @version 1.1
 * @author Amar Kulaglic. Revisited 2015-03-28 by Jonathan Helsing
 */
public abstract class Unit extends Observable {

    public enum FireMode {
        /**
         * Different way of firing a weapon.
         */
        ONE_WEAPON,TWO_WEAPONS,THREE_WEAPONS;

    }

    /**
     * The health is the creature's life.
     */
    private int health;
    /**
     * The speed is the Movement speed of the creature.
     */
    private int speed;
    /**
     * Position of the creature. The position is in the center of the sprite.
     */
    private Point position;
    /**
     * The sprite is the appearance of the creature.
     */
    private ImageView sprite;
    /**
     * The fire mode is how the creature is firing a weapon.
     */
    private FireMode fireMode;
    private Observer observer;

    public Unit(){}

    public void addObserver(Observer observer){
        this.observer=observer;
    }


    /**
     * The damage this creature gets.
     * @param damage
     */
    public void destroy(int damage) {
        health = health-damage;
        if(health<= 0)
            kill();
    }
    /**
     * Kills the creature.
     */
    private void kill(){
        this.notifyObservers();
    }
    /**
     *
     * @return  Returns the position of the creature.
     */
    public Point getPosition(){
        return position;
    }

    /**
     *
     * @return  Returns the fire mode of the creature.
     */
    public FireMode getFireMode(){
        return fireMode;
    }

    /**
     *
     * @return  Returns the sprite of the creature.
     */
    public ImageView getSprite(){
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
     * @param health Sets the health of the unit.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @param mode Sets the FireMode enum for the unit.
     */
    public void setFireMode(FireMode mode) {
        this.fireMode = mode;
    }

    /**
     *
     * @param sprite Sets the sprite for the unit.
     */
    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    /**
     *
     * @param position Sets the position of the unit.
     */
    public void setPosition(Point position){
        this.position=position;
    }
}
