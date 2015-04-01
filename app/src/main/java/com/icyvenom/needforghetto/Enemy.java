package com.icyvenom.needforghetto;

import android.graphics.Path;

/**
 * @author Amar Kulaglic
 * @version 1.0
 */
public abstract class Enemy extends Unit{
    /**
     * The movePath is the path of all the movements that the enemy will take.
     */
    private Path movePath;

    /**
     * Attacks the player from the enemy's weapon.
     */
    @Override
    public void attack(){
        //Code here.
    }

    /**
     * Moves the enemy in a specific path.
     */
    public void move(){
        //Code here.
    }

    /**
     * Kills the enemy.
     */
    @Override
    protected void kill(){
        this.notifyObservers();
    }

    /**
     * Getter for the path of all the movements that the enemy will take.
     * @return  Returns the path of the enemy.
     */
    public Path getMovePath() {
        return movePath;
    }
}
