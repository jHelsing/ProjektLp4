package com.icyvenom.needforghetto.model.enemies;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

/**
 * This abstract class is a superclass to all existing enemies and contains basic
 * functionality.
 * @author Marcus. Revisited by Amar.
 * @version 2.0
 */
public abstract class Enemy {

    /**
     * The speed of the enemy.
     */
    private float SPEED;

    /**
     * The width of the enemy.
     */
    public static final float WIDTH = 0.9f;

    /**
     * The height of the enemy.
     */
    public static final float HEIGHT = 1f;

    /**
     * The position of the enemy.
     */
    private Vector2 position;
    /**
     * The hitbox of the enemy.
     */
    private Rectangle bounds = new Rectangle();

    /**
     * The enemy's weapon.
     */
    private com.icyvenom.needforghetto.model.weapons.Weapon weapon;

    /** The amount of lives that the enemy has.
     *
     */
    private int lives;
    /**
     * The direction of the bullet of the enemy's weapon.
     */
    private static com.icyvenom.needforghetto.model.bullets.BulletDirection bulletDirection = com.icyvenom.needforghetto.model.bullets.BulletDirection.DOWN;

    /**
     * The timer for the attack speed of the enemy. Sets how often
     * the enemy should fire bullets.
     */
    private Timer attackSpeed= new Timer();

    /* Float that will determine the direction of the enemy.
     * Default for this will be downwards.
     */
    private Vector2 goalPosition;

    /*
     * The Direction of the enemy movement.
     */
    private Vector2 direction;

    /**
     *  When a enemy is created, it's position, the hitbox and the speed are set according to
     *  the parameter and variables.
     * @param position This is the given position for the enemy.
     */
    public Enemy(Vector2 position, float speed, final com.icyvenom.needforghetto.model.weapons.Weapon weapon, Vector2 direction){
        this.position = position.cpy();
        this.SPEED = speed;
        this.bounds.height = HEIGHT;
        this.bounds.width = WIDTH;
        this.bounds.setX(this.position.x);
        this.bounds.setY(this.position.y);
        this.weapon= weapon;
        this.weapon.setPosition(this.position.cpy().add(WIDTH / 3, HEIGHT / 4));
        this.direction = direction;
        //Schedule's a new task for the enemy weapons attack speed.
        attackSpeed.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                weapon.addBullet();
            }

        }, 0, weapon.getAttackRate());
        fire();
    }

    /**
     * Start method for the enemy's firing. Starts the timer. The enemy should
     * now be firing bullets.
     */
    public void fire() {
        attackSpeed.start();
    }

    /**
     * Stop method for the enemy's firing. Stops the timer. The enemy should no longer be firing
     * bullets.
     */
    public void stopFire() {
        attackSpeed.stop();
    }

    /**
     * Gets the bounds of the enemy.
     * @return Returns the bounds.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Gets the position of the enemy.
     * @return Returns the position of the enemy.
     */
    public Vector2 getPosition() {
        return position.cpy();
    }

    /**
     * Gets the direction of the enemy.
     * @return Returns the direction of the enemy.
     */
    public Vector2 getDirection() { return direction.cpy(); }

    /**
     * Gets the direction of the bullet of the enemy's weapon
     * @return Returns the direction of the bullet of the enemy's weapon.
     */
    public static com.icyvenom.needforghetto.model.bullets.BulletDirection getBulletDirection() {
        return bulletDirection;
    }

    /**
     * Gets the weapon of the enemy.
     * @return  Returns the weapon of the enemy.
     */
    public com.icyvenom.needforghetto.model.weapons.Weapon getWeapon() {
        return weapon;
    }

    /**
     * Gets the amount of lives of the enemy.
     * @return Returns the amount of lives the enemy has
     */
    public int getLives() { return lives; }

    /**
     * Sets the position of the enemy.
     * @param position This will be the new position of the enemy
     */
    public void setPosition(Vector2 position) {
        this.position = position.cpy();
        this.bounds.setX(this.position.x);
        this.bounds.setY(this.position.y);
        this.weapon.setPosition(this.position.cpy().add(WIDTH/3, HEIGHT/4));
    }

    /**
     * Sets the direction of the enemy.
     * @param dir This will be the new direction of the enemy
     */
    public void setDirection(Vector2 dir){
        this.direction = dir.cpy();
    }

    /**
     * Sets the lives for the enemy.
     * @param lives This will be the new amount of lives of the enemy
     */
    public void setLives(int lives){
        this.lives = lives;
    }
    /**
    * Updates the position of the enemy depending on its direction and speed.
     * @param vec Needed for being able to give Stalker a new direction.
    */
    public void updatePosition(Vector2 vec) {
        goalPosition  = getPosition().cpy().add(direction.cpy().scl(SPEED));
        setPosition(goalPosition);
    }

    /**
     * Lowers the health of the enemy and returns a boolean saying if the enemy died
     * or not.
     * @return boolean saying if the enemy has run out of lives
     */
    public boolean kill(){
        lives--;
        return ( lives <= 0 );
    }
}