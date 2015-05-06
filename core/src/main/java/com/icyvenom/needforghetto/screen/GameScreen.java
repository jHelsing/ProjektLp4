package com.icyvenom.needforghetto.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.icyvenom.needforghetto.controller.PlayerController;
import com.icyvenom.needforghetto.model.World;
import com.icyvenom.needforghetto.view.WorldRenderer;

/**
 * Created by Marcus on 2015-04-29.
 */
public class GameScreen implements Screen {

    private World world;
    private WorldRenderer renderer;
    private PlayerController playerController;

    public GameScreen(World world) {
        this.world = world;
        renderer = new WorldRenderer(world, true);
        playerController = new PlayerController(world, renderer.getCamera());
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        playerController.update();
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        renderer.setSize(width, height);
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

    public WorldRenderer getRenderer() {
        return renderer;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }
}
