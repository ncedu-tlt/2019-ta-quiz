package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Question;
import quiz.game.storage.QuestionStorage;

import java.util.List;
import java.util.Random;

@Service
public class QuestionService {

    @Autowired
    private QuestionStorage questionStorage;

    @Autowired
    private AnswerService answerService;

    public static<T> void Shuffle(List<T> list)
    {
        Random random = new Random();
        int n = list.size();
        for (int i = 0; i < n - 1; i++)
        {
            int j = i + random.nextInt(n - i);
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    public QuestionDTO getRandomQuestionByThemeAndDifId(int idTheme,int idDif) {
        Random random = new Random();
        List<Question> questions = questionStorage.getQuestionByThemeAndDifId(idTheme, idDif);
        QuestionDTO question = new QuestionDTO(questions.get(random.nextInt(questions.size())));
        List<AnswerDTO> answers = answerService.getAllAnswersByQuestionIdWOCorrect(question.getId());
        Shuffle(answers);
        question.setAnswers(answers);
        return question;
    }
}
