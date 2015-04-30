package com.icyvenom.needforghetto.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.icyvenom.needforghetto.NeedForGhetto;
import com.icyvenom.needforghetto.model.Bullet;
import com.icyvenom.needforghetto.model.Player;
import com.icyvenom.needforghetto.model.World;

/**
 * Created by Marcus on 2015-04-29.
 */
public class PlayerController {

    World world;
    Player player;

    public PlayerController(World world){
        this.world = world;
        this.player = world.getPlayer();
    }

    public void move(Vector2 vec){
        player.setPosition(vec);
    }

    public void fire(){ player.fire(); }

    public void update(){
        world.checkCollision();
        for(Bullet b : player.getWeapon().getBullets()) {
            b.update();
        }
    }

}
