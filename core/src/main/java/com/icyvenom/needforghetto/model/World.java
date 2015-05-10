package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

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

    public Timer spawnTimer = new Timer();

    public World() {
        initSpawnTimer();
        createBasicWorld();
        spawnTimer.start();
    }

    public void createBasicWorld(){
        player = new Player(Player.DEFAULTPOSITION);
        /*enemies.add(new Enemy(new Vector2(3,4)));
        enemies.add(new Enemy(new Vector2(2,6)));
        enemies.add(new Enemy(new Vector2(1,2)));
        enemies.add(new Enemy(new Vector2(1,3)));
        enemies.add(new Enemy(new Vector2(1,4)));
        enemies.add(new Enemy(new Vector2(1,5)));*/
    }

    /**
     * Checks for collisions between player and enemies.
     */
    public void checkCollision(){
        //TODO add support for player vs. bullets and enemies vs. bullets
        if(enemies != null) {
            //If enemies exist we check collisions between player and enemies.

            //Creates a column and row where enemies might exist for crash to be possible.
            float minX = player.getBounds().getX();
            float maxX = player.getBounds().getWidth() + minX;
            float minY = player.getBounds().getY();
            float maxY = player.getBounds().getHeight() + minY;

            //Checking collisions between all enemies and the player
            for(int i = 0; i<enemies.size(); i++) {
                Enemy e = enemies.get(i);

                float enemyMinX = e.getBounds().getX();
                float enemyMaxX = e.getBounds().getWidth() + enemyMinX;
                float enemyMinY = e.getBounds().getY();
                float enemyMaxY = e.getBounds().getHeight() + enemyMinY;

                if(minY <= enemyMinY && enemyMinY <= maxY) {
                    //Enemy coming from the bottom (3rd and 4th quadrant)
                    if(minX <= enemyMaxX && enemyMinX <= minX) {
                        //Enemy is in the 3rd quadrant of a system with player as origin
                        enemies.remove(e);
                        player.kill();
                    } else if(enemyMinX <= maxX && maxX <= enemyMaxX) {
                        //Enemy is in the 4th quadrant of a system with player as origin
                        enemies.remove(e);
                        player.kill();
                    }
                } else if(minY <= enemyMaxY && enemyMaxY <= maxY) {
                    //Enemy coming from the bottom (1st and 2nd quadrant)
                    if(minX <= enemyMaxX && enemyMaxX <= maxX) {
                        //Enemy is in the 2nd quadrant of a system with player as origin
                        enemies.remove(e);
                    } else if(minX <= enemyMinX && enemyMinX <= maxX) {
                        //Enemy is in the 1st quadrant of a system with player as origin
                        enemies.remove(e);
                    }
                }
            }

            //checking if there are any bullets that belongs to the player on the screen
            if(!player.getWeapon().getBullets().isEmpty()) {
                //We are now checking for collisions between Player bullets and enemies

                List<Bullet> playerBullets = player.getWeapon().getBullets();

                List<Enemy> enemiesToRemove = new ArrayList<Enemy>();
                List<Bullet> bulletsToRemove = new ArrayList<Bullet>();

                for(int i=0; i<playerBullets.size(); i++) {
                    Bullet b = playerBullets.get(i);

                    float bulletMinX = b.getBounds().getX();
                    float bulletMaxX = b.getBounds().getWidth() + minX;

                    for(int j=0; j<enemies.size(); j++) {
                        Enemy e = enemies.get(j);

                        float enemyMinX = e.getBounds().getX();
                        float enemyMaxX = e.getBounds().getWidth() + enemyMinX;
                        float enemyMinY = e.getBounds().getY();
                        float enemyMaxY = e.getBounds().getHeight() + enemyMinY;

                        if(bulletMinX <= enemyMaxX && enemyMaxX <= bulletMaxX) {
                            if(b.getBounds().getY() <= enemyMinY
                                    && enemyMaxY <= b.getBounds().getY()) {
                                enemiesToRemove.add(e);
                                bulletsToRemove.add(b);
                            }
                        } else if(bulletMinX <= enemyMinX && enemyMinX <= bulletMaxX) {
                            if(b.getBounds().getY() <= enemyMinY
                                    && enemyMaxY <= b.getBounds().getY()) {
                                enemiesToRemove.add(e);
                                bulletsToRemove.add(b);
                            }
                        }
                    }
                }

                if(!enemiesToRemove.isEmpty() && !bulletsToRemove.isEmpty()) {
                    enemies.removeAll(enemiesToRemove);
                    player.getWeapon().getBullets().removeAll(bulletsToRemove);
                }
            }
        }
    }

    // Something like this should suffice for spawning enemies,
    // in the case that we need a more advanced system we could always make a custom timer/task
    private void initSpawnTimer() {
        spawnTimer.scheduleTask(new Timer.Task() {
            int dummy = 0;

            @Override
            public void run() {
                enemies.add(new Enemy(new Vector2(dummy,dummy)));
                dummy++;
            }
        }, 0, 1, 10);
    }
}