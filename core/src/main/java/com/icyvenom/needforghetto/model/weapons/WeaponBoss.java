package com.icyvenom.needforghetto.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.icyvenom.needforghetto.model.bullets.BulletNineMM;

/**
 * Created by anton on 2015-05-28.
 */
public class WeaponBoss extends Weapon {
    /**
     * The damage of the 9mm weapon (pistol)
     */
    private static final int WEAPON_DAMAGE = 2;

    /**
     * The attack rate of the 9mm weapon (pistol)
     */
    private static final float WEAPON_ATTACK_RATE = 1.3f;

    public WeaponBoss(Vector2 position) {
        super.setPosition(position.cpy());
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

    public WeaponBoss(com.icyvenom.needforghetto.model.bullets.BulletDirection bulletDirection) {
        super(bulletDirection);
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

    @Override
    public void addBullet() {
        super.getBullets().add(new BulletNineMM(super.getPosition().cpy().add(-2, 0), super.getBulletDirection()));
        super.getBullets().add(new BulletNineMM(super.getPosition().cpy().add(1, 0), super.getBulletDirection()));
        super.getBullets().add(new BulletNineMM(super.getPosition().cpy().add(4, 0), super.getBulletDirection()));
    }
}
