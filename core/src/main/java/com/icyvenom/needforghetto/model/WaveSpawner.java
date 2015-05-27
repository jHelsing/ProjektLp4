package com.icyvenom.needforghetto.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Timer;

import java.util.List;



/**
 * Created by Marcus on 2015-05-22.
 */
public class WaveSpawner extends Timer.Task {

    private List<Enemy> enemies;
    private int currentWave = 1;
    private EnemyFactory factory;

    public WaveSpawner(List<Enemy> enemies) {
        this.enemies = enemies;

        Json json = new Json();
        json.addClassTag("enemyTemplate", EnemyTemplate.class);
        factory = json.fromJson(EnemyFactory.class,
                Gdx.files.internal("level1.json"));
    }

    public int getCurrentWave(){ return currentWave; }

    @Override
    public void run() {
        if (!(currentWave > 2)) {
            factory.createNewWave(enemies, currentWave);
            currentWave++;
        }
    }

}
