package com.icyvenom.needforghetto.highscore;

/**
 * Created by anton on 2015-05-28.
 */
public class HighscoreFactory {

    public static IHighscore getHighscore() {
        return new HighscoreManager();
    }

}
