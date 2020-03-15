package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quiz.game.service.ResultService;

import javax.servlet.http.HttpSession;

@RestController
public class ResultController {
    @Autowired
    private ResultService service;

    @PostMapping(value = "/results")
    public void addQuestion(@RequestParam int idUser, @RequestParam int idAnswer, HttpSession session) {
        service.addUserAnswer(idUser, idAnswer, session);
    }
}
