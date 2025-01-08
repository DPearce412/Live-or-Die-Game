package com.techelevator.model;

import java.time.LocalDate;

public class Score {

    private int scoreId;
    private int userId;
    private int lastPlayedSceneId;
    private int score;
    private LocalDate playDate;

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLastPlayedSceneId() {
        return lastPlayedSceneId;
    }

    public void setLastPlayedSceneId(int lastPlayedSceneId) {
        this.lastPlayedSceneId = lastPlayedSceneId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getPlayDate() {
        return playDate;
    }

    public void setPlayDate(LocalDate playDate) {
        this.playDate = playDate;
    }

    public Score(int scoreId, int userId, int lastPlayedSceneId, int score, LocalDate playDate) {
        this.scoreId = scoreId;
        this.userId = userId;
        this.lastPlayedSceneId = lastPlayedSceneId;
        this.score = score;
        this.playDate = playDate;
    }

    public Score() {

    }
}
