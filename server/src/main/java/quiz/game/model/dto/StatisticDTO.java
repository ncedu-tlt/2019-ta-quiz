package quiz.game.model.dto;

import quiz.game.model.Statistic;

import java.util.List;

public class StatisticDTO {
    private float rightAnswerPercent;
    private int totalScore;
    private Long totalGames;
    private List<Statistic> specialty;

    public StatisticDTO() {
    }

    public float getRightAnswerPercent() {
        return rightAnswerPercent;
    }

    public void setRightAnswerPercent(float rightAnswerPercent) {
        this.rightAnswerPercent = rightAnswerPercent;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Long getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Long totalGames) {
        this.totalGames = totalGames;
    }

    public List<Statistic> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(List<Statistic> specialty) {
        this.specialty = specialty;
    }
}
