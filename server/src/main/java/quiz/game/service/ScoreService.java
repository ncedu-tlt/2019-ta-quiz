package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.Statistic;
import quiz.game.model.dto.ScoreDTO;
import quiz.game.model.dto.StatisticDTO;
import quiz.game.model.entity.*;
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
    @Autowired
    ThemeService themeService;
    @Autowired
    DifficultService difficultService;

    public void saveScore (Score score) {
        scoreStorage.addScore(score);
    }

    public List<ScoreDTO> getScoresByUserId(HttpServletRequest request) {
        List<Score> scores = scoreStorage.getScoresByUserId(userService.getUserFromJWT(request).getId());
        List<ScoreDTO> result = new ArrayList<>();
        for (Score score : scores) {
            ScoreDTO temp = new ScoreDTO(score);
            result.add(temp);
        }
        return result;
    }

    public StatisticDTO getStatistic(HttpServletRequest request) {
        StatisticDTO result = new StatisticDTO();
        User user = userService.getUserFromJWT(request);
        Long totalGames = scoreStorage.getUserGamesCount(user.getId());
        Integer totalScore = scoreStorage.getUserSumScore(user.getId());
        List<Result> results = resultService.getResultsByUserId(user.getId());
        List<ScoreDTO> scores = getScoresByUserId(request);
        List<Statistic> specialty = new ArrayList<>();
        List<Theme> themes = themeService.getAllThemes();
        List<Difficult> difs = difficultService.getAllDifficult();
        for (Theme theme : themes) {
            for (Difficult difficult : difs) {
                Statistic temp = new Statistic();
                temp.setDifficult(difficult.getDifficultName());
                temp.setTheme(theme.getThemeName());
                temp.setTotalGames(scoreStorage.getUserGamesCount(user.getId(), theme.getId(), difficult.getId()));
                Integer integer = scoreStorage.getUserSumScore(user.getId(), theme.getId(), difficult.getId());
                if (integer == null) {
                    temp.setTotalScore(0);
                } else {
                    temp.setTotalScore(integer);
                }
                specialty.add(temp);
            }
        }

        int rightAnswersCounter = 0;
        for (Result res : results) {
            if (res.getAnswer().isAnswerIsCorrect()) {
                rightAnswersCounter++;
            }
        }

        for (Statistic item : specialty) {
            if (item.getTotalGames() != 0) {
                float totalCounter = 0;
                for (Result res : results) {
                    if (res.getAnswer().getQuestion().getTheme().getThemeName().equals(item.getTheme())
                            && res.getAnswer().getQuestion().getDifficult().getDifficultName().equals(item.getDifficult())) {
                        totalCounter++;
                        if (res.getAnswer().isAnswerIsCorrect()) {
                            item.setRightAnswerPercent(item.getRightAnswerPercent()+1);
                        }
                    }
                }
                item.setRightAnswerPercent(item.getRightAnswerPercent() / totalCounter * 100);
            }
        }

        result.setRightAnswerPercent((float)rightAnswersCounter / (float)results.size() * 100);
        result.setTotalGames(totalGames);
        result.setTotalScore(totalScore);
        result.setSpecialty(specialty);
        return result;
    }
}
