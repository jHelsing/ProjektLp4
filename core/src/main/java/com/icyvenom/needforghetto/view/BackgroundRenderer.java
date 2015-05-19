package com.icyvenom.needforghetto.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.icyvenom.needforghetto.gamestate.GameState;
import com.icyvenom.needforghetto.parallax.ParallaxBackground;
import com.icyvenom.needforghetto.parallax.ParallaxLayer;

/**
 * Created by anton on 2015-05-17.
 */
public class BackgroundRenderer {

    private TextureAtlas backgroundAtlas;
    private ParallaxBackground background;

    public BackgroundRenderer() {
        backgroundAtlas = new TextureAtlas(Gdx.files.internal("skins/testbg.atlas"));

        background = new ParallaxBackground(new ParallaxLayer[]{
                //new ParallaxLayer(backgroundAtlas.findRegion("road"),new Vector2(),new Vector2(0, 0)),
                new ParallaxLayer(backgroundAtlas.findRegion("road"),new Vector2(1.0f,1.0f),new Vector2(0, 0)),
                //new ParallaxLayer(backgroundAtlas.findRegion("road"),new Vector2(0.1f,1.0f),new Vector2(00, 0),new Vector2(0, 0)),
        }, 800, 480,new Vector2(0,300));
    }

    public void render(float delta) {
        switch (GameState.currentState) {
            case RUNNING:
                background.setSpeed(0, 300);
                break;
            case PAUSED:
                background.setSpeed(0,0);
                break;
        }
        this.background.render(delta);

    }
}
