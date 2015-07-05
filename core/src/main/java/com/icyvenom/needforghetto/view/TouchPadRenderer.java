package com.icyvenom.needforghetto.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.util.Observable;

/**
 * Created by amar__000 on 2015-06-29.
 */
public class TouchPadRenderer extends Observable {

    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton fireButton;
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Drawable touchpadBackground;
    private Drawable touchpadKnob;

    private float screenWidth;
    private float screenHeight;

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DroidSerif-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    public TouchPadRenderer(){
        this.table = new Table();
        this.stage = new Stage();
        this.skin = new Skin(Gdx.files.internal("skins/uiskin.json"),
                new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));
        fireButton = new TextButton("Fire", skin);
        fireButton.setColor(255, 255, 255, 0.35f);
        fireButton.setRound(true);

        init();
    }

    public void render() {
        stage.act();
        stage.draw();
    }

    public void init(){
        table.setDebug(false);
        table.setFillParent(true);

        fireButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setChanged();
                notifyObservers(1);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setChanged();
                notifyObservers(0);
            }
        });

        skin.add("touchpadBackground", new Texture(Gdx.files.internal("images/touchpadBackground.png")));

        skin.add("touchpadKnob", new Texture(Gdx.files.internal("images/touchpadKnob.png")));

        touchpadStyle = new Touchpad.TouchpadStyle();

        touchpadBackground = skin.getDrawable("touchpadBackground");
        touchpadKnob = skin.getDrawable("touchpadKnob");

        touchpadStyle.background = touchpadBackground;
        touchpadStyle.knob = touchpadKnob;

        touchpad = new Touchpad((int) (Gdx.graphics.getWidth() * 0.022f), touchpadStyle);

        table.left().bottom();
        table.add(touchpad);
        table.add(fireButton);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);

    }

    public Touchpad getTouchpad(){
        return touchpad;
    }

    public Stage getStage(){
        return stage;
    }

    private void setFontStyles() {
        parameter.size = (int)(screenHeight*0.04f);
        BitmapFont fireLabelFont = generator.generateFont(parameter);
        Label.LabelStyle fireLabelStyle = new Label.LabelStyle(fireLabelFont, Color.WHITE);
        fireButton.getLabel().setStyle(fireLabelStyle);

        table.getCell(fireButton).size((int) (screenWidth * 0.25f), (int) (screenHeight * 0.1f)).
                padLeft((int) (screenWidth * 0.41f)).bottom().padBottom((int) (screenHeight * 0.01f));

        table.getCell(touchpad).padLeft((int) (screenHeight * 0.01f)).padBottom((int) (screenHeight * 0.01f));

        touchpad.setBounds(7.5f, 7.5f, (int) (screenWidth * 0.3125f),  (int) (screenHeight * 0.2084f));

    }

    public void setSize(float width, float height) {
        this.screenWidth = width;
        this.screenHeight = height;
        setFontStyles();
    }
}
