package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import quiz.game.model.dto.ResultAnswerDTO;
import quiz.game.model.dto.ResultQuestionDTO;
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

    public ResponseEntity<?> addUserAnswer(HttpServletRequest request, int idAnswer) {
        Date date = new Date();
        Result result = new Result(date, gameService.getGameId(request) , userService.getUserFromJWT(request), answerService.getAnswerById(idAnswer));
        return gameService.addUserAnswer(result, request);
        //resultStorage.addUserAnswer(result);
        //game.setScore(idAnswer);
    }

    public List<Result> getResultsByUserId(int id) {
        return resultStorage.getResultsByUserId(id);
    }

    public List<ResultQuestionDTO> getResultsByGameId(UUID gameId) {

        List<Result> results = resultStorage.getResultsByGameId(gameId);
        List<ResultQuestionDTO> resultQuestionDTO = new ArrayList<>();

        for (Result result : results) {
            List<ResultAnswerDTO> resultAnswerDTO = new ArrayList<>();
            List<Answer> answers = answerService.getAllAnswersByQuestionId(result.getAnswer().getQuestion().getId());

            for (Answer answer : answers) {
                if (result.getAnswer().getId() == answer.getId()) {
                    ResultAnswerDTO tempAnswerDTO = new ResultAnswerDTO(answer.getAnswerText(), answer.getAnswerIsCorrect(), true);
                    resultAnswerDTO.add(tempAnswerDTO);
                } else {
                    ResultAnswerDTO tempAnswerDTO = new ResultAnswerDTO(answer.getAnswerText(), answer.getAnswerIsCorrect(), false);
                    resultAnswerDTO.add(tempAnswerDTO);
                }
            }

            ResultQuestionDTO tempQuestionDTO = new ResultQuestionDTO(result.getAnswer().getQuestion().getQuestionName(), resultAnswerDTO);
            resultQuestionDTO.add(tempQuestionDTO);
        }
        return resultQuestionDTO;
    }

    public void saveUserAnswer (Result result) {
        resultStorage.addUserAnswer(result);
    }

}
