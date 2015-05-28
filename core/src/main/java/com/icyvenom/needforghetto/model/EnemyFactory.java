package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Marcus on 2015-05-22.
 */
public class EnemyFactory {

    private List waves;
    private Array currentWave;

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

    public ArrayList makeAllEnemies(Array templates){
        ArrayList<Enemy> enemiesToMake = new ArrayList<Enemy>();
        if (templates.size != 0) {
            for (int i = 0; i < templates.size; i++) {
                enemiesToMake.add(createEnemy((EnemyTemplate)templates.get(i)));
            }
        }
        return enemiesToMake;
    }
    public void createNewWave(List<Enemy> enemies, int wave){
        System.err.println("Making " + wave);
        currentWave = (Array)waves.get(wave);
        enemies.addAll(makeAllEnemies(currentWave));
    }

    public int getNumberOfWaves(){
        return waves.size();
    }

}
