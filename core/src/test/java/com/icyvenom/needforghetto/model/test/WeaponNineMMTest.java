package com.icyvenom.needforghetto.model.test;

import com.icyvenom.needforghetto.model.BulletDirection;
import com.icyvenom.needforghetto.model.Weapon;
import com.icyvenom.needforghetto.model.WeaponNineMM;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Amar.
 * @version 1.0
 */
public class WeaponNineMMTest extends TestCase {
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