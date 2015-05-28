package com.icyvenom.needforghetto.highscore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by anton on 2015-05-19.
 */
class HighscoreManager implements IHighscore{

    List<Score> scoreList = new ArrayList<Score>();
    private Preferences preferences = Gdx.app.getPreferences("My preferences");

    public HighscoreManager() {
        loadScorestoArray();
    }

    @Override
    public boolean isHighscore(int score) {
        return scoreList.get(scoreList.size()-1).getScore() < score;
    }

    @Override
    public void addHighscore(Score score) {
        scoreList.add(score);
        sort();
        writeScorestoPref();
    }

    private void loadScorestoArray() {
        for(int i = 0; i < 10; i++) {
            scoreList.add(new Score(preferences.getString("name" + i, "AAA"), preferences.getInteger("score" + i, 0)));
        }
    }

    private void sort() {
        ScoreComparator scoreComparator = new ScoreComparator();
        Collections.sort(scoreList, scoreComparator);
    }

    private void writeScorestoPref() {
        for(int i = 0; i < 10; i++) {
            preferences.putInteger("score"+i, scoreList.get(i).getScore());
            preferences.putString("name"+i, scoreList.get(i).getName());
        }
        preferences.flush();
    }

    @Override
    public List<Score> getScoreList() {
        return scoreList;
    }
}
