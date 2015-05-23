package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private Label title = new Label("Highscores ", skin);
    private ArrayList<Label> highscoreLabels = new ArrayList<Label>();
    private HighscoreManager highscoreManager;

    @Override
    public void show() {
        highscoreManager = new HighscoreManager();

        table.add(title).padBottom(15).row();
        int rank=1;
        String suffix;
        highscoreLabels.add(new Label("Rank" + "        " + "Score" + "         " + "Name", skin));
        for(Score score : highscoreManager.getScoreList()) {
            if(rank==1){
                suffix="st";
            }
            else if(rank==2){
                suffix="nd";
            }
            else if(rank==3){
                suffix="rd";
            }
            else{
                suffix="th";
            }
            highscoreLabels.add(new Label( rank + suffix + "           "+score.getScore()+"             "+score.getName(), skin));
            rank++;
        }

        for(Label label : highscoreLabels) {
            table.add(label).pad(5).row();
        }
        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            Gdx.input.setCatchBackKey(false);
            ((Game)Gdx.app.getApplicationListener()).setScreen(new StartScreen());
        }

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
