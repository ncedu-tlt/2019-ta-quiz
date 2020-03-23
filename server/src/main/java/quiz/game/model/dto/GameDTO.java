package quiz.game.model.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GameDTO {
    private Date date;
    private UUID idGame;
    private int Score;
    private List<ResultDTO> results;

    public GameDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UUID getIdGame() {
        return idGame;
    }

    public void setIdGame(UUID idGame) {
        this.idGame = idGame;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public List<ResultDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultDTO> results) {
        this.results = results;
    }
}
