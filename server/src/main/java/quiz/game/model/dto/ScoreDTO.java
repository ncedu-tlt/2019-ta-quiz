package quiz.game.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.model.entity.Score;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDTO {
    private UUID idGame;
    private String date;
    private String theme;
    private String difficult;
    private int score;

    public ScoreDTO(Score score) {
        this.idGame = score.getIdGame();
        DateFormat df = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        this.date = df.format(score.getDate());
        this.score = score.getScore();
        this.theme = score.getTheme().getThemeName();
        this.difficult = score.getDifficult().getDifficultName();
    }
}
