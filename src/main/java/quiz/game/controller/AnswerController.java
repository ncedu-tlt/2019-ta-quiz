package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private AnswerStorage answerStorage;

    @Autowired
    private AnswerService service;

    @GetMapping(value = "/answer")
    public List<Answer> getAllAnswers() {

        return answerStorage.getAllAnswers();
    }

    @GetMapping(value = "/answer/question-id")
    public List<Answer> getAllAnswersByQuestionId(@RequestParam int id) {

        return answerStorage.getAllAnswersByQuestionId(id);
    }

    @GetMapping(value = "/answer/question-id/WOCorrect")
    public List<AnswerDTO> getAllAnswersByQuestionIdWOCorrect(@RequestParam int id) {

        return service.getAllAnswersByQuestionIdWOCorrect(id);
    }
}
