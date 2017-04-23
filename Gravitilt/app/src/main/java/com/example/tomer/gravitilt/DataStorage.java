package com.example.tomer.gravitilt;

import java.util.ArrayList;

/**
 * Created by Tomer on 23/04/2017.
 */

public interface DataStorage {
    void saveScore(Score score);
    ArrayList<Score> getAllScores();
    void deleteAllScores();
}