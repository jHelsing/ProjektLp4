package com.icyvenom.needforghetto.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.icyvenom.needforghetto.model.bullets.BulletNineMM;

/**
 * A class for a 9mm pistol weapon. Can be used by both enemies and Players.
 * @author Jonathan. Revisited by Amar (2015-05-11).
 * @version 1.1
 */
public class WeaponNineMM extends Weapon {

    /**
     * The damage of the 9mm weapon (pistol)
     */
    private static final int WEAPON_DAMAGE = 2;

    /**
     * The attack rate of the 9mm weapon (pistol)
     */
    private static final float WEAPON_ATTACK_RATE = 1.3f;

    public WeaponNineMM(Vector2 position) {
        super.setPosition(position.cpy());
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

    public WeaponNineMM(com.icyvenom.needforghetto.model.bullets.BulletDirection bulletDirection) {
        super(bulletDirection);
        super.setAttackRate(WEAPON_ATTACK_RATE);
        super.setDamage(WEAPON_DAMAGE);
    }

    @Override
    public void addBullet() {
        super.getBullets().add(new BulletNineMM(super.getPosition().cpy(), super.getBulletDirection()));
    }
}
