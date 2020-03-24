package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.entity.Answer;
import quiz.game.storage.AnswerStorage;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerStorage answerStorage;

    public List<Answer> getAllAnswers() {

        return answerStorage.getAllAnswers();
    }

    public List<Answer> getAllAnswersByQuestionId(int id) {

        return answerStorage.getAllAnswersByQuestionId(id);
    }

    public List<AnswerDTO> getAllAnswersByQuestionIdWOCorrect(int id) {
        List<Answer> answers = answerStorage.getAllAnswersByQuestionId(id);
        List<AnswerDTO> answersDTO = new ArrayList<>();
        for (Answer answer : answers) {
            AnswerDTO temp = new AnswerDTO(answer);
            answersDTO.add(temp);
        }
        return answersDTO;
    }

}
