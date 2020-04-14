package quiz.game.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.model.Statistic;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDTO {
    private float rightAnswerPercent;
    private int totalScore;
    private Long totalGames;
    private List<Statistic> specialty;
}
