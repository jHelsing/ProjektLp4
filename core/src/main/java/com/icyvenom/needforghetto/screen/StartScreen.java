package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.icyvenom.needforghetto.model.WorldFactory;

/**
 * Created by anton on 2015-05-07.
 */
public class StartScreen implements Screen{

    Stage stage = new Stage();
    Table table = new Table();

    Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));

    TextButton buttonPlay = new TextButton("Play", skin);
    TextButton buttonExit = new TextButton("Exit", skin);
    TextButton buttonSettings = new TextButton("Settings", skin);
    TextButton buttonHighscores = new TextButton("Highscores", skin);

    Label title1 = new Label("Need", skin);
    Label title2 = new Label("For", skin);
    Label title3 = new Label("Ghetto", skin);


    SpriteBatch spriteBatch = new SpriteBatch();
    Texture bgTexture = new Texture("images/car.png");
    Sprite bgSprite = new Sprite(bgTexture);

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
