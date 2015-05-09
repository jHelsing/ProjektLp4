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
        enemies.add(new Enemy(new Vector2(2,6)));
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

                for(int i = 0; i<enemies.size(); i++) {
                    Enemy e = enemies.get(i);

                    float enemyMinX = e.getBounds().getX();
                    float enemyMaxX = e.getBounds().getWidth() + enemyMinX;
                    float enemyMinY = e.getBounds().getY();
                    float enemyMaxY = e.getBounds().getHeight() + enemyMinY;

                    for(int j=0; i<playerBullets.size(); j++) {
                        Bullet b = playerBullets.get(j);

                        float bulletMinX = b.getBounds().getX();
                        float bulletMaxX = b.getBounds().getWidth() + bulletMinX;
                        float bulletMinY = b.getBounds().getY();
                        float bulletMaxY = b.getBounds().getHeight() + bulletMinY;

                        if(enemyMinY <= bulletMinY && bulletMinY <= enemyMaxY) {
                            //Bullet coming from the bottom (3rd and 4th quadrant)
                            if(enemyMinX <= bulletMaxX && bulletMinX <= enemyMinX) {
                                //Bullet is in the 3rd quadrant of a system with Enemy as origin
                                enemies.remove(e);
                                player.getWeapon().getBullets().remove(b);
                            } else if(bulletMinX <= enemyMaxX && enemyMaxX <= bulletMaxX) {
                                //Bullet is in the 4th quadrant of a system with Enemy as origin
                                enemies.remove(e);
                                player.getWeapon().getBullets().remove(b);
                            }
                        } else if(enemyMinY <= bulletMaxY && bulletMaxY <= enemyMaxY) {
                            //Bullet coming from the top (1st and 2nd quadrant)
                            if(enemyMinX <= bulletMaxX && bulletMaxX <= enemyMaxX) {
                                //Bullet is in the 2nd quadrant of a system with Enemy as origin
                                enemies.remove(e);
                                player.getWeapon().getBullets().remove(b);
                            } else if(enemyMinX <= bulletMinX && bulletMinX <= enemyMaxX) {
                                //Bullet is in the 1st quadrant of a system with Enemy as origin
                                enemies.remove(e);
                                player.getWeapon().getBullets().remove(b);
                            }
                        }


                    }



                }

            }

        }


    }
}