package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Question;
import quiz.game.payload.request.QuestionAddRequest;
import quiz.game.service.AnswerService;
import quiz.game.service.QuestionService;

import java.util.List;

@CrossOrigin
@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @GetMapping(value = "/questions")
    public List<Question> getAllQuestions() {

        return questionService.getAllQuestions();
    }

    @GetMapping(value = "/questions/{id}")
    public QuestionDTO getQuestionById(@PathVariable int id) {

        return questionService.getQuestionById(id);
    }

    @GetMapping(value = "/questions/ThemeAndDifId")
    public List<Integer> getQuestionByThemeAndDifId(@RequestParam int idTheme, @RequestParam int idDif, @RequestParam int qty) {

        return questionService.getQuestionsByThemeAndDifId(idTheme, idDif, qty);
    }

    @PostMapping(value = "/questions/add")
    public void addQuestion(@RequestBody QuestionAddRequest questionAddRequest) {
        Question question =
        questionService.addQuestion(questionAddRequest);
        answerService.addAnswer(question, questionAddRequest.getAnswers());
    }
}
