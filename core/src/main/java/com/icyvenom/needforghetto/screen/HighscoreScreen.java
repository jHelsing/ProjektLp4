package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.icyvenom.needforghetto.highscore.HighscoreFactory;
import com.icyvenom.needforghetto.highscore.IHighscore;
import com.icyvenom.needforghetto.highscore.Score;

import java.util.ArrayList;

/**
 * Created by anton on 2015-05-13. Revisited by Amar.
 */
public class HighscoreScreen implements Screen {

    private float screenWidth;
    private float screenHeight;

    private Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));
    private Label title = new Label("Highscores ", skin);
    private ArrayList<Label> highscoreLabels = new ArrayList<Label>();
    private IHighscore highscoreManager;

    private Music sound;
    private Timer musicTimer;

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DroidSerif-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    public HighscoreScreen(Music sound, Timer musicTimer){
        this.sound=sound;
        this.musicTimer=musicTimer;
    }


    @Override
    public void show() {
        highscoreManager = HighscoreFactory.getHighscore();

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
        this.screenWidth = width;
        this.screenHeight = height;
        setFontStyles();
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
        Gdx.input.setInputProcessor(null);
        sound.stop();
        musicTimer.stop();
        stage.dispose();
        skin.dispose();
        generator.dispose();
    }

    private void setFontStyles() {
        parameter.size = (int)(screenHeight*0.03f);
        BitmapFont titlefont = generator.generateFont(parameter);
        title.setStyle(new Label.LabelStyle(titlefont, Color.WHITE));

        parameter.size = (int)(screenHeight*0.02f);
        BitmapFont scorefont = generator.generateFont(parameter);
        Label.LabelStyle scorestyle = new Label.LabelStyle(scorefont, Color.WHITE);

        for(Label l : highscoreLabels) {
            l.setStyle(scorestyle);
        }


    }

}
