package com.icyvenom.needforghetto.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.icyvenom.needforghetto.model.bullets.BulletNatoSeven;

/**
 * A class for a AWP sniper rifle with high damage and high attackrate. Can be used by
 * both enemies and Players.
 * @author Jonathan. Revisited by Amar.
 * @version 1.0
 */
public class WeaponAWP extends Weapon {

    private static final int WEAPON_DAMAGE = 3;

    private static final float WEAPON_ATTACK_RATE = 3;

    /**
     * Constructor for a new SimpleWeapon
     * @param position The position of the weapon, often the same position as the player/enemy.
     *                 This will be the position of any new bullet that are added to the bullet list
     */
    public WeaponAWP(Vector2 position) {
        super.setPosition(position);
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

    public WeaponAWP(com.icyvenom.needforghetto.model.bullets.BulletDirection bulletDirection) {
        super(bulletDirection);
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

    /**
     * Used to add a bullets to the bullet-list
     */
    public void addBullet(){
        super.getBullets().add(new BulletNatoSeven(super.getPosition().cpy(), super.getBulletDirection()));
    }

}
