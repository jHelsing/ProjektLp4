package com.icyvenom.needforghetto.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.icyvenom.needforghetto.gamestate.GameState;
import com.icyvenom.needforghetto.model.World;

/**
 * Created by anton on 2015-05-17.
 */
public class StatusBarRenderer {

    private World world;
    private Table table;
    private Skin skin;
    private Stage stage;
    private final TextButton buttonPause;

    private Label lifeStatus;
    private Label scoreStatus;

    private float screenWidth;
    private float screenHeight;

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DroidSerif-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();


    public StatusBarRenderer ( World world) {
        this.world = world;
        this.stage = new Stage();
        this.table = new Table();
        this.skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
                new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));

        buttonPause = new TextButton("Pause", skin);

        this.lifeStatus = new Label("Life: "+world.getPlayer().getLives(), skin);
        this.scoreStatus = new Label("Score: " + world.getPlayer().getScore(), skin);

        init();
    }

    public void render() {
        this.lifeStatus.setText("Life: " + world.getPlayer().getLives());
        this.scoreStatus.setText("Score: " + world.getPlayer().getScore());

        manageCurrentGamestate();

        stage.act();
        stage.draw();
    }

    private void init() {

        table.setDebug(false);
        table.setFillParent(true);
        table.add(scoreStatus).expandX();
        table.add(lifeStatus).expandX();
        table.add(buttonPause);
        table.right().top();

        buttonPause.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                switch (GameState.currentState) {
                    case RUNNING:
                        GameState.currentState = GameState.State.PAUSED;
                        break;
                    case PAUSED:
                        GameState.currentState = GameState.State.RUNNING;
                        break;
                }
            }
        });
        stage.addActor(table);
    }

    public void manageCurrentGamestate(){

        if(GameState.currentState == GameState.State.PAUSED){
            if(buttonPause.getLabel().getText().toString().equals("Pause")){
                world.getTimePointsTimer().stop();
                world.getSpawnTimer().stop();
                buttonPause.setText("Resume");
            }

        }
        else{
            if(buttonPause.getLabel().getText().toString().equals("Resume")){
                world.getTimePointsTimer().start();
                world.getSpawnTimer().start();
                buttonPause.setText("Pause");
                world.restartTimePointsTimer();
            }
        }
    }

    private void setFontStyles() {
        parameter.size = (int)(screenHeight*0.02f);
        BitmapFont labelfont = generator.generateFont(parameter);
        Label.LabelStyle labelStyle = new Label.LabelStyle(labelfont, Color.WHITE);
        lifeStatus.setStyle(labelStyle);
        scoreStatus.setStyle(labelStyle);

        buttonPause.getLabel().setStyle(labelStyle);
        table.getCell(buttonPause).size((int)(screenWidth*0.15f), (int)(screenHeight*0.06f));
    }

    public Stage getStage() {
        return stage;
    }

    public void setSize(float width, float height) {
        this.screenWidth = width;
        this.screenHeight = height;
        setFontStyles();
    }
}
