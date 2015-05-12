package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import java.util.ArrayList;
import java.util.List;

/**
 * The model of the world. This is the main Model-object. It has access to all the other Model
 * objects.
 * @author Marcus. Revisited by Jonathan 2015-05-06. Revisited by Amar.
 * @version 1.15
 */
public class World {

    /**
     * This is a list containing all the Enemies that matter to the game. Enemies that are not on
     * the screen are not in this list.
     */
    private List<Enemy> enemies = new ArrayList<Enemy>();

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
        //TODO add support for player vs. bullets
        if(enemies != null) {
            //If enemies exist we check collisions between player and enemies.

            float pMinX = player.getBounds().getX();
            float pMaxX = pMinX + player.getBounds().getWidth();
            float pMinY = player.getBounds().getY();
            float pMaxY = pMinY + player.getBounds().getHeight();

            //Checking collisions between all enemies and the player
            for(int i = 0; i<enemies.size(); i++) {
                Enemy e = enemies.get(i);

                float eMinX = e.getBounds().getX();
                float eMaxX = eMinX + e.getBounds().getWidth();
                float eMinY = e.getBounds().getY();
                float eMaxY = eMinY + e.getBounds().getHeight();

                if(eMinY <= pMaxY && pMinY <= eMinY) {
                    // From above
                    if(pMinX <= eMaxX && eMaxX <= pMaxX) {
                        // From the left
                        enemies.remove(e);
                        player.kill();
                    } else if(eMinX <= pMaxX && pMinX <= eMinX) {
                        // From the right
                        enemies.remove(e);
                        player.kill();
                    }
                } else if(pMinY <= eMaxY && eMaxY <= pMaxY) {
                    // From below
                    if(pMinX <= eMaxX && eMaxX <= pMaxX) {
                        // From the left
                        enemies.remove(e);
                        player.kill();
                    } else if(eMinX <= pMaxX && pMinX <= eMinX) {
                        // From the right
                        enemies.remove(e);
                        player.kill();
                    }
                }
            }

            //checking if there are any bullets that belongs to the player on the screen
            if(!player.getWeapon().getBullets().isEmpty()) {
                //We are now checking for collisions between Player bullets and enemies

                for(int i=0; i<player.getWeapon().getBullets().size(); i++) {
                    Bullet b = player.getWeapon().getBullets().get(i);
                    checkPlayerBulletCollision(b);
                }
            }
        }
    }

    private boolean checkPlayerBulletCollision(Bullet b) {
        float minX = b.getBounds().getX() - b.getBounds().getWidth();
        float maxX = b.getBounds().getX();
        float minY = b.getBounds().getY() - b.getBounds().getHeight();
        float maxY = b.getBounds().getY();

        for(int j=0; j<enemies.size(); j++) {
            Enemy e = enemies.get(j);

            float enemyMinX = e.getBounds().getX() - e.getBounds().getWidth();
            float enemyMaxX = e.getBounds().getX();
            float enemyMinY = e.getBounds().getY() - e.getBounds().getHeight();
            float enemyMaxY = e.getBounds().getY();


        }
        return false;
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
}