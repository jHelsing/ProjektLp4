package com.icyvenom.needforghetto.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

    public StatusBarRenderer ( World world) {
        this.world = world;
        this.stage = new Stage();
        this.table = new Table();
        this.skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
                new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));

        buttonPause = new TextButton("Pause", skin);

        this.lifeStatus = new Label("Life: "+world.getPlayer().getLifes(), skin);
        this.scoreStatus = new Label("Score: " + world.getPlayer().getScore(), skin);

        init();
    }

    public void render() {
        this.lifeStatus.setText("Life: " + world.getPlayer().getLifes());
        this.scoreStatus.setText("Score: " + world.getPlayer().getScore());

        stage.act();
        stage.draw();
    }

    private void init() {

        table.setDebug(false);
        table.setFillParent(true);
        table.add(scoreStatus).expandX();
        table.add(lifeStatus).expandX();
        table.add(buttonPause).size(80, 50);
        table.right().top();

        buttonPause.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                /*switch (state) {
                    case Running:
                        state = State.Paused;
                        buttonPause.setText("Resume");
                        world.getTimePointsTimer().stop();
                        break;
                    case Paused:
                        state = State.Running;
                        buttonPause.setText("Pause");
                        world.getTimePointsTimer().start();
                        break;
                }*/
               System.err.println("pause button pressed!");
            }
        });
        stage.addActor(table);
    }

    public Stage getStage() {
        return stage;
    }
}
