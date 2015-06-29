package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.icyvenom.needforghetto.gamestate.GameState;
import com.icyvenom.needforghetto.model.bullets.BulletDirection;
import com.icyvenom.needforghetto.model.weapons.Weapon;
import com.icyvenom.needforghetto.model.weapons.WeaponAWP;
import com.icyvenom.needforghetto.model.weapons.WeaponMFourAOne;
import com.icyvenom.needforghetto.model.weapons.WeaponNineMM;

/**
 * The Player class is the model for what the user does in game. Contains
 * the logic for the player.
 * @author Marcus. Revisited by Amar. Revisited by Anton. Revisited by Marcus.
 * @version 2.1
 */
public class Player {

    /**
     * Score of the player.
     */
    private int score = 0;

    /**
     * The movement speed of the Player.
     */
    public static final float SPEED = 0.05f;

    /**
     * The width of the Player.
     */
    public static final float WIDTH = 0.9f;

    /**
     * The height of the Player.
     */
    public static final float HEIGHT = 1f;

    /**
     * The starting position on screen for the Player.
     */
    public static final Vector2 DEFAULTPOSITION = new Vector2(4.5f, 1f);

    /**
     * The number of lives the Player has left. The Player dies when
     * life's reach 0.
     */
    private int lives = 3;

    /**
     * The position on screen for the Player.
     */
    private Vector2 position = new Vector2();

    /**
     * The position that the Player is supposed to move to.
     */
    private Vector2 goalPosition = new Vector2();

    /**
     * The acceleration vector of the Player.
     */
    private Vector2 acceleration = new Vector2();

    /**
     * The Player's velocity vector. (How fast and in what direction it's
     * supposed to move to.
     */
    private Vector2 velocity = new Vector2(0,0);

    /**
     * The Players hitbox. If there's an enemy or enemy bullet inside it
     * the player will lose 1 life.
     */
    private Rectangle bounds = new Rectangle();

    /**
     * The Players weapon.
     */
    private Weapon weapon;
    /**
     * The timer for the attack speed of the player. Sets how often
     * the Player should fire bullets.
     */
    private Timer attackSpeed= new Timer();

    private boolean isDead = false;

    /**
     * The constructor for a new Player object. The given position will be
     * used to determine where the player will start. Makes sure the attack rate
     * timer is setup and ready to use.
     * @param position The spawn position of the Player object.
     */
    public Player(Vector2 position, String playerWeapon){
        this.position = position.cpy();
        setWeapon(playerWeapon);
        this.bounds.height= HEIGHT;
        this.bounds.width = WIDTH;
        this.bounds.setX(this.position.x);
        this.bounds.setY(this.position.y);
        this.weapon.setPosition(this.position);
        //Schedule's a new task for the Player weapons attack speed.
        attackSpeed.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                weapon.addBullet();
            }

        }, 0, weapon.getAttackRate());
        //Force stop the timer so that it doesn't fire bullets as
        //soon as the Player object is created
        attackSpeed.stop();
    }

    /**
     * Getter for the Player hitbox.
     * @return The hitbox for the Player.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * The getter for the Player's position.
     * @return Returns a copy of the Player's position.
     */
    public Vector2 getPosition() {
        return position.cpy();
    }

    /**
     * Setter for the new position of the Player. The method also makes sure that everything is
     * updated with the new position.
     * @param position The Player's new position.
     */
    public void setPosition(Vector2 position) {
        if (this.position != position) {
            this.position = position.cpy();
            this.bounds.setX(this.position.x);
            this.bounds.setY(this.position.y);
            this.weapon.setPosition(this.position.cpy().add(HEIGHT/3, WIDTH/2));
        }
    }

    public void setWeapon(String playerWeapon){
        switch (playerWeapon){
            case "Walther PP":
                weapon = new WeaponNineMM(BulletDirection.UP);
                break;
            case "M4A1":
                weapon = new WeaponMFourAOne(BulletDirection.UP);
                break;
            case "AWP":
                weapon = new WeaponAWP(BulletDirection.UP);
                break;
        }

    }

    /**
     *  Add points to the current point of player.
     * @param add The added point.
     */
    public void addPoints(int add){
        this.score += add;
    }

    /**
     * Kills the Player. The Player lose 1 life and has it's position reset
     * to the start position.
     */
    public void kill(){
        if(!GameState.GODMODE) {
            lives--;
            setPosition(DEFAULTPOSITION);
            goalPosition.set(DEFAULTPOSITION);
            weapon.setPosition(DEFAULTPOSITION);
            GameState.GODMODE = true;
            Timer godModeTimer = new Timer();
            godModeTimer.scheduleTask(new Timer.Task() {

                @Override
                public void run() {
                    GameState.GODMODE = false;
                }

            }, 4, 4f,1);
            if (lives <= 0) isDead = true;
        }
    }

    /**
     * The getter for the Player's weapon.
     * @return The Player's weapon.
     */
    public com.icyvenom.needforghetto.model.weapons.Weapon getWeapon() {
        return weapon;
    }

    /**
     * Start method for the Player's firing. Starts the timer. The player should
     * now be firing bullets.
     */
    public void fire() {
        if(!isGod()){
            attackSpeed.start();
        }
    }

    /**
     * Stop method for the Player's firing. Stops the timer. The player should no longer be firing
     * bullets.
     */
    public void stopFire() {
        attackSpeed.stop();
    }

    /**
     * Getter for the number of lives the Player has.
     * @return The number of lives the Player has.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Getter for the score of the player.
     * @return  The score of the player.
     */
    public int getScore() {return score;}

    /**
     * Updates the position of the Player. if the Player has reached it's goalPosition the Player
     * will stop moving. Otherwise it updates the position accordingly.
     * The second value in the epsilonEquals is the epsilon value that dictates the approved
     * interval.
     */
    public void updatePosition() {
        if(getPosition().epsilonEquals(goalPosition, 0.001f)) {
            velocity.x = 0;
            velocity.y = 0;
        }
        setPosition(getPosition().cpy().add(velocity.cpy().scl(SPEED)));
    }

    /**
     * The setter for the players velocity based on the goalPosition.
     * The goalposition is offset to place the middle of the car on the touchevent.
     * The physics engine says:
     * velocity = goalPosition - currentPosition.
     * @param goalPos The new goalPosition.
     */
    public void setGoalPosition(Vector2 goalPos) {
        if (goalPos != null) {
            goalPosition = goalPos.sub(new Vector2(WIDTH / 2, HEIGHT / 5));
            this.velocity = goalPos.cpy().sub(getPosition());
        }
    }

    /**
     * For testing purposes only!
     * @param lives The nbr of lives the player should have
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isGod() {
        return GameState.GODMODE;
    }
}