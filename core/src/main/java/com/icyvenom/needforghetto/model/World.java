package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import java.util.ArrayList;
import java.util.List;

/**
 * The model of the world. This is the main Model-object. It has access to all the other Model
 * objects.
 * @author Marcus. Revisited by Jonathan 2015-05-13. Revisited by Amar.
 * @version 2.0
 */
public class World {

    /**
     * This is a list containing all the Enemies that matter to the game. Enemies that are not on
     * the screen are not in this list.
     */
    private List<Enemy> enemies = new ArrayList<Enemy>();

    /**
     * This is a list containing the bullets of the enemies that have died.
     */
    private List<Bullet> bullets = new ArrayList<Bullet>();

    /**
     * The Player object that is in the game.
     */
    private Player player;

    /**
     * This is the spawn timer for enemies. The time between each enemy to come on the screen.
     */
    private Timer spawnTimer = new Timer();

    /**
     * The standard constructor for a new World. Makes sure that the spawn timer
     * for enemies has started and that the basic world exists.
     */
    public World() {
        initSpawnTimer();
        createBasicWorld();
        spawnTimer.start();
    }

    /**
     * Creates the basic world where there's only 1 Player and makes sure that it starts in the
     * right position.
     */
    public void createBasicWorld(){
        player = new Player(Player.DEFAULTPOSITION);
    }

    /**
     * Checks for collisions between player and enemies and bullets.
     */
    public void checkCollision(){
        //Starts by checking if there are enemies on screen.
        if(enemies != null) {
            //Creates 4 float's at each max and min value for the player. These are used to
            // calculate overlaping between player and enemy or bullet.
            float pMinX = player.getBounds().getX();
            float pMaxX = pMinX + player.getBounds().getWidth();
            float pMinY = player.getBounds().getY();
            float pMaxY = pMinY + player.getBounds().getHeight();
            //Checking collisions between all enemies and the player
            for(int i = 0; i<enemies.size(); i++) {
                Enemy e = enemies.get(i);
                //Creates 4 float's for each max and min value for the enemy position.
                float eMinX = e.getBounds().getX();
                float eMaxX = eMinX + e.getBounds().getWidth();
                float eMinY = e.getBounds().getY();
                float eMaxY = eMinY + e.getBounds().getHeight();
                //Checks if the enemy is comming from above the player
                if(eMinY <= pMaxY && pMinY <= eMinY) {
                    //Checks if the enemy is comming from the left (from above)
                    if(pMinX <= eMaxX && eMaxX <= pMaxX) {
                        //Collision between Player and Enemy has happened and we make sure to kill
                        //the player and remove the enemy from the game.
                        killEnemy(e);
                        player.kill();
                    // Checks if the enemy is coming from the right (from above)
                    } else if(eMinX <= pMaxX && pMinX <= eMinX) {
                        //Collision between Player and Enemy has happened and we make sure to kill
                        //the player and remove the enemy from the game.
                        killEnemy(e);
                        player.kill();
                    }
                //Checks if the enemy is coming from below the player
                } else if(pMinY <= eMaxY && eMaxY <= pMaxY) {
                    //Checks if the enemy is coming from the left (from below)
                    if(pMinX <= eMaxX && eMaxX <= pMaxX) {
                        //Collision between Player and Enemy has happened and we make sure to kill
                        //the player and remove the enemy from the game.
                        killEnemy(e);
                        player.kill();
                    // Checks if the enemy is coming from the right (from below)
                    } else if(eMinX <= pMaxX && pMinX <= eMinX) {
                        //Collision between Player and Enemy has happened and we make sure to kill
                        //the player and remove the enemy from the game.
                        killEnemy(e);
                        player.kill();
                    }
                }
            }

            //Starts checking for collisions between player bullets and enemies
            if(!player.getWeapon().getBullets().isEmpty()) {
                //So we do not interfere with the enemy loop we create this List to keep track of
                //the enemies we need to remove
                List<Enemy> enemiesToRemove = new ArrayList<Enemy>();

                for (int i=0; i<enemies.size(); i++) {
                    Enemy e = enemies.get(i);
                    //Creates 4 float's for each max and min value for the enemy position.
                    float eMinX = e.getBounds().getX();
                    float eMaxX = eMinX + e.getBounds().getWidth();
                    float eMinY = e.getBounds().getY();
                    float eMaxY = eMinY + e.getBounds().getHeight();
                    //Creates a list of bullets we need to remove so that we do not interfere with
                    //the loop
                    List<Bullet> bulletsToRemove = new ArrayList<Bullet>();

                    for(int j=0; j<player.getWeapon().getBullets().size(); j++) {
                        Bullet b = player.getWeapon().getBullets().get(j);
                        //Creates 4 float's for each max and min value for the Bullet position.
                        float bMinX = b.getBounds().getX();
                        float bMaxX = bMinX + b.getBounds().getWidth();
                        float bMinY = b.getBounds().getY();
                        float bMaxY = bMinY + b.getBounds().getHeight();
                        //These if-cases are done the exact same way as the one above but the
                        //order of the if-case are changed to be more optimized for this calculation
                        if(eMinY <= bMaxY && bMaxY <= eMaxY) {
                            //From below
                            if(eMinX <= bMaxX && bMaxX <= eMaxX) {
                                bulletsToRemove.add(b);
                                enemiesToRemove.add(e);
                            } else if(eMinX <= bMinX && bMinX <= eMaxX) {
                                bulletsToRemove.add(b);
                                enemiesToRemove.add(e);
                            }
                        } else if(eMinY <= bMaxY && bMinY <= eMaxY){
                            if(eMinX <= bMaxX && bMaxX <= eMaxX) {
                                bulletsToRemove.add(b);
                                enemiesToRemove.add(e);
                            } else if(eMinX <= bMinX && bMinX <= eMaxX) {
                                bulletsToRemove.add(b);
                                enemiesToRemove.add(e);
                            }
                        }

                    }

                    player.getWeapon().getBullets().removeAll(bulletsToRemove);
                }
                for (Enemy e : enemiesToRemove){
                    killEnemy(e);
                }

            }

            for(int i=0; i<enemies.size(); i++) {
                Enemy e = enemies.get(i);
                //Creates a list of bullets that will be removed
                List<Bullet> bulletsToRemove = new ArrayList<Bullet>();
                for(int j=0; j<e.getWeapon().getBullets().size(); j++) {
                    Bullet b = e.getWeapon().getBullets().get(j);
                    //Creates 4 float's for each max and min value for the Bullet position.
                    float bMinX = b.getBounds().getX();
                    float bMaxX = bMinX + b.getBounds().getWidth();
                    float bMinY = b.getBounds().getY();
                    float bMaxY = bMinY + b.getBounds().getHeight();
                    //These if-cases are done the exact same way as the ones above but the
                    //order of the if-case are changed to be more optimized for this calculation
                    if(pMinY <= bMaxY && bMinY <= pMaxY){
                        if(pMinX <= bMaxX && bMaxX <= pMaxX) {
                            bulletsToRemove.add(b);
                            player.kill();
                        } else if(pMinX <= bMinX && bMinX <= pMaxX) {
                            bulletsToRemove.add(b);
                            player.kill();
                        }
                    } else if(pMinY <= bMaxY && bMaxY <= pMaxY) {
                        if(pMinX <= bMaxX && bMaxX <= pMaxX) {
                            bulletsToRemove.add(b);
                            player.kill();
                        } else if(pMinX <= bMinX && bMinX <= pMaxX) {
                            bulletsToRemove.add(b);
                            player.kill();
                        }
                    }
                }
                e.getWeapon().getBullets().removeAll(bulletsToRemove);
            }
            if (!bullets.isEmpty()){
                List<Bullet> bulletsToRemove = new ArrayList<Bullet>();
                for (Bullet b : bullets) {
                    float bMinX = b.getBounds().getX();
                    float bMaxX = bMinX + b.getBounds().getWidth();
                    float bMinY = b.getBounds().getY();
                    float bMaxY = bMinY + b.getBounds().getHeight();

                    if(pMinY <= bMaxY && bMinY <= pMaxY){
                        if(pMinX <= bMaxX && bMaxX <= pMaxX) {
                            bulletsToRemove.add(b);
                            player.kill();
                        } else if(pMinX <= bMinX && bMinX <= pMaxX) {
                            bulletsToRemove.add(b);
                            player.kill();
                        }
                    } else if(pMinY <= bMaxY && bMaxY <= pMaxY) {
                        if(pMinX <= bMaxX && bMaxX <= pMaxX) {
                            bulletsToRemove.add(b);
                            player.kill();
                        } else if(pMinX <= bMinX && bMinX <= pMaxX) {
                            bulletsToRemove.add(b);
                            player.kill();
                        }
                    }

                }
                bullets.removeAll(bulletsToRemove);
            }
        }
    }



