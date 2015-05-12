package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icyvenom.needforghetto.controller.PlayerController;
import com.icyvenom.needforghetto.model.World;
import com.icyvenom.needforghetto.model.WorldFactory;
import com.icyvenom.needforghetto.parallax.ParallaxBackground;
import com.icyvenom.needforghetto.parallax.ParallaxLayer;
import com.icyvenom.needforghetto.view.WorldRenderer;

/**
 * This class handles the graphics for the game when the game is played
 * (not when it's in the main menu).
 * @author Marcus. Revisited by Amar.
 * @version 1.05
 */
public class GameScreen implements Screen {

    /**
     * An enum that says which state the game is in.
     */
    private static enum State{
        Running, Paused
    };

    /**
     * The world model that is being played and supposed to be showed on screen.
     */
    private World world;

    /**
     * The render object.
     */
    private WorldRenderer renderer;

    /**
     * The controller for the game.
     */
    private PlayerController playerController;

    private TextureAtlas backgroundAtlas = new TextureAtlas(Gdx.files.internal("skins/testbg.atlas"));

    /**
     * The background that is moving when the game is played.
     */
    private ParallaxBackground background;

    //current state of the game, we start with a running game
    /**
     * The current state that the game is in. It will always start running.
     */
    private State state = State.Running;

    /**
     * The stage for topMenu.
     */
    private Stage stage = new Stage();

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
        // TODO: It is still killing the current screen, should we save state somehow?? maybe call pause()
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
        renderer = new WorldRenderer(world, true);
        playerController = new PlayerController(world, renderer.getCamera());

        // Load textures and other things necessary for rendering menu on the top of the screen
        loadTopMenu();
        loadBackground();
        // Adds inputprocessors to multiplexer
        inputMultiplexerPaused.addProcessor(stage);
        inputMultiplexerPaused.addProcessor(backProcessor);

        inputMultiplexerRunning.addProcessor(stage);
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
        background.render(delta);
        // if we are in a paused state we dont update model
        switch (state) {
            case Running:
                Gdx.input.setInputProcessor(inputMultiplexerRunning);
                playerController.update();
                break;
            case Paused:
                playerController.move(null); //Resets the movement of the car
                Gdx.input.setInputProcessor(inputMultiplexerPaused);
                break;
        }
        renderer.render();

        // this is for menu on top of the screen
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        renderer.setSize(width, height);
    }

    @Override
    public void pause() {
        state = State.Paused;
    }

    @Override
    public void resume() {
        state = State.Running;
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Loads the top menu. This menu is at the top of the screen and contains the number of lives
     * the player has, the play/paus button and the score that the Player has.
     */
    private void loadTopMenu() {
        Table table = new Table();
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
                new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));

        final TextButton buttonPause = new TextButton("Pause", skin);
        Label lifeStatus = new Label("Life: "+world.getPlayer().getLifes(), skin);
        Label scoreStatus = new Label("Score: 0", skin);

        table.setDebug(false);
        table.setFillParent(true);
        table.add(scoreStatus).expandX();
        table.add(lifeStatus).expandX();
        table.add(buttonPause).size(80, 50);
        table.right().top();

        buttonPause.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                switch (state) {
                    case Running:
                        state = State.Paused;
                        buttonPause.setText("Resume");
                        break;
                    case Paused:
                        state = State.Running;
                        buttonPause.setText("Pause");
                        break;
                }
            }
        });
        stage.addActor(table);
    }

    /**
     * This method loads the moving background and makes sure that it's moving.
     */
    private void loadBackground(){
        background = new ParallaxBackground(new ParallaxLayer[]{
                //new ParallaxLayer(backgroundAtlas.findRegion("road"),new Vector2(),new Vector2(0, 0)),
                new ParallaxLayer(backgroundAtlas.findRegion("road"),new Vector2(1.0f,1.0f),new Vector2(0, 0)),
                //new ParallaxLayer(backgroundAtlas.findRegion("road"),new Vector2(0.1f,1.0f),new Vector2(00, 0),new Vector2(0, 0)),
        }, 800, 480,new Vector2(0,300));
    }

    public World getWorld() {
        return world;
    }
}