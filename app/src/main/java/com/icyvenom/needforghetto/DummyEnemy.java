package com.icyvenom.needforghetto;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by Marcus on 2015-04-22.
 */
public class DummyEnemy extends Enemy {
    public DummyEnemy(Bitmap sprite, Point p){
        setSprite(sprite);
        setPosition(p);
        setHitbox(sprite);
    }
}
