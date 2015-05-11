package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

/**
 * A class for a 9mm pistol weapon. Can be used by both enemies and Players.
 * @author Jonathan.
 * @version 1.0
 */
public class WeaponNineMM extends Weapon {

    /**
     * The damage of the 9mm weapon (pistol)
     */
    private static final int WEAPON_DAMAGE = 1;

    /**
     * The attack rate of the 9mm weapon (pistol)
     */
    private static final int WEAPON_ATTACK_RATE = 2;

    public WeaponNineMM(Vector2 position) {
        super.setPosition(position.cpy());
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

    public WeaponNineMM() {
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

    @Override
    public void addBullet() {
        super.getBullets().add(new BulletNineMM(super.getPosition().cpy()));
    }
}
