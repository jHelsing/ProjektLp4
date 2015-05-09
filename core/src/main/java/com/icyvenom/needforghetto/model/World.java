package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcus. Revisited by Jonathan 2015-05-06.
 * @version 1.1
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

    /**
     * Checks for collisions between player and enemies.
     */
    public void checkCollision(){
        //TODO add support for player vs. bullets and enemies vs. bullets
        if(enemies != null) {
            //If enemies exist we check collisions between player and enemies.
            //Creates a column and row where enemies might exist for crash to be possible.
            float minX = player.getPosition().x;
            float maxX = player.getBounds().getWidth() + minX;
            float minY = player.getPosition().y;
            float maxY = player.getBounds().getHeight() + minY;

            //Checking collisions between all enemies and the player
            for(Enemy e: enemies) {
                //Loops all the enemies on screen
                float enemyMinX = e.getPosition().x;
                float enemyMaxX = e.getBounds().getWidth() + enemyMinX;
                float enemyMinY = e.getPosition().y;
                float enemyMaxY = e.getBounds().getHeight() + enemyMinY;


                if(minY <= enemyMaxY && enemyMinY <= minY) {
                    //Player coming from the bottom (3rd and 4th quadrant)
                    if(minX <= enemyMaxX && enemyMinX <= minX) {
                        //Enemy is in the 3rd quadrant of a system with player as origin
                        enemies.remove(e);
                        player.kill();
                    } else if(enemyMinX <= maxX && maxX <= enemyMaxX) {
                        //Enemy is in the 4th quadrant of a system with player as origin
                        enemies.remove(e);
                        player.kill();
                    }
                } else if(minY <= enemyMinY && maxY <= enemyMaxY) {
                    if(minX <= enemyMaxX && enemyMinX <= minX) {
                        //Enemy is in the 1st quadrant of a system with player as origin
                        enemies.remove(e);
                        //player.kill();
                    } else if(enemyMinX <= maxX && maxX <= enemyMaxX) {
                        //Enemy is in the 2nd quadrant of a system with player as origin
                        enemies.remove(e);
                        //player.kill();
                    }
                }
            }
        }
    }
}