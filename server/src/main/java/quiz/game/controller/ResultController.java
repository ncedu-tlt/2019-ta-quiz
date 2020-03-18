package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.entity.Result;
import quiz.game.service.ResultService;

import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
public class ResultController {
    @Autowired
    private ResultService service;

    @PostMapping(value = "/results")
    public void addQuestion(@RequestParam String username, @RequestParam int idAnswer, HttpSession session) {
        service.addUserAnswer(username, idAnswer, session);
    }

    @GetMapping(value = "/results")
    public Result getResultsByUserId(int id) {
        return service.getResultsByUserId(id);
    }
}
