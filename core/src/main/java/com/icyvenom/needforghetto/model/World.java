package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcus on 2015-04-29.
 */
public class World {

    private List<Enemy> enemies = new ArrayList<Enemy>();

    Player player;

    public  List<Enemy> getEnemies(){
        return enemies;
    }
    public Player getPlayer(){
        return player;
    }

    public World() {
        createBasicWorld();
    }

    public void createBasicWorld(){
        player = new Player(new Vector2(7, 2));
        enemies.add(new Enemy(new Vector2(3,4)));
    }

    public void checkCollision(){
        if (enemies != null){
            for (Enemy e : enemies){
                if(player.isColliding(e.getBounds())){
                    enemies.remove(e);
                }

            }
        }
    }
}
