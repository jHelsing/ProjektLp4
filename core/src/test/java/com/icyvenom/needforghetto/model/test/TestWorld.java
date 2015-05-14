package com.icyvenom.needforghetto.model.test;


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
import java.util.Random;

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
        World world = NeedForGhettoTest.world;

        // Creates an instance of World so that we can test it. Also gets the often used objects
        // from World.

        Player player = world.getPlayer();
        Vector2 playerPosition = new Vector2(5f,5f);
        player.setPosition(playerPosition);
        float playerWidth = player.getBounds().getWidth();
        float playerHeight = player.getBounds().getHeight();
        float diff = 0.0001f;
        Random random = new Random();

        for(int i=0; i<100; i++) {
            float xCoord = playerPosition.x + playerWidth + diff;
            float yCoord = (random.nextFloat()*(playerPosition.y+playerHeight)) + playerPosition.y;
            EnemySimple enemy = new EnemySimple(new Vector2(xCoord,yCoord));
            world.getEnemies().add(enemy);
        }
        //Checks collisions
        world.checkCollision();

        boolean testRightSideVsEnemy = false;
        if(world.getEnemies().size() == 100) {
            testRightSideVsEnemy = true;
        }

        //Remove all enemies in the enemies list
        world.getEnemies().clear();

        /**
         * Now testing placing 100 random enemies to the left of the player.
         */

        for(int i=0; i<100; i++) {
            EnemySimple enemy = new EnemySimple(new Vector2());
            float xCoord = playerPosition.x + diff + enemy.getBounds().getWidth()*2;
            float yCoord = (random.nextFloat()*(playerPosition.y+playerHeight)) + playerPosition.y;
            enemy.setPosition(new Vector2(xCoord, yCoord));
            world.getEnemies().add(enemy);
        }

        //Checks collisions
        world.checkCollision();


        boolean testLeftSideVsEnemy = false;
        if(world.getEnemies().size() == 100) {
            testLeftSideVsEnemy = true;
        }

        //Remove all enemies in the enemies list
        world.getEnemies().clear();

        /**
         * Now testing placing 100 random enemies above the player.
         */
        for(int i=0; i<100; i++) {
            EnemySimple enemy = new EnemySimple(new Vector2());
            float xCoord = (random.nextFloat()*(playerPosition.x+playerWidth)) + playerPosition.x;
            float yCoord = playerPosition.y + playerHeight + diff;
            enemy.setPosition(new Vector2(xCoord, yCoord));
            world.getEnemies().add(enemy);
        }

        //Checks collisions
        world.checkCollision();

        boolean testTopSideVsEnemy = false;
        if(world.getEnemies().size() == 100) {
            testTopSideVsEnemy = true;
        }


        //Remove all enemies in the enemies list
        world.getEnemies().clear();

        /**
         * Now testing placing 100 random enemies below the player.
         */
        for(int i=0; i<100; i++) {
            EnemySimple enemy = new EnemySimple(new Vector2());
            float xCoord = (random.nextFloat()*(playerPosition.x+playerWidth)) + playerPosition.x;
            float yCoord = playerPosition.y + diff;
            enemy.setPosition(new Vector2(xCoord, yCoord));
            world.getEnemies().add(enemy);
        }

        //Checks collisions
        world.checkCollision();

        boolean testBottomSideVsEnemy = false;
        if(world.getEnemies().size() == 100) {
            testBottomSideVsEnemy = true;
        }


        boolean b = testRightSideVsEnemy && testLeftSideVsEnemy &&
                testTopSideVsEnemy && testBottomSideVsEnemy;
        assertTrue(b);
    }
}