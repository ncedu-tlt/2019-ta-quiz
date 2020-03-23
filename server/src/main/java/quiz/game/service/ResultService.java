package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.Game;
import quiz.game.model.dto.ResultDTO;
import quiz.game.model.entity.Answer;
import quiz.game.model.entity.Question;
import quiz.game.storage.QuestionStorage;
import quiz.game.storage.ResultStorage;
import quiz.game.model.entity.Result;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ResultService {
    @Autowired
    private ResultStorage resultStorage;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;

    @Autowired
    private GameService game;

    public void addUserAnswer(String username, int idAnswer) {
        Date date = new Date();
        Result result = new Result(date, game.getGameId() , userService.getUserByUsername(username), answerService.getAnswerById(idAnswer));
        resultStorage.addUserAnswer(result);
        game.setScore(idAnswer);
    }

    public List<Result> getResultsByUserId(int id) {
        return resultStorage.getResultsByUserId(id);
    }

    public List<ResultDTO> getResultsByGameId(UUID gameId) {
        List<Result> results = resultStorage.getResultsByGameId(gameId);
        List<ResultDTO> resultDTO = new ArrayList<>();
        for (Result result : results) {
            List<Answer> answers = answerService.getAllAnswersByQuestionId(result.getAnswer().getQuestion().getId());
            ResultDTO tempResult = new ResultDTO(result, answers);
            resultDTO.add(tempResult);
        }
        return resultDTO;
    }
}
