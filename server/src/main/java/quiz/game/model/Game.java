package quiz.game.model;

import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

    private UUID gameId;

    private int chosenThemeId;

    private int chosenDifId;

    private List<QuestionDTO> questionList;

    private List<Result> userAnswers = new ArrayList<>();

    private int progress;

    private int score;

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

    public List<QuestionDTO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionDTO> questionList) {
        this.questionList = questionList;
    }

    public List<Result> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Result> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public void addUserAnswer(Result userAnswer) {
        this.userAnswers.add(userAnswer);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
