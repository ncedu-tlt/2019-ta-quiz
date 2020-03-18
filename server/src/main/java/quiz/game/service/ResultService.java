package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.storage.ResultStorage;
import quiz.game.model.entity.Result;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class ResultService {
    @Autowired
    ResultStorage resultStorage;

    @Autowired
    AnswerService answerService;

    @Autowired
    UserService userService;

    public void addUserAnswer(String username, int idAnswer, HttpSession session) {
        Date date = new Date();
        Result result = new Result(date, session.getId(), userService.getUserByUsername(username), answerService.getAnswerById(idAnswer));
        resultStorage.addUserAnswer(result);
    }

    public Result getResultsByUserId(int id) {
        return resultStorage.getResultsByUserId(id);
    }
}
