package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.Game;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Question;
import quiz.game.service.GameService;
import quiz.game.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private GameService gameService;

    @GetMapping(value = "/questions")
    public List<Question> getAllQuestions() {

        return questionService.getAllQuestions();
    }

    @GetMapping(value = "/questions/{id}")
    public QuestionDTO getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping(value = "/questions/ThemeAndDifId")
    public QuestionDTO getQuestionByThemeAndDifId(@RequestParam int idTheme, @RequestParam int idDif, @RequestParam int qty, HttpServletRequest request) {
        return questionService.getQuestionsByThemeAndDifId(idTheme, idDif, qty, request);
    }

    @GetMapping(value = "/questions/next")
    public QuestionDTO getNextQuestion(HttpServletRequest request) {
        return gameService.getNextQuestion(request);
    }


    @PostMapping(value = "/questions/add")
    public List<Question> addQuestion(@RequestBody Question question) {

        return questionService.addQuestion(question);
    }
}

