package com.icyvenom.needforghetto.model.test;

import com.badlogic.gdx.Game;
import com.icyvenom.needforghetto.model.enemies.EnemyPistol;
import com.icyvenom.needforghetto.model.weapons.WeaponAWP;
import com.icyvenom.needforghetto.model.World;

/**
 * A test "Game" that is needed to be able to create a new World.
 * @author Jonathan.
 * @version 1.0
 */
public class NeedForGhettoTest extends Game {

    public static World world = null;
    public static EnemyPistol enemy = null;
    public static WeaponAWP awp = null;

    @Override
    public void create () {
        world = new World();
    }
}