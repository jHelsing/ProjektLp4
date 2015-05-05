package com.icyvenom.needforghetto;


import com.badlogic.gdx.Game;
import com.icyvenom.needforghetto.screen.GameScreen;

public class NeedForGhetto extends Game {

	@Override
	public void create () {
        setScreen(new GameScreen());
	}
}
