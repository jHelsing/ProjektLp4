package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Jonathan.
 * @version 1.0
 */
public class WeaponSimple extends Weapon {

    private static final int WEAPON_DAMAGE = 1;

    private static final int WEAPON_ATTACK_RATE = 1;

    /**
     * Constructor for a new SimpleWeapon
     * @param position The position of the weapon, often the same position as the player/enemy.
     *                 This will be the position of any new bullet that are added to the bullet list
     */
    public WeaponSimple(Vector2 position) {
        super.setPosition(position);
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

    public WeaponSimple() {
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

}
