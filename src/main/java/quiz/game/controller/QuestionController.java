package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Question;
import quiz.game.service.QuestionService;
import quiz.game.storage.QuestionStorage;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping(value = "/questions")
    public List<Question> getAllQuestions() {

        return service.getAllQuestions();
    }

    @GetMapping(value = "/questions/{id}")
    public QuestionDTO getQuestionById(@PathVariable int id) {

        return service.getQuestionById(id);
    }

    @GetMapping(value = "/questions/ThemeAndDifId")
    public List<Integer> getQuestionByThemeAndDifId(@RequestParam int idTheme, @RequestParam int idDif, @RequestParam int qty) {

        return service.getQuestionsByThemeAndDifId(idTheme, idDif, qty);
    }

    @GetMapping(value = "/questions/ThemeAndDifId/random")
    public QuestionDTO getRandomQuestionByThemeAndDifId(@RequestParam int idTheme, @RequestParam int idDif, @RequestParam int qty) {

        return service.getRandomQuestionByThemeAndDifId(idTheme, idDif, qty);
    }

    @PostMapping(value = "/questions/add")
    public List<Question> addQuestion(@RequestBody Question question) {

        return service.addQuestion(question);
    }
}

