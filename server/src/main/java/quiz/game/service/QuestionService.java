package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Question;
import quiz.game.storage.QuestionStorage;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionStorage questionStorage;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private GameService game;

    public List<Question> getAllQuestions() {

        return questionStorage.getAllQuestions();
    }

    public QuestionDTO getQuestionById(int id) {
        QuestionDTO question = new QuestionDTO(questionStorage.getQuestionById(id));
        List<AnswerDTO> answers = answerService.getAllAnswersByQuestionIdWOCorrect(question.getId());
        question.setAnswers(answers);
        return question;
    }

    public QuestionDTO getQuestionsByThemeAndDifId(int idTheme, int idDif, int qty, HttpServletRequest request) {
        List<Question> questions = questionStorage.getQuestionsByThemeAndDifId(idTheme, idDif, qty);
        List<Integer> result = new ArrayList<>();
        for (Question question : questions) {
            result.add(question.getId());
        }
        return game.start(idTheme, idDif, result, request);
        //return result;
    }

    public List<Question> addQuestion(Question question) {

        return questionStorage.addQuestion(question);
    }
}
