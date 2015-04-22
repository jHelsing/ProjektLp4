package com.icyvenom.needforghetto;

import android.graphics.Point;
import android.graphics.Rect;
import android.widget.ImageView;

/**
 * A class for the player unit. The class is a Singleton to be sure that only 1 player can exist
 * at a time. When a new game is started the method resetPlayer() will reset the Player object to
 * it's original state.
 * @version 1.0
 * @author Jonathan Helsing. Revisited 2015-03-30 by Amar Kulaglic.
 */
public class Player extends Unit {

    /**
     * The amount of lives the Player has. The Player lose 1 life if his health reach 0.
     * When the player have lost 1 life he will respawn on the map with full health but with
     * of course 1 life less.
     */
    private int nbrOfLives;

    /**
     * The amount of points the player have amassed.
     */
    private int score;

    /**
     * The singleton instance of Player.
     */
    private final static Player instance = new Player();

    private Player() {
        nbrOfLives = 3; // Default amount of Lives
        score = 0;
        super.setHealth(1);

        // TODO super.setSprite(new ImageView(getResource(R.drawable.)));
    }

    /**
     * Moves the player from the old position to this new position with a specific x- and y-coordinate.
     * @param x Moves the player to this x-coordinate.
     * @param y Moves the player to this y-coordinate
     */
    public void move(int x,int y){
        this.setPosition(new Point(x,y));
        this.setHitbox(getSprite());
    }

    /**
     * Resets the Player instance. Called on when the user starts a new game. All values reset to
     * default.
     */
    public void resetPlayer() {
        nbrOfLives=3;
        score = 0;
        super.setHealth(1);
        //TODO add everything else that's added to the constructor.
    }

    /**
     * Adds the amount of points specified in the parameter to the player's score.
     * @param points The amount of points to be added to the player's score.
     */
    public void addPointsToScore(int points) {
        score += points;
    }
    /**
     * Kills the player.
     */
    @Override
    protected void kill() /* throws GameOverException */{
        if(nbrOfLives<1){
            //throw new GameOverException();
            //TODO Handle game over way better?
        }
        else{
            nbrOfLives--;
        }
    }

    /**
     * Attacks a enemy from the player's weapon.
     */
    @Override
    public void attack(){
        //Code here.
    }

    /**
     * Getter for the Player instance.
     * @return The Player instance
     */
    public static Player getInstance() {
        return instance;
    }

    /**
     * Getter for the amount of points (the total score) the player currently has.
     * @return Score, the amount of points the player currently has.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Getter for the amount of lives the player still has
     * @return The number of lives remaining for the player
     */

    public int getNbrOfLives() {
        return this.nbrOfLives;
    }

}
