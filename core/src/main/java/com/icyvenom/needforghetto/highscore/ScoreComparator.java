package com.icyvenom.needforghetto.highscore;

import java.util.Comparator;

/**
 * Created by anton on 2015-05-19.
 */
class ScoreComparator implements Comparator<Score> {
    @Override
    public int compare(Score lhs, Score rhs) {
        int score1 = lhs.getScore();
        int score2 = rhs.getScore();

        if(score1 > score2) {
            return -1;
        }
        else if (score1 < score2) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
