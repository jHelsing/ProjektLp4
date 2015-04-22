package com.icyvenom.needforghetto;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by Marcus on 2015-04-22.
 */
public class DummyEnemy extends Enemy {
    public DummyEnemy(Bitmap sprite){
        setSprite(sprite);
        setPosition(new Point(400,250));
        setHitbox(sprite);
    }
}
