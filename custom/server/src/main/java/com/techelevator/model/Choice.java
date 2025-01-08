package com.techelevator.model;

public class Choice {

    private int choiceId;
    private int sceneId;
    private String description;
    private boolean isSafe;
    private String consequence;

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

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

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    public String getConsequence() {
        return consequence;
    }

    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    public Choice() {

    }

    public Choice(int choiceId, int sceneId, String description, boolean isSafe, String consequence) {
        this.choiceId = choiceId;
        this.sceneId = sceneId;
        this.description = description;
        this.isSafe = isSafe;
        this.consequence = consequence;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "choiceId=" + choiceId +
                ", sceneId=" + sceneId +
                ", description='" + description +
                ", isSafe=" + isSafe +
                ", consequence='" + consequence + '\'' +
                '}';
    }
}
