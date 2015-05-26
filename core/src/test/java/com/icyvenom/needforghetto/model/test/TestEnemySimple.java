package com.icyvenom.needforghetto.model.test;

import com.badlogic.gdx.math.Vector2;
import com.icyvenom.needforghetto.model.Enemy;
import com.icyvenom.needforghetto.model.EnemyPistol;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jonathan on 2015-05-13.
 */
public class TestEnemySimple {

    
    @Test
    public void testConstructor() {
        String[] s = new String[20];
        HeadlessLauncher.main(s);
        Enemy enemy = new EnemyPistol(new Vector2(1,1));
        boolean b = false;
        if(enemy.getPosition().x == 1 && enemy.getPosition().y == 1){
            b = true;
        }
        assertTrue(b);
    }

    @Test
    public void testFire(){
        String[] s = new String[20];
        HeadlessLauncher.main(s);

        Thread thread = new Thread() {
            boolean bSleep = true;
            //Needs a thread to sleep before assertion to have time to do the fire-method and add
            //the bullet from the weapon of the enemySimple to its bullet-list.
            @Override
            public void run() {
                try {
                    while(bSleep) {
                        Enemy enemy = new EnemyPistol(new Vector2(1,1));
                        enemy.fire();
                        sleep(100);
                        bSleep = false;
                        boolean b = false;
                        if(!enemy.getWeapon().getBullets().isEmpty()){
                            b = true;
                        }

                        assertTrue(b);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    @Test
    public void testStopFire(){
        String[] s = new String[20];
        HeadlessLauncher.main(s);
        Enemy enemy = new EnemyPistol(new Vector2(2,8));
        enemy.stopFire();
        boolean b= false;
        if(enemy.getWeapon().getBullets().isEmpty()){
            b = true;
        }
        assertTrue(b);
    }

}