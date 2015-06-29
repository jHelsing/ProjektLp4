package com.icyvenom.needforghetto.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by amar__000 on 2015-06-29.
 */
public class TouchPadRenderer {

    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton fireButton;

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
