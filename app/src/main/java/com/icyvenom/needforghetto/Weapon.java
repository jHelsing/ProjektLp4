package com.icyvenom.needforghetto;

/**
 * @author Amar Kulaglic
 * @version 1.0
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
    private int attackRate;

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
        return attackRate;
    }

    /**
     *
     * @return  Returns the bullet type of the weapon.
     */
    public Bullet getBullet() {
        return bullet;
    }
}
