package com.icyvenom.needforghetto.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Timer;
import com.icyvenom.needforghetto.model.enemies.EnemyFactory;
import com.icyvenom.needforghetto.model.enemies.EnemyTemplate;

import java.util.List;



/**
 * A class that spawns the different waves read from the provided .json file.
 * It will spawn as many waves that are found within the json by asking the
 * enemyfactory and adding the new enemies to the provided list.
 *
 * Created by Marcus on 2015-05-22.
 */
public class WaveSpawner extends Timer.Task {

    private List<com.icyvenom.needforghetto.model.enemies.Enemy> enemies;
    private int currentWave = 0;
    private com.icyvenom.needforghetto.model.enemies.EnemyFactory factory;

    public WaveSpawner(List<com.icyvenom.needforghetto.model.enemies.Enemy> enemies) {
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