    /**
     * Initiates the spawn timer for enemies.
     */
    private void initSpawnTimer() {
        // in the case that we need a more advanced system we could always make a custom timer/task
        spawnTimer.scheduleTask(new Timer.Task() {
            int dummy = 0;

            @Override
            public void run() {
                enemies.add(new EnemySimple(new Vector2(dummy,dummy)));
                dummy++;
            }
        }, 0, 1, 10);
    }

    /**
     * Getter for the list of enemies that are relevant to the game.
     * @return A list of enemies.
     */
    public List<Enemy> getEnemies(){
        return enemies;
    }

    /**
     * A getter for the Player object that is playing this game.
     * @return The Player object.
     */
    public Player getPlayer(){
        return player;
    }

    public Timer getSpawnTimer() {
        return spawnTimer;
    }

    public List<Bullet> getBullets() { return bullets; }

    /**
     * Checks the list of Enemies for any enemies that have out of the bounds of the game
     * and removes them if they have.
     */

    public void removeOutOfBoundsEnemies(){
        List<Enemy> enemiesToRemove = new ArrayList<Enemy>();
        if (!enemies.isEmpty()) {
            for (Enemy e : enemies) {
                if ((e.getBounds().getY() + e.getBounds().getHeight()) < 0f || e.getBounds().getX() < 0.5f
                        || e.getBounds().getX() + e.getBounds().getHeight() > 10.5f) {
                    enemiesToRemove.add(e);
                }
            }
            for (Enemy e : enemiesToRemove) {
                killEnemy(e);
            }
        }
    }

    /**
     * Removes a killed enemy from the game and gives the bullets it owns to the world.
     * @param e enemy to be removed
     */
    public void killEnemy(Enemy e) {
        bullets.addAll(e.getWeapon().getBullets());
        enemies.remove(e);
    }

    /**
     * Checks the list of the bullets in the world to see if they are still within bounds
     * and removes them if they are not.
     */
    public void removeOutOfBoundsBullets(){
        List<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        if(!bullets.isEmpty()) {
            for (Bullet b : getBullets()) {
                if ( (b.getBounds().getY() + b.getBounds().height < 0f) || b.getBounds().getX() < -0.5f
                        || b.getBounds().getX() + b.getBounds().getWidth() > 10.5f){
                    bulletsToRemove.add(b);
                }
            }
            bullets.removeAll(bulletsToRemove);
        }
    }
}