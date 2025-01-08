package com.techelevator.controller;

import com.techelevator.dao.ScoreDAO;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Score;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/scores")
public class ScoresController {

    private final ScoreDAO scoreDao;

    public ScoresController(ScoreDAO scoreDao) {
        this.scoreDao = scoreDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Score create(@RequestBody @Valid Score score) {
        return scoreDao.createScore(score);
    }

    @RequestMapping(path = "/{scoreId}", method = RequestMethod.PUT)
    public Score update(@RequestBody @Valid Score score, @PathVariable int scoreId) {
        score.setScoreId(scoreId);
        try {
            Score updatedScore = scoreDao.updateScore(score);
            return updatedScore;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Score> getScores() {
        return scoreDao.getScores();
    }
}
