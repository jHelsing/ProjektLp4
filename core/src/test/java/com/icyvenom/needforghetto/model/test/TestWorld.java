package com.icyvenom.needforghetto.model.test;


import com.badlogic.gdx.math.Vector2;
import com.icyvenom.needforghetto.model.Bullet;
import com.icyvenom.needforghetto.model.BulletDirection;
import com.icyvenom.needforghetto.model.BulletNineMM;
import com.icyvenom.needforghetto.model.Enemy;
import com.icyvenom.needforghetto.model.EnemySimple;
import com.icyvenom.needforghetto.model.Player;
import com.icyvenom.needforghetto.model.Weapon;
import com.icyvenom.needforghetto.model.WeaponNineMM;
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
    public void testPlayerEnemyCollision() throws Exception {
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

        assertTrue(testRightSideVsEnemy);

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

        assertTrue(testLeftSideVsEnemy);

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

        assertTrue(testTopSideVsEnemy);

        //Remove all enemies in the enemies list
        world.getEnemies().clear();

        /**
         * Now testing placing 100 random enemies below the player.
         */
        for(int i=0; i<100; i++) {
            EnemySimple enemy = new EnemySimple(new Vector2());
            float xCoord = (random.nextFloat()*(playerPosition.x + playerWidth)) + playerPosition.x;
            float yCoord = playerPosition.y + diff + enemy.getBounds().getHeight();
            enemy.setPosition(new Vector2(xCoord, yCoord));
            world.getEnemies().add(enemy);
        }

        //Checks collisions
        world.checkCollision();

        boolean testBottomSideVsEnemy = false;
        if(world.getEnemies().size() == 100) {
            testBottomSideVsEnemy = true;
        }

        assertTrue(testBottomSideVsEnemy);
    }

    @Test
    public void testPlayerBulletCollision() throws Exception {
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

        // Makes sure that the weapon has a good position in front of Player

        WeaponNineMM testWeapon = new WeaponNineMM(BulletDirection.DOWN);
        testWeapon.addBullet();
        Bullet bTemp = testWeapon.getBullets().get(0);
        testWeapon.setPosition(playerPosition.add(0, playerHeight + bTemp.getBounds().getHeight()));
        EnemySimple enemy = new EnemySimple(playerPosition.add(0, playerHeight + bTemp.getBounds().getHeight()));
        testWeapon.getBullets().clear();

        //A loop that will create 100 new bullets right in front of the player
        for(int i=0; i<100; i++) {
            Vector2 newPosition= new Vector2((random.nextFloat()*(playerPosition.x+playerWidth)) +
                    playerPosition.x, playerPosition.y + playerHeight + bTemp.getBounds().getHeight());
            enemy.getWeapon().setPosition(newPosition.cpy());
            enemy.getWeapon().addBullet();
        }

        // Now moving these bullets forward and over the Player. If test is successful there should
        // not be any bullets left in testWeapon.getBullets()
        for(int i=0; i<1000; i++)
            if (!enemy.getWeapon().getBullets().isEmpty()) {
                for (Bullet b : enemy.getWeapon().getBullets()) {
                    b.update();
                }
                world.checkCollision();
            }

        boolean bulletDownInFrontPlayer = false;
        if(enemy.getWeapon().getBullets().isEmpty())
            bulletDownInFrontPlayer = true;

        assertTrue(bulletDownInFrontPlayer);




    }

}