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
    private QuestionStorage questionStorage;

    @Autowired
    private QuestionService service;

    @GetMapping(value = "/question")
    public List<Question> getAllQuestions() {

        return questionStorage.getAllQuestions();
    }

    @GetMapping(value = "/question/id")
    public List<Question> getQuestionById(@RequestParam int id) {

        return questionStorage.getQuestionById(id);
    }

    @GetMapping(value = "/question/ThemeAndDifId")
    public List<Question> getQuestionByThemeAndDifId(@RequestParam int idTheme, @RequestParam int idDif) {

        return questionStorage.getQuestionByThemeAndDifId(idTheme, idDif);
    }

    @GetMapping(value = "/question/ThemeAndDifId/random")
    public QuestionDTO getRandomQuestionByThemeAndDifId(@RequestParam int idTheme, @RequestParam int idDif) {

        return service.getRandomQuestionByThemeAndDifId(idTheme, idDif);
    }

    @PostMapping(value = "/question/add")
    public List<Question> addQuestion(@RequestBody Question question) {

        return questionStorage.addQuestion(question);
    }
}

