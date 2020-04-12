package quiz.game.model.dto;

import quiz.game.model.entity.Score;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ScoreDTO {
    private UUID idGame;
    private String date;
    private String theme;
    private String difficult;
    private int score;

    public ScoreDTO() {
    }

    public ScoreDTO(Score score) {
        this.idGame = score.getIdGame();
        DateFormat df = new SimpleDateFormat("HH:mm:ss dd-mm-yyyy");
        this.date = df.format(score.getDate());
        this.score = score.getScore();
    }

    public UUID getIdGame() {
        return idGame;
    }

    public void setIdGame(UUID idGame) {
        this.idGame = idGame;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
