package com.icyvenom.needforghetto.highscore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anton on 2015-05-13.
 */
public class Highscore {

    private static Preferences preferences = Gdx.app.getPreferences("My preferences");

    public static boolean isHighscore(int score) {
        return preferences.getInteger("score10") < score;
    }

    public static void addHighscore(String name, int score) {
        preferences.putString("name1", name);
        preferences.putInteger("score1", score);
        for(int i = 10; i <= 1; i--) {
            if(preferences.getInteger("score"+i) < score) {
                preferences.putInteger("score"+(i+1), preferences.getInteger("score"+i));
                preferences.putString("name"+(i+1), preferences.getString("score" + i));
            }
            else {
                preferences.putInteger("score"+(i+1), score);
                preferences.putString("name"+(i+1), name);
            }
        }
        preferences.flush();
    }

    public static List<Integer> getScores() {
        List<Integer> scores = new ArrayList<Integer>();

        for(int i = 1; i <= 10; i++) {
            scores.add(preferences.getInteger("score"+i));
        }
        return scores;
    }

    public static List<String> getNames() {
        List<String> names = new ArrayList<String>();

        for(int i = 1; i <= 10; i++) {
            names.add(preferences.getString("name"+i));
        }

        return names;
    }


    // DO NOT USE
    public static void resetHighscores() {
        preferences.putInteger("score1", 0);
        preferences.putInteger("score2", 0);
        preferences.putInteger("score3", 0);
        preferences.putInteger("score4", 0);
        preferences.putInteger("score5", 0);
        preferences.putInteger("score6", 0);
        preferences.putInteger("score7", 0);
        preferences.putInteger("score8", 0);
        preferences.putInteger("score9", 0);
        preferences.putInteger("score10", 0);

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

}
