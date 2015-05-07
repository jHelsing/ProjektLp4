package com.icyvenom.needforghetto;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.icyvenom.needforghetto.controller.PlayerController;
import com.icyvenom.needforghetto.model.World;
import com.icyvenom.needforghetto.model.WorldFactory;
import com.icyvenom.needforghetto.screen.GameScreen;
import com.icyvenom.needforghetto.screen.StartScreen;

public class NeedForGhetto extends Game {

	@Override
	public void create () {
        setScreen(new StartScreen());
    }
}
