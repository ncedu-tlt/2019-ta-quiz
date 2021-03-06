package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Question;
import quiz.game.payload.request.QuestionAddRequest;
import quiz.game.service.AnswerService;
import quiz.game.service.GameService;
import quiz.game.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private GameService gameService;
/*
    @GetMapping(value = "/questions")
    public List<Question> getAllQuestions() {

        return questionService.getAllQuestions();
    }
 */
/*
    @GetMapping(value = "/questions/{id}")
    public QuestionDTO getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }
 */
    @GetMapping(value = "/questions/ThemeAndDifId")
    public QuestionDTO getQuestionByThemeAndDifId(@RequestParam int idTheme, @RequestParam int idDif, HttpServletRequest request) {
        return gameService.start(idTheme, idDif, request);
    }

    @PostMapping(value = "/questions/add")
    public void addQuestion(@RequestBody QuestionAddRequest questionAddRequest) {
        Question question =
        questionService.addQuestion(questionAddRequest);
        answerService.addAnswer(question, questionAddRequest.getAnswers());
    }
}

