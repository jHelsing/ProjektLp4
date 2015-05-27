package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcus on 2015-05-22.
 */
public class EnemyFactory {

    private ArrayList<EnemyTemplate> wave1;
    private ArrayList<EnemyTemplate> wave2;

    public Enemy createEnemy(EnemyTemplate et){
        if (et.enemyType.equals("EnemyPistol")){
            EnemyPistol e = new EnemyPistol(new Vector2(et.startPosX, et.startPosY));
            return e;
        }else if(et.enemyType.equals("EnemyAwp")){
            EnemyAwp e = new EnemyAwp(new Vector2(et.startPosX, et.startPosY));
            return e;
        }else if(et.enemyType.equals("EnemyStalker")){
            EnemyStalker e = new EnemyStalker(new Vector2(et.startPosX, et.startPosY));
            return e;
        }else {
            return null;
        }
    }

    public ArrayList<Enemy> makeAllEnemies(ArrayList<EnemyTemplate> templates){
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        if (!templates.isEmpty()) {
            for (int i = 0; i < templates.size(); i++) {
                enemies.add(createEnemy(templates.get(i)));
            }
        }
        return enemies;
    }

    public void createNewWave(List<Enemy> enemies, int wave){
        System.err.println("Making " + wave);
        switch(wave){
            case 1: enemies.addAll(makeAllEnemies(wave1)); break;

            case 2: enemies.addAll(makeAllEnemies(wave2)); break;
        }
    }
}
