package com.techelevator.controller;


import com.techelevator.dao.ChoiceDAO;
import com.techelevator.dao.SceneDAO;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Choice;
import com.techelevator.model.Scene;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/scenes")
public class ScenesController {

    private final SceneDAO sceneDao;
    private final ChoiceDAO choiceDao;

    public ScenesController(SceneDAO sceneDao, ChoiceDAO choiceDao) {
        this.sceneDao = sceneDao;
        this.choiceDao = choiceDao;
    }

    @GetMapping
    public List<Scene> getScenes() {
        return sceneDao.getScenes();
   }

   @GetMapping(path = "/choices")
   public List<Choice> getAllChoices() {
        return choiceDao.getChoices();
   }

   @GetMapping(path = "/{id}/choices")
   public List<Choice> getChoicesBySceneId(@PathVariable int id) { //@PathVariable so Spring can go get the variable
        return choiceDao.getChoicesBySceneId(id);
   }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Scene create(@RequestBody @Valid Scene scene) {
        return sceneDao.createScene(scene);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/choices", method = RequestMethod.POST)
    public Choice create(@RequestBody @Valid Choice choice) {
        return choiceDao.createChoice(choice);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{sceneId}", method = RequestMethod.PUT)
    public Scene update(@RequestBody @Valid Scene scene, @PathVariable int sceneId) {
        scene.setSceneId(sceneId);
        try {
            Scene updatedScene = sceneDao.updateScene(scene);
            return updatedScene;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{sceneId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int sceneId) {
        sceneDao.deleteSceneById(sceneId);
    }


}
