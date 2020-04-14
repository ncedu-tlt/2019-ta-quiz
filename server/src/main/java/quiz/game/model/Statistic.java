package quiz.game.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Statistic {
    private String theme;
    private String difficult;
    private float rightAnswerPercent;
    private Integer totalScore;
    private Long totalGames;
}
