package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.entity.Result;
import quiz.game.storage.ResultStorage;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class ResultService {
    @Autowired
    ResultStorage resultStorage;

    @Autowired
    AnswerService answerService;

    public void addUserAnswer(int idUser, int idAnswer, HttpSession session) {
        Date date = new Date();

        Result result = new Result(date, session.getId(), idUser, answerService.getAnswerById(idAnswer));
        resultStorage.addUserAnswer(result);
    }
}
