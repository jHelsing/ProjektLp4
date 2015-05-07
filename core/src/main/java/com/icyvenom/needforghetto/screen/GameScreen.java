package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
import com.icyvenom.needforghetto.view.WorldRenderer;

/**
 * Created by Marcus on 2015-04-29.
 */
public class GameScreen implements Screen {

    // state of the game
    private static enum State{
        Running, Paused
    };

    private World world;
    private WorldRenderer renderer;
    private PlayerController playerController;

    //current state of the game, we start with a running game
    private State state = State.Running;

    //Stage for topMenu
    private Stage stage = new Stage();

    //We need a inputmultiplexer since we need to delegate events to diffrent inputcontrollers
    //One for Running state and one for paused state
    private InputMultiplexer inputMultiplexerRunning = new InputMultiplexer();
    private InputMultiplexer inputMultiplexerPaused = new InputMultiplexer();

    // Inputprocessor for back key since we want to override the default behaviour
    // TODO: It is still killing the current screen, should we save state somehow??
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
        renderer = new WorldRenderer(world, true);
        playerController = new PlayerController(world, renderer.getCamera());

        // Load textures and other things necessary for rendering menu on the top of the screen
        loadTopMenu();

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
        // if we are in a paused state we dont update model
        switch (state) {
            case Running:
                Gdx.input.setInputProcessor(inputMultiplexerRunning);
                playerController.update();
                break;
            case Paused:
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

    private void loadTopMenu() {
        Table table = new Table();
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
                new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));

        final TextButton buttonPause = new TextButton("Pause", skin);
        Label lifeStatus = new Label("Life: ", skin);
        Label scoreStatus = new Label("Score: ", skin);

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

}
