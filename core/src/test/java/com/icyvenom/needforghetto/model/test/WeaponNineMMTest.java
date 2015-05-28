package com.icyvenom.needforghetto.model.test;

import com.icyvenom.needforghetto.model.bullets.BulletDirection;
import com.icyvenom.needforghetto.model.weapons.Weapon;
import com.icyvenom.needforghetto.model.weapons.WeaponNineMM;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Amar.
 * @version 1.0
 */
public class WeaponNineMMTest {
    @Test
    public void testAddBullet(){
        String[] s = new String[20];
        HeadlessLauncher.main(s);
        Weapon weaponNineMM = new WeaponNineMM(BulletDirection.UP);

        weaponNineMM.addBullet();
        boolean b = false;
        if(!weaponNineMM.getBullets().isEmpty()){
            b = true;
        }
        assertTrue(b);
    }
}