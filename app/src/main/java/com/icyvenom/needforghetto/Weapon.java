package com.icyvenom.needforghetto;

import android.content.Context;
import android.os.CountDownTimer;

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
    private final Bullet bullet;

    private Context context;

    private CountDownTimer timer;

    public Weapon(Context context, int weaponDamage, int attackRate, final Bullet bullet) {
        this.context=context;
        this.weaponDamage=weaponDamage;
        this.attackRate=attackRate;
        this.bullet=bullet;
        timer = new CountDownTimer(Long.MAX_VALUE, attackRate) {
            @Override
            public void onTick(long millisUntilFinished) {
                BulletList.addBullet(bullet);
            }

            @Override
            public void onFinish() {

            }
        };
    }

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

    public void startFire() {
        timer.start();
    }

    public void stopFire() {
        timer.cancel();
    }
}
