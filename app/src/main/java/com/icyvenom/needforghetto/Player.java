package com.icyvenom.needforghetto;

/**
 * A class for the player unit.
 *
 * @author Jonathan Helsing
 */
public class Player extends Unit {

    private int nbrOfLives;

    private final static Player instance = new Player();

    private Player() {
        super();
        nbrOfLives = 3; // Default amount of Lives
        super.setHealth(1);
        super.setFireMode(FireMode.ONE_WEAPON);
        super.
    }



    public static Player getInstance() {
        return instance;
    }



}
