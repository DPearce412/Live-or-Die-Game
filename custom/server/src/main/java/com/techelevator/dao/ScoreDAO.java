package com.techelevator.dao;

import com.techelevator.model.Score;

import java.util.List;

public interface ScoreDAO {
    Score getScoreById(int scoreId);

    List<Score> getScores();

    Score createScore(Score newScore);

    Score updateScore(Score score);

    int deleteScoreById(int scoreId);

}
