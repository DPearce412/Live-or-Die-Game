package com.techelevator.dao;

import com.techelevator.model.Choice;

import java.util.List;

public interface ChoiceDAO {

    Choice getChoiceById(int choiceId);

    List<Choice> getChoices();

    Choice createChoice(Choice newChoice);


    Choice updateChoice(Choice choice);

    int deleteChoiceById(int choiceId);

    List<Choice> getChoicesBySceneId(int sceneId);
            
}
