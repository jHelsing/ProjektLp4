package com.icyvenom.needforghetto.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.icyvenom.needforghetto.model.Bullet;
import com.icyvenom.needforghetto.model.Player;
import com.icyvenom.needforghetto.model.World;

/**
 * This is the controller for the game.
 * @author Marcus. Revisited by Amar.
 * @version 1.0
 */
public class PlayerController implements InputProcessor{

    /**
     * The world object.
     */
    World world;

    /**
     * The player object.
     */
    Player player;

    /**
     * This is the OrthographicCamera object.
     * It is needed to convert from pixel to unit.
     */
    OrthographicCamera camera;

    /**
     * The constructor for a new controller to control the given world-model.
     * @param world The world-model to control.
     * @param camera The camera to be used.
     */
    public PlayerController(World world, OrthographicCamera camera){
        this.world = world;
        this.player = world.getPlayer();
        this.camera = camera;
    }

    /**
     * The move method for the player. Gives the player a velocity.
     * @param vec The vector that the player should move in.
     */
    public void move(Vector2 vec){
        player.setVelocity(vec);
    }

    /**
     * Updates the models and makes the necessary stuff to draw the next frame.
     */
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
        player.fire();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //TODO: implement fireStop(); this should stop the timer for bullets
        player.stopFire();
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