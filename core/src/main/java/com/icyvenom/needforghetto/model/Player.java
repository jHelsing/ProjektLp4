package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import java.awt.Event;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The Player class is the model for what the user does in game. Contains
 * the logic for the player.
 * @author Marcus. Revisited by Amar. Revisited by Anton. Revisited by Marcus.
 * @version 2.1
 */
public class Player extends Observable{

    public static enum Event{
        Gameover, Lostlife
    };

    private ArrayList<Observer> observers = new ArrayList<Observer>();

    /**
     * Score of the player.
     */
    private int score = 0;

    /**
     * The movement speed of the Player.
     */
    public static final float SPEED = 0.05f;

    /**
     * The size of the Player.
     */
    public static final float SIZE = 1f;

    /**
     * The starting position on screen for the Player.
     */
    public static final Vector2 DEFAULTPOSITION = new Vector2(4.5f, 1f);

    /**
     * The number of lives the Player has left. The Player dies when
     * life's reach 0.
     */
    private int lifes = 3;

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
    private Weapon weapon = new WeaponNineMM(BulletDirection.UP);
    /**
     * The timer for the attack speed of the player. Sets how often
     * the Player should fire bullets.
     */
    private Timer attackSpeed= new Timer();

    /**
     * The constructor for a new Player object. The given position will be
     * used to determine where the player will start. Makes sure the attack rate
     * timer is setup and ready to use.
     * @param position The spawn position of the Player object.
     */
    public Player(Vector2 position){
        this.position = position.cpy();
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
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
        System.err.print("Player shoot");
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
        this.position = position.cpy();
        this.bounds.setX(this.position.x);
        this.bounds.setY(this.position.y);
        this.weapon.setPosition(this.position);
    }

    /**
     *
     * @param add Add points to the current point of player.
     */
    public void addPoints(int add){
        this.score += add;
    }

    /**
     * Kills the Player. The Player lose 1 life and has it's position reset
     * to the start position.
     */
    public void kill(){
        lifes--;
        setPosition(DEFAULTPOSITION);
        goalPosition.set(DEFAULTPOSITION);
        weapon.setPosition(DEFAULTPOSITION);
        System.err.println(lifes);
        if(lifes <= 0) notifyObservers(this, Event.Gameover);
        else notifyObservers(this, Event.Lostlife);
    }

    /**
     * The getter for the Player's weapon.
     * @return The Player's weapon.
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Start method for the Player's firing. Starts the timer. The player should
     * now be firing bullets.
     */
    public void fire() {
        attackSpeed.start();
    }

    /**
     * Stop method for the Player's firing. Stops the timer. The player should no longer be firing
     * bullets.
     */
    public void stopFire() {
        attackSpeed.stop();
    }

    /**
     * Getter for the number of lifes the Player has.
     * @return The number of lifes the Player has.
     */
    public int getLifes() {
        return lifes;
    }

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
            goalPosition = goalPos.sub(new Vector2(SIZE / 2, SIZE / 5));
            this.velocity = goalPos.cpy().sub(getPosition());
        }
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Observable observable, Event event) {
        for(Observer observer : observers) {
            observer.update(observable, event);
        }
    }
}