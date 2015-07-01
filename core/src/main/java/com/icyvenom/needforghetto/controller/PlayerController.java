package com.icyvenom.needforghetto.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.icyvenom.needforghetto.Settings;
import com.icyvenom.needforghetto.model.bullets.Bullet;
import com.icyvenom.needforghetto.model.enemies.Enemy;
import com.icyvenom.needforghetto.model.Player;
import com.icyvenom.needforghetto.model.World;
import com.icyvenom.needforghetto.view.TouchPadRenderer;

import java.util.Observable;
import java.util.Observer;

/**
 * This is the controller for the game.
 * @author Marcus. Revisited by Amar.
 * @version 1.0
 */
public class PlayerController implements InputProcessor, Observer{

    /**
     * The world object.
     */
    World world;

    /**
     * The player object.
     */
    Player player;

    private TouchPadRenderer touchpadRenderer;

    /**
     * This is the OrthographicCamera object.
     * It is needed to convert from pixel to unit.
     */
    OrthographicCamera camera;

    private boolean dragControltypeOn;

    private boolean touchControltypeOn;

    /**
     * The constructor for a new controller to control the given world-model.
     * @param world The world-model to control.
     * @param camera The camera to be used.
     */

    /**
     * Vector used to stop movement when finger leaves screen.
     */
    private boolean move = false;

    public PlayerController(World world, OrthographicCamera camera, boolean touchControltypeOn){
        this.world = world;
        this.player = world.getPlayer();
        this.camera = camera;
        this.touchControltypeOn=touchControltypeOn;
        this.dragControltypeOn=isDragControltypeOn();
    }

    public PlayerController(World world, OrthographicCamera camera, TouchPadRenderer touchPadRenderer,
                            boolean touchControltypeOn){
        this.world = world;
        this.player = world.getPlayer();
        this.camera = camera;
        this.touchControltypeOn=touchControltypeOn;
        this.touchpadRenderer=touchPadRenderer;
        this.dragControltypeOn=isDragControltypeOn();
        this.touchpadRenderer.addObserver(this);

    }

    /**
     * The move method for the player. Gives the player a velocity.
     * @param goalPos The vector that the player should move in.
     */
    public void move(Vector2 goalPos) {
        player.setGoalPosition(goalPos);
    }

    public void movePlayerWithTouchpad(){
        float nextX=player.getPosition().x + touchpadRenderer.getTouchpad().getKnobPercentX()*player.SPEED*100;
        float nextY=player.getPosition().y + touchpadRenderer.getTouchpad().getKnobPercentY() * player.SPEED * 100;
        move(new Vector2(nextX, nextY));
    }

    /**
     * Updates the models and makes the necessary stuff to draw the next frame.
     */
    public void update(){
        world.checkCollision();
        if(world.getPlayer().getLives() <= 0) {
            // TODO: call method gameOver() and stop enemies from spawning and show gameover screen
            //System.err.println("Game Over");
        }
        for(Bullet b : player.getWeapon().getBullets()) {
            b.update();
        }
        for(Bullet b : world.getBullets()) {
            b.update();

        }
        for(Enemy e: world.getEnemies()){
            e.updatePosition(world.getPlayer().getPosition());
            for(Bullet b: e.getWeapon().getBullets()){
                b.update();
            }
            e.getWeapon().removeOutOfBoundsBullets();
        }
        if (move) {
            world.getPlayer().updatePosition();
        }

        world.getPlayer().getWeapon().removeOutOfBoundsBullets();
        world.removeOutOfBoundsEnemies();
        world.removeOutOfBoundsBullets();
        if(touchControltypeOn){
            movePlayerWithTouchpad();
        }
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
        if(dragControltypeOn){
            Vector3 pos = camera.unproject(new Vector3(screenX, screenY, 0));
            move(new Vector2(pos.x, pos.y));
            player.fire();
            this.move = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(dragControltypeOn){
            //TODO: implement fireStop(); this should stop the timer for bullets
            player.stopFire();
            this.move = false;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(dragControltypeOn){
            Vector3 pos = camera.unproject(new Vector3(screenX, screenY, 0));
            move(new Vector2(pos.x, pos.y));
            this.move = true;
        }
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

    /**
     * This method tells us if the controltype is Drag.
     * @return Returns a boolean with  the value true if Drag-Controltype is on.
     */
    private boolean isDragControltypeOn(){
        return Settings.getControlType().equals("Drag");
    }

    @Override
    public void update(Observable observable, Object o) {
        if((Integer) o == 1){
            player.fire();
        }
        else{
            player.stopFire();
        }
    }
}