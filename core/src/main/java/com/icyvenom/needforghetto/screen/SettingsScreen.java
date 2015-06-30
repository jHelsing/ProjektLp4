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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by amar__000 on 2015-06-29.
 */
public class SettingsScreen implements Screen {

    private float screenWidth;
    private float screenHeight;

    private Stage stage = new Stage();

    private Table table = new Table();

    private Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DroidSerif-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    private Label title = new Label("Settings", skin);
    private Label chooseControllerLabel = new Label("Choose controller", skin);
    private TextButton leftControllerArrow = new TextButton("<", skin);
    private Label controllerLabel = new Label("Drag", skin);
    private TextButton rightControllerArrow = new TextButton(">", skin);

    private Music sound;
    private Timer musicTimer;

    public SettingsScreen(Music sound, Timer musicTimer){
        this.sound=sound;
        this.musicTimer=musicTimer;
    }
    @Override
    public void show() {
        leftControllerArrow.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                switch (controllerLabel.getText().toString()) {
                    case "Drag":
                        controllerLabel.setText("Touchpad");
                        break;
                    case "Touchpad":
                        controllerLabel.setText("Drag");
                        break;
                }
            }
        });

        rightControllerArrow.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                switch (controllerLabel.getText().toString()) {
                    case "Drag":
                        controllerLabel.setText("Touchpad");
                        break;
                    case "Touchpad":
                        controllerLabel.setText("Drag");
                        break;
                }
            }
        });

        controllerLabel.setAlignment(Align.center);

        table.add(title).colspan(3).center();
        table.row();
        table.add(chooseControllerLabel).colspan(3).center();
        table.row();
        table.add(leftControllerArrow);
        table.add(controllerLabel);
        table.add(rightControllerArrow);

        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
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
        parameter.size = (int) (screenHeight * 0.07f);
        BitmapFont titleFont = generator.generateFont(parameter);
        Label.LabelStyle titleStyle = new Label.LabelStyle(titleFont, Color.WHITE);
        title.setStyle(titleStyle);
        leftControllerArrow.getLabel().setStyle(titleStyle);
        rightControllerArrow.getLabel().setStyle(titleStyle);

        parameter.size = (int) (screenHeight * 0.04f);
        BitmapFont headingFont = generator.generateFont(parameter);
        Label.LabelStyle headingStyle = new Label.LabelStyle(headingFont, Color.WHITE);
        chooseControllerLabel.setStyle(headingStyle);

        parameter.size = (int)(screenHeight*0.03f);
        BitmapFont textFont = generator.generateFont(parameter);
        Label.LabelStyle textStyle = new Label.LabelStyle(textFont, Color.WHITE);

        controllerLabel.setStyle(textStyle);

        table.getCell(leftControllerArrow).width((int) (screenWidth * 0.125f));
        table.getCell(rightControllerArrow).width((int) (screenWidth * 0.125f));
        table.getCell(controllerLabel).width((int)(screenWidth * 0.344f));
        table.getCell(title).padBottom((int) (screenHeight * 0.084f));
        table.getCell(chooseControllerLabel).padBottom((int) (screenHeight * 0.021f));
    }
}
