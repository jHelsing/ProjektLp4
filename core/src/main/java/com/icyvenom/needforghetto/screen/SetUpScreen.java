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

/**
 * This is the screen before the game starts to have the opportunity to change the player's property.
 * For example, change weapons and change car color etc.
 * @author Amar.
 * @version 1.0
 */
public class SetUpScreen implements Screen {

    private float screenWidth;
    private float screenHeight;

    private Stage stage = new Stage();

    private Table table = new Table();

    private Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DroidSerif-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    private Label title = new Label("Set-up game ", skin);

    private Music sound;
    private Timer musicTimer;

    public SetUpScreen(Music sound, Timer musicTimer){
        this.sound=sound;
        this.musicTimer=musicTimer;
    }

    @Override
    public void show() {
        table.add(title).row();
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
        musicTimer.stop();
        sound.stop();
        stage.dispose();
        skin.dispose();
        generator.dispose();

    }

    private void setFontStyles() {
        parameter.size = (int)(screenHeight*0.07f);
        BitmapFont titlefont = generator.generateFont(parameter);
        title.setStyle(new Label.LabelStyle(titlefont, Color.WHITE));

        parameter.size = (int)(screenHeight*0.02f);
        BitmapFont textFont = generator.generateFont(parameter);
        Label.LabelStyle textStyle = new Label.LabelStyle(textFont, Color.WHITE);

    }
}