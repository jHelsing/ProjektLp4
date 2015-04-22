package com.icyvenom.needforghetto;

import android.content.Context;
import android.graphics.Point;

/**
 * Created by Jonathan on 2015-04-22.
 */
public class BulletStandard extends Bullet {

    public BulletStandard(Context context, Point position, BulletDirection direction) {
        super(context, position, 1, direction);
    }

    public BulletStandard() {
        super();
    }
}
