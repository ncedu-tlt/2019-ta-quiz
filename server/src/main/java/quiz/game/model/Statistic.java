package quiz.game.model;

public class Statistic {
    private String theme;
    private String difficult;
    private float rightAnswerPercent;
    private Integer totalScore;
    private Long totalGames;

    public Statistic() {
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public float getRightAnswerPercent() {
        return rightAnswerPercent;
    }

    public void setRightAnswerPercent(float rightAnswerPercent) {
        this.rightAnswerPercent = rightAnswerPercent;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Long getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Long totalGames) {
        this.totalGames = totalGames;
    }
}
