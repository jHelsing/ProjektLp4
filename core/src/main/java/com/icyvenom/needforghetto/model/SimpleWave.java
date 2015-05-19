package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import java.util.List;

/**
 * Created by anton on 2015-05-17.
 */
public class SimpleWave extends Timer.Task {

    private List<Enemy> enemies;

    public SimpleWave(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    public void run() {
        enemies.add(new EnemyPistol(new Vector2(1.5f, 11f)));
        enemies.add(new EnemyPistol(new Vector2(3f, 11f)));
        enemies.add(new EnemyPistol(new Vector2(4.5f, 11f)));
        enemies.add(new EnemyStalker(new Vector2(6f, 11f)));
        enemies.add(new EnemyAwp(new Vector2(7.5f, 11f)));
    }
}
