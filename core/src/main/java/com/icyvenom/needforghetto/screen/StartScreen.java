package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.icyvenom.needforghetto.gamestate.GameState;

/**
 * This is the first screen that is shown when the application is first opened.
 * It basically contains a start menu. There's a button to start a new game, exit the game,
 * change the settings of the game and to view the highscore's list.
 * @author Anton. Revisited by Amar.
 * @version 1.05
 */
public class StartScreen implements Screen{

    private float screenWidth;
    private float screenHeight;


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
     * Creates a new game button that will hold the text New game.
     */
    private TextButton buttonNewGame = new TextButton("New game", skin);

    /**
     * Creates a new exit button that will hold the text Exit.
     */
    private TextButton buttonExit = new TextButton("Exit", skin);

    /**
     * Creates a new settings button that will hold the text Settings.
     */
    private TextButton buttonCheat = new TextButton("Cheats", skin);

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

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DroidSerif-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();


    private SpriteBatch spriteBatch = new SpriteBatch();
    private Texture bgTexture = new Texture("images/bg.jpg");
    private Sprite bgSprite = new Sprite(bgTexture);

    private Music sound;
    private Timer musicTimer = new Timer();


    public StartScreen() {

    }

    @Override
    public void show() {
        buttonNewGame.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new SetUpScreen(sound,musicTimer));
            }
        });

        buttonExit.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        buttonCheat.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                Input.TextInputListener dialog = new Input.TextInputListener() {
                    @Override
                    public void input(String text) {
                        String code = text.toLowerCase();
                        if(code.equals("shall fight")) {
                            GameState.GODMODE = true;
                        }
                    }

                    @Override
                    public void canceled() {
                    }
                };

                Gdx.input.getTextInput(dialog, "Cheat", "", "Enter cheatcode");
            }
        });

        buttonHighscores.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new HighscoreScreen());
            }
        });


        table.add(title1).padBottom(-10).row();
        table.add(title2).padBottom(-10).row();
        table.add(title3).padBottom(50).row();
        table.add(buttonNewGame).size(550, 100).padBottom(20).row();
        table.add(buttonHighscores).size(550, 100).padBottom(20).row();
        table.add(buttonCheat).size(550, 100).padBottom(20).row();
        table.add(buttonExit).size(550,100).padBottom(20).row();

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(false);

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
        this.screenWidth = width;
        this.screenHeight = height;
        loadFonts();
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
        generator.dispose();
    }

    private void loadFonts() {

        // title font size is 100
        parameter.size = (int) (screenHeight * 0.09f);
        BitmapFont titlefont = generator.generateFont(parameter);

        //button font size is 50
        parameter.size = (int) (screenHeight * 0.04f);
        BitmapFont buttonfont = generator.generateFont(parameter);

        Label.LabelStyle titlestyle = new Label.LabelStyle(titlefont, Color.WHITE);
        Label.LabelStyle buttontyle = new Label.LabelStyle(buttonfont, Color.WHITE);

        title1.setStyle(titlestyle);
        title2.setStyle(titlestyle);
        title3.setStyle(titlestyle);

        buttonNewGame.getLabel().setStyle(buttontyle);
        buttonHighscores.getLabel().setStyle(buttontyle);
        buttonCheat.getLabel().setStyle(buttontyle);
        buttonExit.getLabel().setStyle(buttontyle);

        table.getCell(buttonNewGame).size((int)(screenWidth*0.8f),(int)(screenHeight*0.09f));
        table.getCell(buttonHighscores).size((int)(screenWidth*0.8f),(int)(screenHeight*0.09f));
        table.getCell(buttonCheat).size((int)(screenWidth*0.8f),(int)(screenHeight*0.09f));
        table.getCell(buttonExit).size((int)(screenWidth*0.8f),(int)(screenHeight*0.09f));
    }

}
