package com.icyvenom.needforghetto.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.icyvenom.needforghetto.NeedForGhetto;
import com.icyvenom.needforghetto.model.Bullet;
import com.icyvenom.needforghetto.model.Player;
import com.icyvenom.needforghetto.model.World;

/**
 * Created by Marcus on 2015-04-29.
 */
public class PlayerController implements InputProcessor{

    World world;
    Player player;

    //this is needed to convert from pixel to unit
    OrthographicCamera camera;

    public PlayerController(World world, OrthographicCamera camera){
        this.world = world;
        this.player = world.getPlayer();
        this.camera = camera;
    }

    public void move(Vector2 vec){
        player.setVelocity(vec);
    }

    public void fire(){ player.fire(); }

    public void update(){
        world.checkCollision();
        if(world.getPlayer().getLifes() <= 0) {
            // TODO: call method gameOver() and stop enemies from spawning and show gameover screen
            //System.err.println("Game Over");
        }
        for(Bullet b : player.getWeapon().getBullets()) {
            b.update();
        }
        world.getPlayer().updatePosition();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 pos = camera.unproject(new Vector3(screenX, screenY, 0));
        move(new Vector2(pos.x, pos.y));

        //fireStart();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //TODO: implement fireStop(); this should stop the timer for bullets
        //fireStop();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 pos = camera.unproject(new Vector3(screenX, screenY, 0));
        move(new Vector2(pos.x, pos.y));
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
