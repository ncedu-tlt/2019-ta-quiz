package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.dto.ResultGameDTO;
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
    public ResponseEntity<?> addUserAnswer(@RequestParam int idAnswer, HttpServletRequest request) {
        ResponseEntity<?> response = resultService.addUserAnswer(request, idAnswer);
        if (response.getStatusCode().isError()) {
            return response;
        } else {
            return new ResponseEntity<>(gameService.getNextQuestion(request), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/results")
    public ResultGameDTO getResultsByGameId(HttpServletRequest request) {
        return gameService.getGameResults(request);
    }


}
