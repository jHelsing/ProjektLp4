package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

/**
 * A class for a M4A1 assault rifle. Can be used by both enemies and Players.
 * @author Jonathan. Revisited by Amar (2015-05-11).
 * @version 1.0
 */
public class WeaponMFourAOne extends Weapon {

    /**
     * The damage of the M4A1 (assault rifle)
     */
    private static final int WEAPON_DAMAGE = 1;

    /**
     * The attack rate of the M4A1 (assault rifle)
     */
    private static final float WEAPON_ATTACK_RATE = 0.3f;

    /**
     * Constructor for a new M4A1
     * @param position The position of the weapon, often the same position as the player/enemy.
     *                 This will be the position of any new bullet that are added to the bullet list
     */
    public WeaponMFourAOne(Vector2 position) {
        super.setPosition(position.cpy());
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

    /**
     * Constructor for a new M4A1
     */
    public WeaponMFourAOne(BulletDirection bulletDirection) {
        super(bulletDirection);
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

    @Override
    public void addBullet() {
        super.getBullets().add(new BulletNatoFive(super.getPosition().cpy(), super.getBulletDirection()));
    }
}
