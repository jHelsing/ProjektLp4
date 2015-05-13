package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * This is the first screen that is shown when the application is first opened.
 * It basically contains a start menu. There's a button to start playing the game, exit the game,
 * change the settings of the game and to view the highscore's list.
 * @author Anton. Revisited by Amar.
 * @version 1.05
 */
public class StartScreen implements Screen{

    /**
     * Sets the stage.
     */
    private Stage stage = new Stage();

    /**
     * Creates a new table.
     */
    private Table table = new Table();

    /**
     * Sets the skin to be used.
     */
    private Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));

    /**
     * Creates a new play button that will hold the text Play.
     */
    private TextButton buttonPlay = new TextButton("Play", skin);

    /**
     * Creates a new exit button that will hold the text Exit.
     */
    private TextButton buttonExit = new TextButton("Exit", skin);

    /**
     * Creates a new settings button that will hold the text Settings.
     */
    private TextButton buttonSettings = new TextButton("Settings", skin);

    /**
     * Creates a new highscore button that will hold the text Highscore
     */
    private TextButton buttonHighscores = new TextButton("Highscore", skin);

    /**
     * Creates 3 new title label that are used to show the game title.
     */
    private Label title1 = new Label("Need", skin);
    private Label title2 = new Label("For", skin);
    private Label title3 = new Label("Ghetto", skin);


    private SpriteBatch spriteBatch = new SpriteBatch();
    private Texture bgTexture = new Texture("images/car.png");
    private Sprite bgSprite = new Sprite(bgTexture);

    public StartScreen() {

    }

    @Override
    public void show() {
        buttonPlay.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });

        buttonExit.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        buttonSettings.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        buttonHighscores.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(title1).padBottom(-10).row();
        table.add(title2).padBottom(-10).row();
        table.add(title3).padBottom(50).row();
        table.add(buttonPlay).size(150,40).padBottom(20).row();
        table.add(buttonHighscores).size(150,40).padBottom(20).row();
        table.add(buttonSettings).size(150,40).padBottom(20).row();
        table.add(buttonExit).size(150,40).padBottom(20).row();

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
        //Gdx.input.setCatchBackKey(false);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
            bgSprite.draw(spriteBatch);
        spriteBatch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

}
