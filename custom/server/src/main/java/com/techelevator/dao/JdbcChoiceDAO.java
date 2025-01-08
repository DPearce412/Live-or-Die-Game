package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Choice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcChoiceDAO implements ChoiceDAO {

    // Implementation of the interfaces
    // Create & Update and/or Delete methods too

    private final JdbcTemplate template;

    public JdbcChoiceDAO(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    public Choice getChoiceById(int choiceId) {
        Choice choice = null;
        String sql = "SELECT * FROM Choice WHERE choice_id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, choiceId);
            if (results.next()) {
                choice = mapRowToChoice(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("Unable to connect to server or database");
        }
        return choice;
    }

    @Override
    public List<Choice> getChoices() {
        List<Choice> choices = new ArrayList<>();
        String sql = "SELECT * FROM choice";
        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                Choice choice = mapRowToChoice(results);
                choices.add(choice);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("Unable to connect to server or database");
        }
        return choices;
    }

    @Override
    public Choice createChoice(Choice newChoice) {
        Choice addedChoice = null;
        String sql = "INSERT INTO choice (scene_id, description, is_safe, consequence) " +
                "VALUES (?, ?, ?, ?) " +
                "RETURNING choice_id;";
        try {
            int returningId = template.queryForObject(sql, int.class, newChoice.getSceneId(),
                    newChoice.getDescription(), newChoice.isSafe(), newChoice.getConsequence());

            addedChoice = getChoiceById(returningId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
        return addedChoice;
    }

    @Override
    public Choice updateChoice(Choice choice) {
        Choice newChoice = null;
        String sql = "UPDATE choice SET choice_id = ?, scene_id = ?, description = ? " +
                "WHERE choice_id = ?;";
        try {
            int rowsAffected = template.update(sql, choice.getChoiceId(), choice.getSceneId(), choice.getDescription());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            newChoice = getChoiceById(choice.getChoiceId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newChoice;
    }

    @Override
    public int deleteChoiceById(int choiceId) {
        int numOfRows = 0;
        String deleteChoiceSql = "DELETE FROM choice WHERE choice_id = ?";

        try {
            template.update(deleteChoiceSql, choiceId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numOfRows;
    }

    @Override
    public List<Choice> getChoicesBySceneId(int sceneId) {
        List<Choice> choices = new ArrayList<>();
        String sql = "SELECT * FROM Choice WHERE scene_id = ?";
        SqlRowSet results = template.queryForRowSet(sql, sceneId);
        while (results.next()) {
            Choice choice = new Choice();
            choice.setChoiceId(results.getInt("choice_id"));
            choice.setSceneId(results.getInt("scene_id"));
            choice.setDescription(results.getString("description"));
            choice.setSafe(results.getBoolean("is_safe"));
            choice.setConsequence(results.getString("consequence"));
            choices.add(choice);
        }
        return choices;
    }

    private Choice mapRowToChoice(SqlRowSet results) {
        Choice choice = new Choice();
        choice.setChoiceId(results.getInt("choice_id"));
        choice.setSceneId(results.getInt("scene_id"));
        choice.setDescription(results.getString("description"));
        choice.setSafe(results.getBoolean("is_safe"));
        choice.setConsequence(results.getString("consequence"));
        return choice;
    }

}
