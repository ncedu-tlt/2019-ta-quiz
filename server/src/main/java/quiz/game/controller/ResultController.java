package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.dto.ResultGameDTO;
import quiz.game.model.dto.ResultQuestionDTO;
import quiz.game.model.dto.ScoreDTO;
import quiz.game.model.entity.Score;
import quiz.game.service.GameService;
import quiz.game.service.ResultService;
import quiz.game.service.ScoreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class ResultController {
    @Autowired
    private ResultService resultService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ScoreService scoreService;

    @PostMapping(value = "/results")
    public ResponseEntity<?> addUserAnswer(@RequestParam int idAnswer, HttpServletRequest request) {
        ResponseEntity<?> response = resultService.addUserAnswer(request, idAnswer);
        if (response.getStatusCode().isError()) {
            return response;
        } else {
            return new ResponseEntity<>(gameService.getNextQuestion(request), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/results")
    public ResultGameDTO getLastGameResults(HttpServletRequest request) {
        return gameService.getGameResults(request);
    }

    @GetMapping(value = "/results/{id}")
    public List<ResultQuestionDTO> getResultsByGameId(@PathVariable UUID id) {
        return resultService.getResultsByGameId(id);
    }

    @GetMapping(value = "/results/all")
    public List<ScoreDTO> getUserGames(HttpServletRequest request) {
        return scoreService.getScoresByUserId(request);
    }
}
