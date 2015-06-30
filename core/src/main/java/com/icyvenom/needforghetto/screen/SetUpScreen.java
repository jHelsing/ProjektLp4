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

    private Label title = new Label("Set-up game", skin);
    private Label chooseWeaponLabel = new Label("Choose a weapon", skin);
    private TextButton leftWeaponArrow = new TextButton("<", skin);
    private Label weaponLabel = new Label("Walther PP", skin);
    private TextButton rightWeaponArrow = new TextButton(">", skin);

    private Label chooseCarColorLabel = new Label("Choose a car color", skin);
    private TextButton leftCarColorArrow = new TextButton("<", skin);
    private Label carColorLabel = new Label("Black", skin);
    private TextButton rightCarColorArrow = new TextButton(">", skin);

    private TextButton playButton = new TextButton("Play", skin);

    private Music sound;
    private Timer musicTimer;

    public SetUpScreen(Music sound, Timer musicTimer){
        this.sound=sound;
        this.musicTimer=musicTimer;
    }

    public SetUpScreen(){
        this.musicTimer = new Timer();
        sound = Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));
        sound.play();
        musicTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                sound.stop();
                sound.play();
            }
        }, 0, 120, 120);
    }

    @Override
    public void show() {

        leftWeaponArrow.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                switch (weaponLabel.getText().toString()) {
                    case "Walther PP":
                        weaponLabel.setText("AWP");
                        break;
                    case "M4A1":
                        weaponLabel.setText("Walther PP");
                        break;
                    case "AWP":
                        weaponLabel.setText("M4A1");
                        break;
                }
            }
        });

        rightWeaponArrow.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                switch (weaponLabel.getText().toString()) {
                    case "Walther PP":
                        weaponLabel.setText("M4A1");
                        break;
                    case "M4A1":
                        weaponLabel.setText("AWP");
                        break;
                    case "AWP":
                        weaponLabel.setText("Walther PP");
                        break;
                }
            }
        });

        leftCarColorArrow.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                switch (carColorLabel.getText().toString()) {
                    case "Black":
                        carColorLabel.setText("White");
                        break;
                    case "White":
                        carColorLabel.setText("Black");
                        break;
                }
            }
        });

        rightCarColorArrow.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                switch (carColorLabel.getText().toString()) {
                    case "Black":
                        carColorLabel.setText("White");
                        break;
                    case "White":
                        carColorLabel.setText("Black");
                        break;
                }
            }
        });

        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(weaponLabel.
                        getText().toString(), carColorLabel.getText().toString()));
            }
        });

        weaponLabel.setAlignment(Align.center);
        carColorLabel.setAlignment(Align.center);

        table.add(title).colspan(3).center();
        table.row();
        table.add(chooseWeaponLabel).colspan(3).center();
        table.row();
        table.add(leftWeaponArrow);
        table.add(weaponLabel);
        table.add(rightWeaponArrow);
        table.row();
        table.add(chooseCarColorLabel).colspan(3).center();
        table.row();
        table.add(leftCarColorArrow);
        table.add(carColorLabel);
        table.add(rightCarColorArrow);
        table.row();
        table.add(playButton).colspan(3).center();

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
        parameter.size = (int)(screenHeight*0.07f);
        BitmapFont titleFont = generator.generateFont(parameter);
        Label.LabelStyle titleStyle = new Label.LabelStyle(titleFont, Color.WHITE);
        title.setStyle(titleStyle);

        parameter.size = (int)(screenHeight*0.04f);
        BitmapFont headingFont = generator.generateFont(parameter);
        Label.LabelStyle headingStyle = new Label.LabelStyle(headingFont, Color.WHITE);
        chooseWeaponLabel.setStyle(headingStyle);
        chooseCarColorLabel.setStyle(headingStyle);

        parameter.size = (int)(screenHeight*0.03f);
        BitmapFont textFont = generator.generateFont(parameter);
        Label.LabelStyle textStyle = new Label.LabelStyle(textFont, Color.WHITE);
        leftWeaponArrow.getLabel().setStyle(titleStyle);
        leftCarColorArrow.getLabel().setStyle(titleStyle);
        weaponLabel.setStyle(textStyle);
        carColorLabel.setStyle(textStyle);
        rightWeaponArrow.getLabel().setStyle(titleStyle);
        rightCarColorArrow.getLabel().setStyle(titleStyle);

        playButton.getLabel().setStyle(headingStyle);

        table.getCell(playButton).size((int) (screenWidth * 0.61f), (int) (screenHeight * 0.084f)).
                padTop((int) (screenHeight * 0.167f));
        table.getCell(leftWeaponArrow).width((int) (screenWidth * 0.125f));
        table.getCell(leftCarColorArrow).width((int) (screenWidth * 0.125f));
        table.getCell(rightWeaponArrow).width((int) (screenWidth * 0.125f));
        table.getCell(rightCarColorArrow).width((int)(screenWidth * 0.125f));
        table.getCell(weaponLabel).width((int)(screenWidth * 0.344f));
        table.getCell(carColorLabel).width((int)(screenWidth * 0.344f));
        table.getCell(title).padBottom((int)(screenHeight * 0.125f));
        table.getCell(chooseWeaponLabel).padBottom((int)(screenHeight * 0.021f));
        table.getCell(chooseCarColorLabel).padBottom((int)(screenHeight * 0.021f)).
                padTop((int)(screenHeight * 0.105f));

    }
}
