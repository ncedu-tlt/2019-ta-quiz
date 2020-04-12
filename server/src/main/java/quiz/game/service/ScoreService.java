package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.dto.ScoreDTO;
import quiz.game.model.entity.Result;
import quiz.game.model.entity.Score;
import quiz.game.storage.ResultStorage;
import quiz.game.storage.ScoreStorage;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ScoreService {
    @Autowired
    ScoreStorage scoreStorage;
    @Autowired
    UserService userService;
    @Autowired
    ResultService resultService;

    public void saveScore (Score score) {
        scoreStorage.addScore(score);
    }

    public List<ScoreDTO> getScoresByUserId(HttpServletRequest request) {
        List<Score> scores = scoreStorage.getScoresByUserId(userService.getUserFromJWT(request).getId());
        List<ScoreDTO> result = new ArrayList<>();
        for (Score score : scores) {
            ScoreDTO temp = new ScoreDTO(score);
            List<Result> res = resultService.getResultsByGameIdRaw(score.getIdGame());
            temp.setTheme(res.get(0).getAnswer().getQuestion().getTheme().getThemeName());
            temp.setDifficult(res.get(0).getAnswer().getQuestion().getDifficult().getDifficultName());
            result.add(temp);
        }
        return result;
    }
}
