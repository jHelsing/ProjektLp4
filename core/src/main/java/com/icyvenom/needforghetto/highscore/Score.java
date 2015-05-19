package com.icyvenom.needforghetto.highscore;

/**
 * Created by anton on 2015-05-19.
 */
public class Score {

    private String name;
    private int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
