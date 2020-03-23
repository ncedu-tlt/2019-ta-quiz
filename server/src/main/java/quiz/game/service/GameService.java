package quiz.game.service;

import org.springframework.stereotype.Service;
import quiz.game.model.Game;

import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private Game game;

    public void start(int chosenThemeId, int chosenDifId, List<Integer> questionList) {
        this.game = new Game();
        game.setChosenThemeId(chosenThemeId);
        game.setChosenDifId(chosenDifId);
        game.setQuestionList(questionList);
        game.setGameId(UUID.randomUUID());
    }

    public int getScore() {
        return game.getScore();
    }

    public UUID getGameId() {
        return game.getGameId();
    }
}

