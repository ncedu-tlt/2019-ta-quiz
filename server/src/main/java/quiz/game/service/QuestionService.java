package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Question;
import quiz.game.payload.request.QuestionAddRequest;
import quiz.game.storage.QuestionStorage;

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

    @Autowired
    private ThemeService themeService;

    @Autowired
    private DifficultService difficultService;

    public List<Question> getAllQuestions() {

        return questionStorage.getAllQuestions();
    }

    public QuestionDTO getQuestionById(int id) {
        QuestionDTO question = new QuestionDTO(questionStorage.getQuestionById(id));
        List<AnswerDTO> answers = answerService.getAllAnswersByQuestionIdWOCorrect(question.getId());
        question.setAnswers(answers);
        return question;
    }

    public List<Integer> getQuestionsByThemeAndDifId(int idTheme, int idDif, int qty) {
        List<Question> questions = questionStorage.getQuestionsByThemeAndDifId(idTheme, idDif, qty);
        List<Integer> result = new ArrayList<>();
        for (Question question : questions) {
            result.add(question.getId());
        }
        game.start(idTheme, idDif, result);
        return result;
    }

    public Question addQuestion(QuestionAddRequest questionAddRequest) {
        Question question = new Question();
        question.setTheme(themeService.getThemeById(questionAddRequest.getThemeId()));
        question.setDifficult(difficultService.getDifficultById(questionAddRequest.getDifficultId()));
        question.setQuestionName(questionAddRequest.getQuestionName());
        return questionStorage.addQuestion(question);
    }
}
