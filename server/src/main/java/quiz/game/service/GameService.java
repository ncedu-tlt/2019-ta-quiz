package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.Game;
import quiz.game.model.dto.GameDTO;
import quiz.game.model.entity.Answer;

import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    @Autowired
    private ResultService resultService;
    @Autowired
    private AnswerService answerService;

    private Game game;

    public void start(int chosenThemeId, int chosenDifId, List<Integer> questionList) {
        this.game = new Game();
        game.setChosenThemeId(chosenThemeId);
        game.setChosenDifId(chosenDifId);
        game.setQuestionList(questionList);
        game.setGameId(UUID.randomUUID());
    }

    public GameDTO getGameResults() {
        GameDTO result = new GameDTO();
        result.setResults(resultService.getResultsByGameId(game.getGameId()));
        result.setScore(game.getScore());
        result.setIdGame(game.getGameId());
        return result;
    }

    public int getScore() {
        return game.getScore();
    }


    public UUID getGameId() {
        return game.getGameId();
    }

    public void setScore(int idAnswer) {
        Answer answer = answerService.getAnswerById(idAnswer);
        int points = answer.getQuestion().getDifficult().getDifficultFactor();
        if (answer.isAnswerIsCorrect()) {
            game.setScore(game.getScore() + (points *100));
        }
    }
}

