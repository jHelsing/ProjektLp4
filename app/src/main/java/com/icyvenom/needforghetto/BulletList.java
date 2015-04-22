package com.icyvenom.needforghetto;

import java.util.ArrayList;

/**
 * Created by Jonathan on 2015-04-22.
 */
public class BulletList extends ArrayList<Bullet> {

    private static BulletList list = new BulletList();

    private BulletList() {
        super();
    }

    public static BulletList getInstance() {
        return list;
    }

    public static void addBullet(Bullet b) {
        BulletList.getInstance().add(b);
    }
}
