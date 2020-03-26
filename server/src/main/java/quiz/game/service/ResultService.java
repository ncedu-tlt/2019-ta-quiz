package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.dto.ResultDTO;
import quiz.game.model.entity.Answer;
import quiz.game.model.entity.Result;
import quiz.game.storage.ResultStorage;

import javax.servlet.http.HttpServletRequest;
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
    private GameService gameService;

    public void addUserAnswer(HttpServletRequest request, int idAnswer) {
        Date date = new Date();
        Result result = new Result(date, gameService.getGameId(request) , userService.getUserFromJWT(request), answerService.getAnswerById(idAnswer));
        gameService.addUserAnswer(result, request);
        //resultStorage.addUserAnswer(result);
        //game.setScore(idAnswer);
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

    public void saveUserAnswer (Result result) {
        resultStorage.addUserAnswer(result);
    }

}
