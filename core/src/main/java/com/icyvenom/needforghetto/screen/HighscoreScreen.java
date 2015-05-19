package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icyvenom.needforghetto.highscore.HighscoreManager;
import com.icyvenom.needforghetto.highscore.Score;

import java.util.ArrayList;

/**
 * Created by anton on 2015-05-13.
 */
public class HighscoreScreen implements Screen {

    private Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));
    private Label title = new Label("Highscores: ", skin);
    private ArrayList<Label> highscoreLabels = new ArrayList<Label>();
    private HighscoreManager highscoreManager;

    @Override
    public void show() {
        highscoreManager = new HighscoreManager();

        table.add(title).padBottom(10).row();

        for(Score score : highscoreManager.getScoreList()) {
            highscoreLabels.add(new Label(score.getName()+"..."+score.getScore(), skin));
        }

        for(Label label : highscoreLabels) {
            table.add(label).pad(10).row();
        }
        table.setFillParent(true);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
}
