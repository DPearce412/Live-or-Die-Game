package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Score;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcScoreDAO implements ScoreDAO {

    private final JdbcTemplate template;

    public JdbcScoreDAO(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    public Score getScoreById(int scoreId) {
        Score score = null;
        String sql = "SELECT score_id, user_id, last_played_scene_id, score, play_date FROM score WHERE score_id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, scoreId);
            if (results.next()) {
                score = mapRowToScore(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("Unable to connect to server or database");
        }
        return score;
    }

    @Override
    public List<Score> getScores() {
        List<Score> scores = new ArrayList<>();
        String sql = "SELECT score_id, user_id, last_played_scene_id, score, play_date FROM score";
        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                Score score = mapRowToScore(results);
                scores.add(score);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("Unable to connect to server or database");
        }
        return scores;
    }

    @Override
    public Score createScore(Score newScore) {
        Score addedScore = null;
        String sql = "INSERT INTO score (user_id, last_played_scene_id, score, play_date) " +
                "VALUES (?, ?, ?, ?) " +
                "RETURNING score_id;";
        try {
            int returningId = template.queryForObject(sql, int.class, newScore.getUserId(),
                    newScore.getLastPlayedSceneId(), newScore.getScore(), newScore.getPlayDate());

            addedScore = getScoreById(returningId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
        return addedScore;
    }

    @Override
    public Score updateScore(Score score) {
        Score newScore = null;
        String sql = "UPDATE score SET user_id = ?, last_played_scene_id = ?, score = ?, play_date = ? " +
                "WHERE score_id = ?;";
        try {
            int rowsAffected = template.update(sql, score.getUserId(), score.getLastPlayedSceneId(),
                    score.getScore(), score.getPlayDate(), score.getScoreId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            newScore = getScoreById(score.getScoreId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newScore;
    }

    @Override
    public int deleteScoreById(int scoreId) {
        int numOfRows = 0;
        String deleteScoreSql = "DELETE FROM score WHERE score_id = ?";

        try {
           numOfRows = template.update(deleteScoreSql, scoreId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numOfRows;
    }


    private Score mapRowToScore(SqlRowSet results) {
        Score score = new Score();
        score.setScoreId(results.getInt("score_id"));
        score.setUserId(results.getInt("user_id"));
        score.setLastPlayedSceneId(results.getInt("last_played_scene_id"));
        score.setScore(results.getInt("score"));
        score.setPlayDate(results.getDate("play_date").toLocalDate());
        return score;
    }
}
