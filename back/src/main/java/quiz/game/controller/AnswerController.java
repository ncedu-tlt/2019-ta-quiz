package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.entity.Answer;
import quiz.game.service.AnswerService;
import quiz.game.storage.AnswerStorage;

import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService service;

    @GetMapping(value = "/answers")
    public List<Answer> getAllAnswers() {

        return service.getAllAnswers();
    }

    @GetMapping(value = "/answers/{id}/correct")
    public List<Answer> getAllAnswersByQuestionId(@PathVariable int id) {

        return service.getAllAnswersByQuestionId(id);
    }

    @GetMapping(value = "/answers/{id}")
    public List<AnswerDTO> getAllAnswersByQuestionIdWOCorrect(@PathVariable int id) {

        return service.getAllAnswersByQuestionIdWOCorrect(id);
    }
}