package com.icyvenom.needforghetto;

/**
 * Created by Amar Kulaglic on 2015-03-30.
 */
public abstract class Weapon {

    /**
     *
     * @return  Returns the damage of the weapon.
     */
    private int weaponDamage;

    /**
     *
     * @return  Returns the attack rate of the weapon.
     */
    private int AttackRate;

    /**
     *
     * @return  Returns the bullet type of the weapon.
     */
    private Bullet bullet;

    /**
     *
     * @return  Returns the damage of the weapon.
     */
    public int getWeaponDamage() {
        return weaponDamage + bullet.getDamageModifier();
    }

    /**
     *
     * @return  Returns the attack rate of the weapon.
     */
    public int getAttackRate() {
        return AttackRate;
    }

    /**
     *
     * @return  Returns the bullet type of the weapon.
     */
    public Bullet getBullet() {
        return bullet;
    }
}
