package com.icyvenom.needforghetto.model;

/**
 * @author Anton (2015-05-05).
 * @version 1.0
 */
public class WorldFactory {

    public static World generateWorld(String playerWeapon) {
        return new World(playerWeapon);
    }

}
