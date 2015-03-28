package com.icyvenom.needforghetto;

import android.widget.ImageView;

/**
 * A class for the player unit. The class is a Singleton to be sure that only 1 player can exist
 * at a time. When a new game is started the method resetPlayer() will reset the Player object to
 * it's original state.
 * @version 1.0
 * @author Jonathan Helsing
 */
public class Player extends Unit {

    /**
     * The amount of lives the Player has. The Player lose 1 life if his health reach 0.
     * When the player have lost 1 life he will respawn on the map with full health but with
     * of course 1 life less.
     */
    private int nbrOfLives;

    private final static Player instance = new Player();

    private Player() {
        super();
        nbrOfLives = 3; // Default amount of Lives
        super.setHealth(1);
        super.setFireMode(FireMode.ONE_WEAPON);
        // TODO super.setSprite(new ImageView(getResource(R.drawable.)));
    }

    /**
     * Resets the Player instance. Called on when the user starts a new game.
     */
    public void resetPlayer() {
        nbrOfLives=3;
        super.setHealth(1);
        super.setFireMode(FireMode.ONE_WEAPON);
        //TODO add everything else that's added to the constructor.
    }

    /**
     * Getter for the Player instance.
     * @return The Player instance
     */

    public static Player getInstance() {
        return instance;
    }

    /**
     * Getter for the amount of lives the player still has
     * @return The number of lives remaining for the player
     */

    public int getNbrOfLives() {
        return this.nbrOfLives;
    }



}
