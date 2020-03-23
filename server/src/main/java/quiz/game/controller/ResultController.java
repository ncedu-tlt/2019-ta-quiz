package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.dto.ResultDTO;
import quiz.game.model.entity.Result;
import quiz.game.service.ResultService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class ResultController {
    @Autowired
    private ResultService service;

    @PostMapping(value = "/results")
    public void addQuestion(@RequestParam String username, @RequestParam int idAnswer) {
        service.addUserAnswer(username, idAnswer);
    }

    @GetMapping(value = "/results")
    public List<ResultDTO> getResultsByGameId() {
        return service.getResultsByGameId();
    }
}
