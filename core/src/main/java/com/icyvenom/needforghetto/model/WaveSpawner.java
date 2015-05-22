package com.icyvenom.needforghetto.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcus on 2015-05-22.
 */
public class WaveSpawner extends Timer.Task {

    private List<Enemy> enemies;

    public WaveSpawner(List<Enemy> enemies) {
        this.enemies = enemies;

        //file = Gdx.files.internal("level1.json");
    }

    @Override
    public void run() {

        Json json = new Json();
        json.addClassTag("enemyTemplate", EnemyTemplate.class);
        EnemyFactory factory = json.fromJson(EnemyFactory.class,
                Gdx.files.internal("level1.json"));

        enemies.add(factory.CreateEnemy(0));
    }

}
