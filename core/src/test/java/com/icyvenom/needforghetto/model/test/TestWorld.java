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
 * @version 2.0
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
    public void testPlayerAboveBulletCollision() throws Exception {
        String[] s = new String[20];
        HeadlessLauncher.main(s);
        World world = NeedForGhettoTest.world;

        // Creates an instance of World so that we can test it. Also gets the often used objects
        // from World.

        Player player = world.getPlayer();
        Vector2 playerPosition = new Vector2(4.5f, 1f);
        player.setPosition(playerPosition.cpy());
        player.setLifes(Integer.MAX_VALUE);
        Random random = new Random();

        // Makes sure that the weapon has a good position in front of Player
        world.getEnemies().clear();
        EnemySimple enemy = new EnemySimple(new Vector2(4.5f, 5f));
        enemy.stopFire();
        world.getEnemies().add(enemy);
        enemy.getWeapon().getBullets().clear();
        enemy.getWeapon().setAttackRate(1000000000f);

        for(int i=0; i<100; i++) {
            float xPos = random.nextFloat()*player.getBounds().getWidth() + 4.5f;
            Vector2 newPosition = new Vector2(xPos, player.getPosition().y+5f);
            enemy.setPosition(newPosition.cpy());
            enemy.getWeapon().addBullet();
        }

        while(!enemy.getWeapon().getBullets().isEmpty()) {
            world.checkCollision();
            for(Bullet b : enemy.getWeapon().getBullets()) {
                b.update();
            }
        }

        boolean bulletDownInFrontPlayer = false;
        if(enemy.getWeapon().getBullets().isEmpty())
            bulletDownInFrontPlayer = true;

        assertTrue(bulletDownInFrontPlayer);

    }

    @Test
    public void testPlayerMoveIntoBulletCollision() {
        String[] s = new String[20];
        HeadlessLauncher.main(s);
        World world = NeedForGhettoTest.world;

        // Creates an instance of World so that we can test it. Also gets the often used objects
        // from World.

        Player player = world.getPlayer();
        Vector2 playerPosition = new Vector2(4.5f, 1f);
        player.setPosition(playerPosition.cpy());
        Random random = new Random();

        world.getEnemies().clear();
        EnemySimple enemy = new EnemySimple(new Vector2(4.5f-player.getBounds().getWidth(),
                1.1f+player.getBounds().getHeight()));
        enemy.stopFire();
        world.getEnemies().add(enemy);
        enemy.getWeapon().getBullets().clear();
        enemy.getWeapon().setAttackRate(1000000000f);

        enemy.getWeapon().addBullet();

        enemy.getWeapon().getBullets().get(0).update();

        player.setPosition(new Vector2(4.5f - player.getBounds().getWidth(), 1f));

        world.checkCollision();

        boolean bulletToTheLeftOfPlayer = false;
        if(player.getLifes()==2 && enemy.getWeapon().getBullets().isEmpty()) {
            bulletToTheLeftOfPlayer = true;
        }

        assertTrue(bulletToTheLeftOfPlayer);

        /**
         * Testing to the right of the player
         */
        enemy.getWeapon().getBullets().clear();

        player.setPosition(playerPosition.cpy());
        enemy.setPosition(new Vector2(player.getBounds().getWidth()+player.getPosition().x + 0.7f, player.getPosition().y+1.1f));
        enemy.getWeapon().addBullet();
        enemy.getWeapon().getBullets().get(0).update();

        player.setPosition(new Vector2(4.5f + player.getBounds().getWidth(), 1f));

        world.checkCollision();

        boolean bulletToTheRightOfPlayer = false;
        if(player.getLifes()==1 && enemy.getWeapon().getBullets().isEmpty()) {
            bulletToTheRightOfPlayer = true;
        }

        assertTrue(bulletToTheRightOfPlayer);

    }

    @Test
    public void testPlayerBelowBulletCollision() {
        String[] s = new String[20];
        HeadlessLauncher.main(s);
        World world = NeedForGhettoTest.world;

        // Creates an instance of World so that we can test it. Also gets the often used objects
        // from World.

        Player player = world.getPlayer();
        Vector2 playerPosition = new Vector2(4.5f, 5f);
        player.setPosition(playerPosition.cpy());
        player.setLifes(Integer.MAX_VALUE);
        Random random = new Random();

        // Makes sure that the weapon has a good position below the Player
        world.getEnemies().clear();
        EnemySimple enemy = new EnemySimple(new Vector2(4.5f, 1f));
        enemy.stopFire();
        world.getEnemies().add(enemy);
        enemy.getWeapon().getBullets().clear();
        enemy.getWeapon().setAttackRate(1000000000f);
        enemy.getWeapon().setBulletDirection(BulletDirection.UP);

        for(int i=0; i<100; i++) {
            float xPos = random.nextFloat()*player.getBounds().getWidth() + 4.5f;
            Vector2 newPosition = new Vector2(xPos, 1f);
            enemy.setPosition(newPosition.cpy());
            enemy.getWeapon().addBullet();
        }

        while(!enemy.getWeapon().getBullets().isEmpty()) {
            player.setPosition(playerPosition);
            world.checkCollision();
            for(Bullet b : enemy.getWeapon().getBullets()) {
                b.update();
                player.setPosition(playerPosition);
            }
        }

        boolean bulletBelowPlayer = false;
        if(enemy.getWeapon().getBullets().isEmpty())
            bulletBelowPlayer = true;

        assertTrue(bulletBelowPlayer);
    }

    

}