package com.icyvenom.needforghetto.model.test;

import com.icyvenom.needforghetto.model.bullets.BulletDirection;
import com.icyvenom.needforghetto.model.weapons.Weapon;
import com.icyvenom.needforghetto.model.weapons.WeaponMFourAOne;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Amar.
 * @version 1.0
 */
public class TestWeaponMFourAOne {

    @Test
    public void testAddBullet(){
        String[] s = new String[20];
        HeadlessLauncher.main(s);
        Weapon weaponMFourAOne = new WeaponMFourAOne(BulletDirection.DOWN);

        weaponMFourAOne.addBullet();
        boolean b = false;
        if(!weaponMFourAOne.getBullets().isEmpty()){
            b = true;
        }
        assertTrue(b);
    }
}