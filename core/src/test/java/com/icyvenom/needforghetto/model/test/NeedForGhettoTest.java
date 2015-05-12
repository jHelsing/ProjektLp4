package com.icyvenom.needforghetto.model.test;

import com.badlogic.gdx.Game;
import com.icyvenom.needforghetto.model.World;
import com.icyvenom.needforghetto.screen.GameScreen;
import com.icyvenom.needforghetto.screen.StartScreen;

/**
 * Created by Jonathan on 2015-05-12.
 */
public class NeedForGhettoTest extends Game {

    public static World world = null;

    @Override
    public void create () {
        world = new World();
    }
}
