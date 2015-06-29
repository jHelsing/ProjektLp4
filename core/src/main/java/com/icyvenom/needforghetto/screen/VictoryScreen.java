package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import com.icyvenom.needforghetto.highscore.HighscoreFactory;
import com.icyvenom.needforghetto.highscore.IHighscore;
import com.icyvenom.needforghetto.highscore.Score;

/**
 * Created by anton on 2015-05-29.
 */
public class VictoryScreen implements Screen {

    private float screenWidth;
    private float screenHeight;

    private int score;
    private Table table;
    private Skin skin;
    private Stage stage;

    private String playerWeapon;
    private String playerCarColor;

    private Label gameOverLabel;
    private Label scoreLabel;
    private TextButton restartButton;
    private TextButton newGameButton;
    private TextButton returnButton;

    private IHighscore highscoreManager;

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DroidSerif-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    public VictoryScreen(int score, String playerWeapon, String playerCarColor) {
        this.score = score;
        this.playerWeapon = playerWeapon;
        this.playerCarColor = playerCarColor;
    }

    @Override
    public void show() {
        this.highscoreManager = HighscoreFactory.getHighscore();
        this.stage = new Stage();
        this.table = new Table();
        this.skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
                new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));

        this.gameOverLabel = new Label("Level cleared!", skin);
        this.scoreLabel = new Label("Your score:" + score, skin);

        this.restartButton = new TextButton("Replay", skin);
        this.newGameButton = new TextButton("New game", skin);
        this.returnButton = new TextButton("Return to main menu", skin);
        init();
        if(highscoreManager.isHighscore(score)) {
            highscoreDialog();
        }
        Gdx.input.setCatchBackKey(false);
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

    }

    @Override
    public void dispose() {

    }

    private void init() {
        table.setDebug(false);
        table.setFillParent(true);
        table.add(gameOverLabel).colspan(2).expandX().row();
        table.add(scoreLabel).padBottom(40).colspan(2).expandX().row();
        table.add(restartButton).size(100, 50).padBottom(20).colspan(3).center().row();
        table.add(newGameButton).size(100, 50).padBottom(20).colspan(3).center().row();
        table.add(returnButton).size(100, 50).colspan(3).center();
        table.row();


        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.err.println("restart button pressed!");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(playerWeapon, playerCarColor));
            }
        });

        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new SetUpScreen());
            }
        });

        returnButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new StartScreen());
            }
        });

        stage.addActor(table);

    }

    private void highscoreDialog(){
        Input.TextInputListener dialog = new Input.TextInputListener() {
            @Override
            public void input(String text) {
                highscoreManager.addHighscore(new Score(text, score));
            }

            @Override
            public void canceled() {
                highscoreManager.addHighscore(new Score("AAA", score));
            }
        };

        Gdx.input.getTextInput(dialog, "New Highscore: "+score, null, "Your name here");
    }

    private void setFontStyles() {
        parameter.size = (int)(screenHeight*0.03f);
        BitmapFont titlefont = generator.generateFont(parameter);
        gameOverLabel.setStyle(new Label.LabelStyle(titlefont, Color.WHITE));
        scoreLabel.setStyle(new Label.LabelStyle(titlefont, Color.WHITE));

        parameter.size = (int)(screenHeight*0.02f);
        BitmapFont buttonfont = generator.generateFont(parameter);

        returnButton.getLabel().setStyle(new Label.LabelStyle(buttonfont, Color.WHITE));
        restartButton.getLabel().setStyle(new Label.LabelStyle(buttonfont, Color.WHITE));
        newGameButton.getLabel().setStyle(new Label.LabelStyle(buttonfont, Color.WHITE));

        table.getCell(newGameButton).size((int) (screenWidth * 0.4f), (int) (screenHeight * 0.09f));
        table.getCell(returnButton).size((int) (screenWidth * 0.4f), (int) (screenHeight * 0.09f));
        table.getCell(restartButton).size((int)(screenWidth*0.4f),(int)(screenHeight*0.09f));
    }

}
