package quiz.game.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.model.Statistic;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDTO {
    private float rightAnswerPercent;
    private int totalScore;
    private Long totalGames;
    private List<Statistic> specialty;

    public void setRightAnswerPercent(float rightAnswerPercent) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#0.00");
        formatter.setDecimalFormatSymbols(dfs);
        this.rightAnswerPercent = Float.valueOf(formatter.format(rightAnswerPercent));
    }
}
