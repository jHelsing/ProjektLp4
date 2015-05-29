package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.icyvenom.needforghetto.controller.PlayerController;
import com.icyvenom.needforghetto.gamestate.GameState;
import com.icyvenom.needforghetto.model.World;
import com.icyvenom.needforghetto.model.WorldFactory;
import com.icyvenom.needforghetto.view.BackgroundRenderer;
import com.icyvenom.needforghetto.view.StatusBarRenderer;
import com.icyvenom.needforghetto.view.WorldRenderer;

/**
 * This class handles the graphics for the game when the game is played
 * (not when it's in the main menu).
 * @author Marcus. Revisited by Amar.
 * @version 1.05
 */
public class GameScreen implements Screen {

    /**
     * The world model that is being played and supposed to be showed on screen.
     */
    private World world;

    /**
     * The render object.
     */
    private WorldRenderer worldRenderer;
    private StatusBarRenderer statusBarRenderer;
    private BackgroundRenderer backgroundRenderer;

    /**
     * The controller for the game.
     */
    private PlayerController playerController;

    //We need a inputmultiplexer since we need to delegate events to diffrent inputcontrollers
    //One for Running state and one for paused state
    /**
     * The inputmultiplexer that is used to delegate events to different inputcontrollers. This
     * one is used when the game is running.
     */
    private InputMultiplexer inputMultiplexerRunning = new InputMultiplexer();

    /**
     * The inputmultiplexer that is used to delegate events to different inputcontrollers. This
     * one is used when the game is paused.
     */
    private InputMultiplexer inputMultiplexerPaused = new InputMultiplexer();

    /**
     * The inputprocessor for the back-key on the phone. We want to override the default behavior.
     */
    private InputAdapter backProcessor = new InputAdapter() {
        @Override
        public boolean keyDown(int keycode) {
            if(keycode == Input.Keys.BACK) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new StartScreen());

            }
            return true;
        }
    };

    @Override
    public void show() {
        //World
        world = WorldFactory.getWorld();
        // true/false: debug flag
        worldRenderer = new WorldRenderer(world, true);

        statusBarRenderer = new StatusBarRenderer(world);
        backgroundRenderer = new BackgroundRenderer();

        GameState.currentState = GameState.State.RUNNING;

        playerController = new PlayerController(world, worldRenderer.getCamera());

        // Adds inputprocessors to multiplexer
        inputMultiplexerPaused.addProcessor(statusBarRenderer.getStage());
        inputMultiplexerPaused.addProcessor(backProcessor);

        inputMultiplexerRunning.addProcessor(statusBarRenderer.getStage());
        inputMultiplexerRunning.addProcessor(backProcessor);
        inputMultiplexerRunning.addProcessor(playerController);

        // setCatchBackKey needs to be true to handle back-key
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(inputMultiplexerRunning);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        backgroundRenderer.render(delta);

        if(world.getPlayer().isDead()) {
            GameOverScreen ble = new GameOverScreen(world.getPlayer().getScore());
            ((Game) Gdx.app.getApplicationListener()).setScreen(ble);
        }

        switch (GameState.currentState) {
            case RUNNING:
                Gdx.input.setInputProcessor(inputMultiplexerRunning);
                playerController.update();
                world.getSpawnTimer().start();
                break;
            case PAUSED:
                playerController.move(null); //Resets the movement of the car
                Gdx.input.setInputProcessor(inputMultiplexerPaused);
                world.getSpawnTimer().stop();
                break;
            case VICTORY:
                ((Game) Gdx.app.getApplicationListener()).setScreen(new VictoryScreen(world.getPlayer().getScore()));
        }

        worldRenderer.render();
        // this is for menu on top of the screen
        statusBarRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.setSize(width, height);
        statusBarRenderer.setSize(width, height);
    }

    @Override
    public void pause() {
        GameState.currentState = GameState.State.PAUSED;
    }

    @Override
    public void resume() {
        GameState.currentState = GameState.State.RUNNING;
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }

}