package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Scene;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcSceneDAO implements SceneDAO {

    private final JdbcTemplate template;

    public JdbcSceneDAO(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    public Scene getSceneById(int sceneId) {
        Scene scene = null;
        String sql = "SELECT scene_id, description FROM scene WHERE scene_id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, sceneId);
            if (results.next()) {
                scene = mapRowToScene(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("Unable to connect to server or database");
        }
        return scene;
    }

    @Override
    public List<Scene> getScenes() {
        List<Scene> scenes = new ArrayList<>();
        String sql = "SELECT scene_id, description FROM scene";
        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                Scene scene = mapRowToScene(results);
                scenes.add(scene);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("Unable to connect to server or database");
        }
        return scenes;
    }

    @Override
    public Scene createScene(Scene newScene) {
        Scene addedScene = null;
        String sql = "INSERT INTO scene (description) " +
                "VALUES (?) " +
                "RETURNING scene_id;";
        try {
            int returningId = template.queryForObject(sql, int.class,
                    newScene.getDescription());

            addedScene = getSceneById(returningId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
        return addedScene;
    }

    @Override
    public Scene updateScene(Scene scene) {
        Scene newScene = null;
        String sql = "UPDATE scene SET scene_id = ?, description = ? " +
                "WHERE scene_id = ?;";
        try {
            int rowsAffected = template.update(sql, scene.getSceneId(), scene.getDescription(), scene.getSceneId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            newScene = getSceneById(scene.getSceneId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newScene;
    }

    @Override
    public int deleteSceneById(int sceneId) {
        int numOfRows = 0;
        String deleteSceneSql = "DELETE FROM scene WHERE scene_id = ?";

        try {
            template.update(deleteSceneSql, sceneId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numOfRows;
    }


    private Scene mapRowToScene(SqlRowSet results) {
        Scene scene = new Scene();
        scene.setSceneId(results.getInt("scene_id"));
        scene.setDescription(results.getString("description"));
        return scene;
    }
}
