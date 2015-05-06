package com.icyvenom.needforghetto;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.icyvenom.needforghetto.controller.PlayerController;
import com.icyvenom.needforghetto.model.World;
import com.icyvenom.needforghetto.model.WorldFactory;
import com.icyvenom.needforghetto.screen.GameScreen;

public class NeedForGhetto extends Game {

	@Override
	public void create () {
        World world = WorldFactory.getWorld();
        GameScreen gameScreen = new GameScreen(world);
        setScreen(gameScreen);
        Gdx.input.setInputProcessor(gameScreen.getPlayerController());
    }
}
