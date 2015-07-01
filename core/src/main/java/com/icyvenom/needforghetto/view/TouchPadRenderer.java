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
import com.icyvenom.needforghetto.Settings;

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
    private Drawable touchBackground;
    private Drawable touchKnob;

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
                notifyObservers(1);
                System.err.print("SKJUTER SONDER!");
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                notifyObservers(0);
            }
        });

        skin.add("touchBackground", new Texture(Gdx.files.internal("images/touchBackground.png")));

        skin.add("touchKnob", new Texture(Gdx.files.internal("images/touchKnob.png")));

        touchpadStyle = new Touchpad.TouchpadStyle();

        touchBackground = skin.getDrawable("touchBackground");
        touchKnob = skin.getDrawable("touchKnob");

        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;

        touchpad = new Touchpad(10, touchpadStyle);

        touchpad.setBounds(15, 15, 200, 200);

        table.add(touchpad);
        table.add(fireButton);

        stage.addActor(table);

    }

    public Touchpad getTouchpad(){
        return touchpad;
    }

    public Stage getStage(){
        return stage;
    }

    private void setFontStyles() {
        parameter.size = (int)(screenHeight*0.02f);
        BitmapFont labelfont = generator.generateFont(parameter);
        Label.LabelStyle labelStyle = new Label.LabelStyle(labelfont, Color.WHITE);
        fireButton.getLabel().setStyle(labelStyle);

        table.getCell(fireButton).size((int) (screenWidth * 0.15f), (int) (screenHeight * 0.06f));
    }

    public void setSize(float width, float height) {
        this.screenWidth = width;
        this.screenHeight = height;
        setFontStyles();
    }
}
