package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.dto.GameDTO;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.service.GameService;
import quiz.game.service.ResultService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class ResultController {
    @Autowired
    private ResultService resultService;

    @Autowired
    private GameService gameService;

    @PostMapping(value = "/results")
    public QuestionDTO addQuestion(@RequestParam int idAnswer, HttpServletRequest request) {
        resultService.addUserAnswer(request, idAnswer);
        return gameService.getNextQuestion(request);
    }

    @GetMapping(value = "/results")
    public GameDTO getResultsByGameId(HttpServletRequest request) {
        //return resultService.getResultsByGameId();
        return gameService.getGameResults(request);
    }



}
