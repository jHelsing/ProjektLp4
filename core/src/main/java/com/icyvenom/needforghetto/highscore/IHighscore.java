package com.icyvenom.needforghetto.highscore;

import java.util.List;

/**
 * Created by anton on 2015-05-28.
 */
public interface IHighscore {

    public boolean isHighscore(int score);
    public void addHighscore(Score score);
    public List<Score> getScoreList();

}
