package com.icyvenom.needforghetto.model.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.icyvenom.needforghetto.model.BulletDirection;
import com.icyvenom.needforghetto.model.Enemy;
import com.icyvenom.needforghetto.model.EnemySimple;
import com.icyvenom.needforghetto.model.WeaponAWP;
import com.icyvenom.needforghetto.model.World;
import com.icyvenom.needforghetto.screen.GameScreen;
import com.icyvenom.needforghetto.screen.StartScreen;

import java.util.Vector;

/**
 * A test "Game" that is needed to be able to create a new World.
 * @author Jonathan.
 * @version 1.0
 */
public class NeedForGhettoTest extends Game {

    public static World world = null;
    public static EnemySimple enemy = null;
    public static WeaponAWP awp = null;

    @Override
    public void create () {
        world = new World();
    }
}