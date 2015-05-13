package com.icyvenom.needforghetto.model.test;

import com.badlogic.gdx.math.Vector2;
import com.icyvenom.needforghetto.model.Bullet;
import com.icyvenom.needforghetto.model.WeaponAWP;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * @author Amar.
 * @version 1.0
 */
public class TestWeaponAWP extends TestCase {
    @Test
    public void testAddBullet(){
        String[] s = new String[20];
        HeadlessLauncher.main(s);
        WeaponAWP awp=NeedForGhettoTest.awp;

        awp.addBullet();
        boolean b = false;
        if(!awp.getBullets().isEmpty()){
            b = true;
        }
        assertTrue(b);
    }
}