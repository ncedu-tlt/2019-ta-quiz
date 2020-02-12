package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import quiz.game.model.Question;
import quiz.game.storage.QuestionStorage;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionStorage questionStorage;

    @GetMapping(value = "/question")
    public List<Question> getAllQuestions() {
        return questionStorage.getAllQuestions();
    }

    @PostMapping(value = "/question/add")
    public List<Question> addQuestion(@RequestBody Question question) {
        return questionStorage.addQuestion(question);
    }
}

