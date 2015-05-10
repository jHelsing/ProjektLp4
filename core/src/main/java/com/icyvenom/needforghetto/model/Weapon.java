package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton. Revicited by Jonathan (2015-05-10) (made abstract)
 * @version 2.0
 */
public abstract class Weapon {

    /**
     * The damage of the weapon. Decides how much the weapon will damage an entity when they are hit
     */
    private int damage;

    /**
     * The attack rate of the weapon. Decides how often the weapon will fire bullets
     */
    private static float attackRate = 0.2f;

    /**
     * The position of the weapon
     */
    private Vector2 position = new Vector2();

    /**
     * A list of all the bullets that are in the game that belongs to this weapon.
     */
    private List<Bullet> bullets = new ArrayList<Bullet>();

    /**
     * Getter for the bullet's that belongs to this weapon
     * @return A list of bullets.
     */
    public List<Bullet> getBullets() {
        return bullets;
    }

    /**
     * Getter for the weapons position
     * @return The weapons position.
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Sets the amount of damage that the weapon does.
     * @param damage The amount of damage the weapon does.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Sets the attack rate of the weapon. How fast it will fire bullets.
     * @param attackRate The attack rate of the weapon.
     */
    public void setAttackRate(int attackRate) {
        this.attackRate = attackRate;
    }

    /**
     * Sets the position of the weapon.
     * @param position The position of the weapon
     */
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /**
     * Used to add a bullets to the bullet-list
     */
    public void addBullet() {
        bullets.add(new BulletSimple(this.position.cpy()));
    }

    public float getAttackRate() {
        return attackRate;
    }

}