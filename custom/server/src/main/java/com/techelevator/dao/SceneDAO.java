package com.techelevator.dao;

import com.techelevator.model.Scene;

import java.util.List;

public interface SceneDAO {


    Scene getSceneById(int SceneId);

    List<Scene> getScenes();

    Scene createScene(Scene newScene);

    Scene updateScene(Scene scene);

    int deleteSceneById(int sceneId);

}
