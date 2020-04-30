package quiz.game.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Data
@NoArgsConstructor
public class Statistic {
    private String theme;
    private String difficult;
    private float rightAnswerPercent;
    private Integer totalScore;
    private Long totalGames;

    public void setRightAnswerPercent(float rightAnswerPercent) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#0.00");
        formatter.setDecimalFormatSymbols(dfs);
        this.rightAnswerPercent = Float.valueOf(formatter.format(rightAnswerPercent));
    }
}
