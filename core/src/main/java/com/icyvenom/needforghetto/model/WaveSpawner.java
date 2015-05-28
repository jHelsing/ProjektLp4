package com.icyvenom.needforghetto.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Marcus on 2015-05-22.
 */
public class WaveSpawner extends Timer.Task {

    private List<Enemy> enemies;
    private int currentWave = 0;
    private EnemyFactory factory;

    public WaveSpawner(List<Enemy> enemies) {
        this.enemies = enemies;
        FileHandle file = Gdx.files.internal("level1.json");

        Json json = new Json();
        json.addClassTag("enemyTemplate", EnemyTemplate.class);
        factory = json.fromJson(EnemyFactory.class, file);
    }

    public int getCurrentWave(){ return currentWave; }

    @Override
    public void run() {
        if (currentWave < factory.getNumberOfWaves()) {
            factory.createNewWave(enemies, currentWave);
            currentWave++;
        }
    }

}
