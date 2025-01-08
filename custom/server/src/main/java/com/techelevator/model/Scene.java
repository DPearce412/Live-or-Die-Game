package com.techelevator.model;

public class Scene {

    private int sceneId;

    private String description;

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Scene(int sceneId, String description) {
        this.sceneId = sceneId;
        this.description = description;
    }

    public Scene() {

    }
}

