package com.icyvenom.needforghetto.highscore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by anton on 2015-05-13.
 */
public class Highscore {

    Preferences preferences = Gdx.app.getPreferences("My preferences");

    // Dummy contructor
    public Highscore() {
        preferences.putInteger("score1", 1);
        preferences.putInteger("score2", 2);
        preferences.putInteger("score3", 3);
        preferences.putInteger("score4", 4);
        preferences.putInteger("score5", 1);
        preferences.putInteger("score6", 2);
        preferences.putInteger("score7", 3);
        preferences.putInteger("score8", 4);
        preferences.putInteger("score9", 1);
        preferences.putInteger("score10", 2);

        preferences.putString("name1", "AAA");
        preferences.putString("name2", "AAA");
        preferences.putString("name3", "AAA");
        preferences.putString("name4", "AAA");
        preferences.putString("name5", "AAA");
        preferences.putString("name6", "AAA");
        preferences.putString("name7", "AAA");
        preferences.putString("name8", "AAA");
        preferences.putString("name9", "AAA");
        preferences.putString("name10", "AAA");

        preferences.flush();
    }

    public String getRank(int i) {

        return preferences.getString("name"+i)+"..."+preferences.getInteger("score"+i);

    }

}
