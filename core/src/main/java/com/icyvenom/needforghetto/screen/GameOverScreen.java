package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icyvenom.needforghetto.highscore.Highscore;

/**
 * Created by anton on 2015-05-17.
 */
public class GameOverScreen implements Screen{

    private int score;
    private Table table;
    private Skin skin;
    private Stage stage;

    private Label gameOverLabel;
    private Label scoreLabel;
    private TextButton restartButton;
    private TextButton exitButton;

    private Highscore highscore;

    public GameOverScreen(int score) {
        this.score = score;
    }

    @Override
    public void show() {
        this.stage = new Stage();
        this.table = new Table();
        this.skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
                new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));

        this.gameOverLabel = new Label("Game Over!", skin);
        this.scoreLabel = new Label("Your score:" + score, skin);

        this.restartButton = new TextButton("Play again!", skin);
        this.exitButton = new TextButton("Ragequit", skin);
        init();
        if(highscore.isHighscore(score)) {
            highscoreDialog();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(stage);
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

    }

    @Override
    public void dispose() {

    }

    private void init() {
        table.setDebug(false);
        table.setFillParent(true);
        table.add(gameOverLabel).colspan(2).expandX().row();
        table.add(scoreLabel).colspan(2).expandX().row();
        table.add(restartButton).size(100, 50);
        table.add(exitButton).size(100, 50);
        table.row();


        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.err.println("restart button pressed!");
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(table);

    }

    private void highscoreDialog(){
        Input.TextInputListener dialog = new Input.TextInputListener() {
            @Override
            public void input(String text) {
                highscore.addHighscore(text, score);
            }

            @Override
            public void canceled() {

            }
        };

        Gdx.input.getTextInput(dialog, "New Highscore: "+score, "", "You name here");
    }
}
