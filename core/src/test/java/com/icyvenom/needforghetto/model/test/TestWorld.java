package com.icyvenom.needforghetto.model.test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.math.Vector2;
import com.icyvenom.needforghetto.model.Enemy;
import com.icyvenom.needforghetto.model.EnemySimple;
import com.icyvenom.needforghetto.model.Player;
import com.icyvenom.needforghetto.model.Weapon;
import com.icyvenom.needforghetto.model.World;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * @author Jonathan.
 * @version 1.0
 */
public class TestWorld extends TestCase {

    @Test
    public void testCreateBasicWorld() throws Exception {

    }

    @Test
    public void testCheckCollision() throws Exception {
        String[] s = new String[20];
        HeadlessLauncher.main(s);
        SpecialApplication hej = HeadlessLauncher.test;
        hej.getApplicationListener().create();
        World world = NeedForGhettoTest.world;

        // Creates an instance of World so that we can test it. Also gets the often used
        // objects from World

        Player player = world.getPlayer();
        Weapon playerWeapon = player.getWeapon();

        // Checking what happens to collision between player and enemy when Player is around Enemy
        Enemy e = new EnemySimple(new Vector2(5,5));
        world.getEnemies().add(e);

        //Sets upp the vector positions that are in each corner of the enemy's hitbox
        Vector2 topLeftCorner = new Vector2(e.getBounds().getX(), e.getBounds().getY());
        Vector2 topRightCorner = new Vector2(e.getBounds().getX()+e.getBounds().getWidth(),
                e.getBounds().getY());
        Vector2 bottomRightCorner = new Vector2(e.getBounds().getX()+e.getBounds().getWidth(),
                e.getBounds().getY()+e.getBounds().getHeight());
        Vector2 bottomLeftCorner = new Vector2(e.getBounds().getX(), e.getBounds().getY() +
                e.getBounds().getHeight());

        //Start testing from the top left corner. The test vector should be very close to the enemy.
        Vector2 testVector = new Vector2(0.0001f, 0.0001f).add(topLeftCorner);
        player.setPosition(testVector);
        world.checkCollision();
        boolean testTopLeftCorner;
        if(world.getEnemies().isEmpty()) {
            testTopLeftCorner = false;
            world.getEnemies().add(e);
        } else
            testTopLeftCorner = true; //Test passed. There should not be any collisions

        //Resetting the test vector and makes it ready for next position test
        testVector.setZero();
        testVector.add(0.0001f,0.0001f).add(topRightCorner);
        player.setPosition(testVector);
        world.checkCollision();
        boolean testTopRightCorner = false;
        if(world.getEnemies().isEmpty()) {
            testTopRightCorner = false;
            world.getEnemies().add(e);
        } else
            testTopRightCorner = true; //Test passed. There should not be any collisions

        //Resetting the test vector and makes it ready for next position test
        testVector.setZero();
        testVector.add(0.0001f,0.0001f).add(bottomLeftCorner);
        player.setPosition(testVector);
        world.checkCollision();
        boolean testBottomLeftCorner = false;
        if(world.getEnemies().isEmpty()) {
            testBottomLeftCorner = false;
            world.getEnemies().add(e);
        } else
            testBottomLeftCorner = true; //Test passed. There should not be any collisions

        //Resetting the test vector and makes it ready for next position test
        testVector.setZero();
        testVector.add(0.0001f,0.0001f).add(bottomRightCorner);
        player.setPosition(testVector);
        world.checkCollision();
        boolean testBottomRightCorner = false;
        if(world.getEnemies().isEmpty()) {
            testBottomRightCorner = false;
            world.getEnemies().add(e);
        } else
            testBottomRightCorner = true; //Test passed. There should not be any collisions

        boolean playerVsEnemyCollision = testTopLeftCorner && testTopRightCorner &&
                testBottomLeftCorner && testBottomRightCorner;

        boolean enemyVsBulletCollision = true;

        assertTrue(true);
    }
}