package com.icyvenom.needforghetto.highscore;

/**
 * Created by anton on 2015-05-19. Revisited by Amar.
 */
public class Score {

    private String name;
    private int score;

    public Score(String name, int score) {
        String temp = name;
        this.score = score;
        temp.trim();
        if(temp.equals(null) || temp.equals("")){
            this.name="AAA";
        }
        else{
            this.name=name;
        }
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
