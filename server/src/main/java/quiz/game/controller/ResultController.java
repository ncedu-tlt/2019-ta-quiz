package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.dto.GameDTO;
import quiz.game.model.dto.ResultDTO;
import quiz.game.model.entity.Result;
import quiz.game.service.GameService;
import quiz.game.service.ResultService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class ResultController {
    @Autowired
    private ResultService resultService;

    @Autowired
    private GameService gameService;

    @PostMapping(value = "/results")
    public void addQuestion(@RequestParam String username, @RequestParam int idAnswer) {
        resultService.addUserAnswer(username, idAnswer);
    }

    @GetMapping(value = "/results")
    public GameDTO getResultsByGameId() {
        //return resultService.getResultsByGameId();
        return gameService.getGameResults();
    }

}
