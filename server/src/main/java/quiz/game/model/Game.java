package quiz.game.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

public class Game {

    private UUID gameId;

    private int chosenThemeId;

    private int chosenDifId;

    private List<Integer> questionList;

    private int progress;

    public Game() {
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public int getChosenThemeId() {
        return chosenThemeId;
    }

    public void setChosenThemeId(int chosenThemeId) {
        this.chosenThemeId = chosenThemeId;
    }

    public int getChosenDifId() {
        return chosenDifId;
    }

    public void setChosenDifId(int chosenDifId) {
        this.chosenDifId = chosenDifId;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Integer> questionList) {
        this.questionList = questionList;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
