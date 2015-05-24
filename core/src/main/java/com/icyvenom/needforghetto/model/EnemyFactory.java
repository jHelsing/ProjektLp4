package com.icyvenom.needforghetto.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Marcus on 2015-05-22.
 */
public class EnemyFactory {

    private ArrayList<EnemyTemplate> templates;

    public Enemy CreateEnemy(int id){
        EnemyTemplate et = templates.get(id);
        if (et.enemyType.equals("EnemyPistol")){
            EnemyPistol e = new EnemyPistol(new Vector2(et.startPos, 10f));
            return e;
        }else {
            return null;
        }
    }

    public boolean GetNextEnemy(int id){
        EnemyTemplate temp = templates.get(id);
        return temp.enemyType.equals("EnemyPistol");
    }

    public ArrayList<Enemy> MakeAllEnemies(){
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        if (!templates.isEmpty()) {
            for (int i = 0; i < templates.size(); i++) {
                enemies.add(CreateEnemy(i));
            }
        }
        return enemies;
    }
}
